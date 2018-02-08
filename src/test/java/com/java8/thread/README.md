[Markdown support](https://daringfireball.net/projects/markdown/syntax)

[Хоткеи в IntelliJ-IDEA](https://juja.com.ua/java/ide/intellij-idea-hotkeys) ([+](http://eax.me/intellij-idea-hotkeys))

Java 8 CompletableFuture
---

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
  1. выполнить: `new` (создать пустой); `supplyAsync` (выполнить лямбду); `runAsync` (выполнить Runnable...);
  2. завершать: `complete` (можно поместить значение для успешного результата); `completeExceptionally` (можно завершить с помощью Exception...);
  3. навешать callbacks-методы, которые принимают какие-то значения: `thenAccept`; `thenRun`; `exceptionally`; `handle`;
  4. трансформация: `thenApply`; комбинирование: `thenCombine` (выполнить два действия одновременно И только после того как будет доступно оба результат выполнить функцию...); композиция: `thenCompose` (сценарий каких-то последовательных действий);

* CompletableFuture можно:
  - завершать только 1-раз
  - выполнять действия асинхронно, НО при этом он НЕисключает блокирование (в каком-то треде...)
  - контекст выполнения:
    1. если метод НЕ '..Async' - то он выполняется в том же потоке где выполнился код который завершил Future
    2. если метод '..Async' - то он выполняется: либо в каком-то пуле потоков ('ForkJoinPool.commonPool');  либо в переданном 'Executor';




