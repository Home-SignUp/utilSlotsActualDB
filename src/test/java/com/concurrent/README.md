
* [Atomic](https://habrahabr.ru/post/187854)
* [Многопоточное программирование в Java 8. Часть третья. Атомарные переменные и конкурентные таблицы](https://tproger.ru/translations/java8-concurrency-tutorial-3)

http://please.noroutine.me/2011/08/atomic.html
---
* `AtomicInteger` (тут все ясно)
* `AtomicLong` (тут тоже)
* `AtomicBoolean` (яснее некуда)
* `AtomicReference` (атомная ссылка на экземпляр чего-либо, не имеет отношения к java.lang.ref.Reference)

* [Особенности Java 8 – максимальное руководство (часть 2)](http://info.javarush.ru/translation/2014/10/09/Особенности-Java-8-максимальное-руководство-часть-2-.html)
* [Java Concurrency - Part 6 : Atomic Variables](https://baptiste-wicht.com/posts/2010/09/java-concurrency-atomic-variables.html)


[_Многопоточное программирование в Java 8_](https://github.com/Home-Java8/java8-tutorial) **(** [_оригинал_](https://github.com/winterbe/java8-tutorial) **)**
---
1. [Параллельное выполнение кода с помощью потоков](https://tproger.ru/translations/java8-concurrency-tutorial-1)
2. [Синхронизация доступа к изменяемым объектам](https://tproger.ru/translations/java8-concurrency-tutorial-2)
3. [Атомарные переменные и конкурентные таблицы](https://tproger.ru/translations/java8-concurrency-tutorial-3)

    `Процесс` — это экземпляр программы, который запускается независимо от остальных — участок кода, который обычно называется задачей (task).
    `Потоки` — можем использовать внутри процессов...
    Это делается через реализацию интерфейса `Runnable` у которого есть только один метод без аргументов: `void run()`

```java
Runnable task = () -> {
    String threadName = Thread.currentThread().getName();
    System.out.println("Hello " + threadName);
};
 
task.run();
 
Thread thread = new Thread(task);
thread.start();
 
System.out.println("Done!")

// => Hello main
// => Hello Thread-0
// => Done!
```

Работать с потоками напрямую неудобно и чревато ошибками. Поэтому в 2004 году в Java 5 добавили Concurrency API `java.util.concurrent`

Исполнители
---

    `Executors` — (класс) предоставляет фабричные методы для создания сервисов исполнителей.
    submit() — ...
    `ExecutorService` — (интерфейс) сервис исполнителей, выполняют задачи асинхронно и используют пул потоков (так что нам не надо создавать их вручную).
    Все потоки из пула будут использованы повторно после выполнения задачи (это одна из самых важных частей Concurrency API)... 
    Но есть важное отличие — он никогда не остановится, поэтому работу исполнителей надо завершать явно:
    shutdown() — ждет завершения запущенных задач
    shutdownNow() — останавливает исполнитель немедленно

```java
ExecutorService executor = Executors.newSingleThreadExecutor(); // вернет исполнителя с пулом в 1-поток
ExecutorService executor = Executors.newFixedThreadPool(10);    // вернет исполнителя с пулом в 10-потоков (фиксированного количества потоков)
ExecutorService executor = Executors.newWorkStealingPool();     // (появился в Java 8) создает ForkJoinPool с определенным параллелизмом (по умолчанию равным количеству ядер машины)
executor.submit(() -> {
    String threadName = Thread.currentThread().getName();
    System.out.println("Hello " + threadName);
});
 
// => Hello pool-1-thread-1
```

```java
try {
    System.out.println("attempt to shutdown executor");
    executor.shutdown();
    executor.awaitTermination(5, TimeUnit.SECONDS); // Исполнитель пытается завершить работу, ожидая завершения запущенных задач в течение 5 секунд
} catch (InterruptedException e) {
    System.err.println("tasks interrupted");
} finally {
    if (!executor.isTerminated()) {
        System.err.println("cancel non-finished tasks");
    }
    executor.shutdownNow();
    System.out.println("shutdown finished");
}
```


Callable и Future
---

    Runnable, Callable задачи могут быть переданы исполнителям.
    (`Runnable` — это функциональный интерфейс, который ничего не принимает и ничего не возвращает)
    `Callable` — это функциональный интерфейс, он может возвращать значение.
    submit() — не ждет завершения задачи, исполнитель не может вернуть результат задачи напрямую.
                Вместо этого исполнитель возвращает специальный объект `Future` у которого мы сможем запросить результат задачи.

```java
// возвращает целое число после секундной паузы:
Callable task = () -> {
    try {
        TimeUnit.SECONDS.sleep(1);
        return 123;
    }
    catch (InterruptedException e) {
        throw new IllegalStateException("task interrupted", e);
    }
};
```

```java
ExecutorService executor = Executors.newFixedThreadPool(1);
Future<Integer> future = executor.submit(task);
 
System.out.println("future done? " + future.isDone()); // isDone() — проверяет завершено ли выполнение задачи
 
Integer result = future.get(); // get() — блокирует поток и ждет завершения задачи, а затем возвращает результат ее выполнения
 
System.out.println("future done? " + future.isDone());
System.out.print("result: " + result);

// => future done? false
// => future done? true
// => result: 123
```


invokeAll(), invokeAny()
---

    Исполнители могут принимать список задач на выполнение:
    `invokeAll()` — принимает коллекцию `Callable` задач и возвращает список из `Future`
    `invokeAny()` — блокирует поток до того как завершится хоть одна задача и возвращает ее результат

```java
ExecutorService executor = Executors.newWorkStealingPool();
 
List<Callable<String>> callables = Arrays.asList(
        () -> "task1",
        () -> "task2",
        () -> "task3");
 
executor.invokeAll(callables)
    .stream()
    .map(future -> {
        try {
            return future.get();
        }
        catch (Exception e) {
            throw new IllegalStateException(e);
        }
    })
    .forEach(System.out::println);
```

```java
ExecutorService executor = Executors.newWorkStealingPool();
 
List<Callable<String>> callables = Arrays.asList(
    callable("task1", 2),
    callable("task2", 1),
    callable("task3", 3));
 
String result = executor.invokeAny(callables);
System.out.println(result);
 
// => task2
```










