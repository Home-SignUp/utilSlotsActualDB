[Markdown support](https://daringfireball.net/projects/markdown/syntax)

[Хоткеи в IntelliJ-IDEA](https://juja.com.ua/java/ide/intellij-idea-hotkeys) ([+](http://eax.me/intellij-idea-hotkeys))

Java 8 CompletableFuture
---

    CompletableFuture — новый класс для асинхронной работы, который дает возможность комбинировать шаги обработки, соединяя их в цепочку.
    Класс содержит около 50-методов

* https://www.youtube.com/watch?v=8EoINS1Kacs
* [Часть 1](https://vertex-academy.com/tutorials/ru/java-8-completablefuture)
* [Часть 2](https://vertex-academy.com/tutorials/ru/java-8-completablefuture-part-2)
* [Часть 3](https://vertex-academy.com/tutorials/ru/java-8-completablefuture-chast-3)


`Future` и `Promise` — это примитивы для создания многопоточных приложений
Результатом любого вычисления является значение. Это значение можно поместить в некий абстрактный контейнер (такой контейнер является immutable)...

`Promise` — это функция которая помещает значение (результат вычисления кода) в контейнер.
`Future` — (это 'proxy') позволяет: следить за контейнером и когда появится какое-то значение - выполнить 'callback-функцию'; и еще трансформирует это значение внутри контейнера;
         — но НЕпозволяет получать данные из контейнера;

* методы **Future** (в Java-5): `isDone`, `get` (блокирующий)
* методы **CompletableFuture** (в Java-8):
  1. Для создания CompletableFuture можно воспользоваться методами: `new` (создать пустой); `supplyAsync` (выполнить лямбду); `runAsync` (выполнить Runnable...);
     (где *future* исполнится в `ForkJoinPool.commonPool()`, так как мы не указывали ему *Executor*. Если мы хотим указать где будет исполняться *future* то передаем `Executor` вторым параметром)
  2. Для того чтобы получить результат с CompletableFuture необходимо вызвать метод: `get`
  3. навешать callbacks-методы, которые принимают какие-то значения: `thenAccept`; `thenRun`; `exceptionally`; `handle`;
```javascript
    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hi");
    future.thenAccept(result -> System.out.println(result));
    future.get();
```
  4.
**трансформация**: `thenApply` (добавление нескольких `callback`);
```javascript
    /**
     * Объединять CompletableFuture можно несколькими способами, один из них:
     * Метод thenApply() является аналогом Optional.map()
     * 
     * Нужно помнить, что функция в thenApply() исполняется в том же потоке, где вызывается.
     * Если же использовать  thenApplyAsync(), тогда функция будет исполнена как отдельная задача в ForkJoinPool.commonPool.
     */
    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hi");

    future.thenApply(result -> {
        System.out.println(result + " all"); //output Hi all
        return result;
    });

    future.thenApply(result -> {
        System.out.println(result + ", world!"); //output Hi, world!
        return result;
    });

    future.get();
```
**композиция**: `thenCompose` (сценарий каких-то последовательных действий);
```javascript
    /**
     * Объединять CompletableFuture можно несколькими способами, один из них:
     * В то время как thenCompose() является аналогом Optional.flatMap()
     */
    CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 10)
            .thenCompose(result ->
                    CompletableFuture.supplyAsync(() -> result * 2)
            ).thenCompose(result ->
                    CompletableFuture.supplyAsync(() -> result * 5)
            );
    System.out.println(future.get()); //output 100
```
**комбинирование**: `thenCombine` (выполнить два действия одновременно И только после того как будет доступно оба результат выполнить функцию...);
```javascript
    /**
     * Если мы хотим по завершению двух задач выполнить третью, то это можно сделать с помощью thenCombine()
     */
    CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 10);
    CompletableFuture<Integer> anotherFuture = CompletableFuture.supplyAsync(() -> 2);
    CompletableFuture<Integer> result = future.thenCombine(anotherFuture, (a, b) -> a * b);
     
    System.out.println(result.get()); //output 20
```
  5. завершать: `complete` (можно поместить значение для успешного результата); `completeExceptionally` (можно завершить с помощью Exception...);

* CompletableFuture можно:
  - завершать только 1-раз
  - выполнять действия асинхронно, НО при этом он НЕисключает блокирование (в каком-то треде...)
  - контекст выполнения:
    1. если метод НЕ '..Async' - то он выполняется в том же потоке где выполнился код который завершил Future
    2. если метод '..Async' - то он выполняется: либо в каком-то пуле потоков ('ForkJoinPool.commonPool');  либо в переданном 'Executor';


* метод `allOf()` — лишь запускает переданные задачи (а результат работы нужно получать у каждого CompletableFuture отдельно).
* метод `join()` — если же нам нужно получить результат работы всех задач


    Метод  join() как и get() — возвращает результат работы CompletableFuture,
    но в отличии от него join() — бросает RuntimeException в случае ошибки исполнения.
