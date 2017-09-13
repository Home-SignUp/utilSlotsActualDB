
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


Работать с потоками напрямую неудобно и чревато ошибками. Поэтому в 2004 году в Java 5 добавили Concurrency API `java.util.concurrent`

Исполнители
---

    `Executors` — (класс) предоставляет фабричные методы для создания сервисов исполнителей.
    `ExecutorService` — (интерфейс) сервис исполнителей, выполняют задачи асинхронно и используют пул потоков (так что нам не надо создавать их вручную).
    Все потоки из пула будут использованы повторно после выполнения задачи (это одна из самых важных частей Concurrency API)... 
    Но есть важное отличие — он никогда не остановится, поэтому работу исполнителей надо завершать явно:
    shutdown() — ждет завершения запущенных задач
    shutdownNow() — останавливает исполнитель немедленно

```java
ExecutorService executor = Executors.newSingleThreadExecutor();
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




