[Markdown support](https://daringfireball.net/projects/markdown/syntax)

[Хоткеи в IntelliJ-IDEA](https://juja.com.ua/java/ide/intellij-idea-hotkeys) ([+](http://eax.me/intellij-idea-hotkeys))


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

    `Runnable`, `Callable` — это задачи которые могут быть переданы исполнителям.
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


Интерфейс `Lock` (Синхронизация и Блокировки)
---

    `ReentrantLock` — (класс) реализует то же поведение что и обычные неявные блокировки
    `tryLock()` — возвращает булевый результат который стоит проверить перед тем как пытаться производить какие-то действия с общими объектами
    `lock()` — осуществляет блокировку 
    `unlock()` — освобождаются ресурсы
    `ReadWriteLock` — (интерфейс) блокировока отдельная для чтения и отдельная для запись
    `StampedLock` — (появился в Java 8) в отличие от ReadWriteLock, возвращает 'штамп' — значение типа long для может использоваться в дальнейшем как для высвобождения ресурсов, так и для проверки состояния блокировки.
                     Вдобавок у этого класса есть методы для 'оптимистичной блокировки':
                     `tryOptimisticRead()` — блокировка для чтения
                     `tryConvertToWriteLock()` — преобразовать блокировку для чтения в блокировку для записи не высвобождая ресурсы
    `validate(stamp)` — проверяет является ли блокировка валидной

```java
// пример с увеличением на единицу:
ReentrantLock lock = new ReentrantLock();
int count = 0;
 
void increment() {
    lock.lock();
    try {
        count++;
    } finally {
        lock.unlock();
    }
}
```

```java
ExecutorService executor = Executors.newFixedThreadPool(2);
Map<String, String> map = new HashMap<>();
ReadWriteLock lock = new ReentrantReadWriteLock();
 
executor.submit(() -> {
    lock.writeLock().lock();
    try {
        sleep(1);
        map.put("foo", "bar");
    } finally {
        lock.writeLock().unlock();
    }
});
```

```java
ExecutorService executor = Executors.newFixedThreadPool(2);
StampedLock lock = new StampedLock();
 
executor.submit(() -> {
    long stamp = lock.readLock();
    try {
        if (count == 0) {
            stamp = lock.tryConvertToWriteLock(stamp);
            if (stamp == 0L) {
                System.out.println("Could not convert to write lock");
                stamp = lock.writeLock();
            }
            count = 23;
        }
        System.out.println(count);
    } finally {
        lock.unlock(stamp);
    }
});
 
stop(executor);
```


Семафоры
---
ограничивает количество потоков, которые одновременно работают над одним и тем же ресурсом:


```java
ExecutorService executor = Executors.newFixedThreadPool(10);
 
Semaphore semaphore = new Semaphore(5);
 
Runnable longRunningTask = () -> {
    boolean permit = false;
    try {
        permit = semaphore.tryAcquire(1, TimeUnit.SECONDS);
        if (permit) {
            System.out.println("Semaphore acquired");
            sleep(5);
        } else {
            System.out.println("Could not acquire semaphore");
        }
    } catch (InterruptedException e) {
        throw new IllegalStateException(e);
    } finally {
        if (permit) {
            semaphore.release();
        }
    }
}
 
IntStream.range(0, 10)
    .forEach(i -> executor.submit(longRunningTask));
 
stop(executor);
```


AtomicInteger, AtomicBoolean, AtomicLong и AtomicReference
---

    `incrementAndGet()` — является атомарной операцией
    `updateAndGet()` — принимает в качестве аргумента лямбда-выражение и выполняет над числом заданные арифметические операции
    `LongAdder` — (класс) альтернативы AtomicLong для последовательного сложения чисел
    `LongAccumulator` — (класс) расширяет LongAdder, вместо простого сложения он обрабатывает входящие значения с помощью лямбды типа LongBinaryOperator

полезные классы для выполнения атомарных операций `java.concurrent.atomic`
(атомарная операция когда её можно безопасно выполнять при параллельных вычислениях в нескольких потоках не используя при этом ни блокировок ни synchronized)
Эти инструкции работают гораздо быстрее, чем синхронизация с помощью блокировок:

```java
AtomicInteger atomicInt = new AtomicInteger(0);
 
ExecutorService executor = Executors.newFixedThreadPool(2);
 
IntStream.range(0, 1000)
    .forEach(i -> executor.submit(atomicInt::incrementAndGet));
 
stop(executor);
 
System.out.println(atomicInt.get());    // => 1000
```

```java
AtomicInteger atomicInt = new AtomicInteger(0);
 
ExecutorService executor = Executors.newFixedThreadPool(2);
 
IntStream.range(0, 1000)
    .forEach(i -> {
        Runnable task = () ->
            atomicInt.updateAndGet(n -> n + 2);
        executor.submit(task);
    });
 
stop(executor);
 
System.out.println(atomicInt.get());    // => 2000
```

```java
ExecutorService executor = Executors.newFixedThreadPool(2);
 
IntStream.range(0, 1000)
    .forEach(i -> executor.submit(adder::increment));
 
stop(executor);
 
System.out.println(adder.sumThenReset());   // => 1000
```

```java
LongBinaryOperator op = (x, y) -> 2 * x + y;
LongAccumulator accumulator = new LongAccumulator(op, 1L);
 
ExecutorService executor = Executors.newFixedThreadPool(2);
 
IntStream.range(0, 10)
    .forEach(i -> executor.submit(() -> accumulator.accumulate(i)));
 
stop(executor);
 
System.out.println(accumulator.getThenReset());     // => 2539
```




* [Atomic](https://habrahabr.ru/post/187854)
* [Многопоточное программирование в Java 8. Часть третья. Атомарные переменные и конкурентные таблицы](https://tproger.ru/translations/java8-concurrency-tutorial-3)

http://please.noroutine.me/2011/08/atomic.html
---

* [Особенности Java 8 – максимальное руководство (часть 2)](http://info.javarush.ru/translation/2014/10/09/Особенности-Java-8-максимальное-руководство-часть-2-.html)
* [Java Concurrency - Part 6 : Atomic Variables](https://baptiste-wicht.com/posts/2010/09/java-concurrency-atomic-variables.html)
