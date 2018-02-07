package com.java8.thread;

import org.junit.Test;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Consumer;

import static java.util.stream.Collectors.joining;
import static sun.misc.Version.println;


public class CompletableFutureTest {

    /**
     * @see https://www.youtube.com/watch?v=8EoINS1Kacs
     *      *******************************************
     * Future и Promise - это примитивы для создания многопоточных приложений
     * Результатом любого вычисления является значение. Это значение можно поместить в некий абстрактный контейнер (такой контейнер является immutable)...
     *
     * Promise - это функция которая помещает значение (результат вычисления кода) в контейнер.
     * Future - (это 'proxy') позволяет: следить за контейнером и когда появится какое-то значение - выполнить 'callback-функцию'; и еще трансформирует это значение внутри контейнера;
     *        - но НЕпозволяет получать данные из контейнера;
     *
     * методы Future (в Java-5): isDone, get (блокирующий)
     * методы CompletableFuture (в Java-8):
     * 1. выполнить: new (создать пустой); supplyAsync (выполнить лямбду); runAsync (выполнить Runnable...);
     * 2. завершать: complete (можно поместить значение для успешного результата); completeExceptionally (можно завершить с помощью Exception...);
     * 3. навешать callbacks-методы, которые принимают какие-то значения: thenAccept; thenRun; exceptionally; handle;
     * 4. трансформация: thenApply; комбинирование: thenCombine (выполнить два действия одновременно И только после того как будет доступно оба результат выполнить функцию...); композиция: thenCompose (сценарий каких-то последовательных действий);
     *
     * * CompletableFuture можно:
     *   - завершать только 1-раз
     *   - выполнять действия асинхронно, НО при этом он НЕисключает блокирование (в каком-то треде...)
     *   - контекст выполнения:
     *     1. если метод НЕ '..Async' - то он выполняется в том же потоке где выполнился код который завершил Future
     *     2. если метод '..Async' - то он выполняется: либо в каком-то пуле потоков ('ForkJoinPool.commonPool');  либо в переданном 'Executor';
     */
    @Test
    public void test1() {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.thenAccept(s -> { // callbacks-метод выполниться в том потоке в котором закончит выполняться наш Future...
            System.out.println("Completed in thread - #1, Message: " + s);
        });

        System.out.println("Not completed yet in thread: #2");
        future.complete("Completed async");
    }

    @Test
    public void test2() {
        CompletableFuture<String> future = new CompletableFuture<>();
        // такой метод НЕстоит часто использовать (так как он только один на всю JVM и поэтому может быть сильно перегружен...)
        future.thenAcceptAsync(s -> { // выполняется в каком-то пуле потоков 'ForkJoinPool.commonPool' (в Java существует только 1-commonPool который стартует вместе с JVM и он всегда доступен...)
            System.out.println("Completed in thread - #1, Message: " + s);
        });
        future.complete("Completed async");

        Executor executor = Executors.newSingleThreadExecutor();
        future.thenAcceptAsync(s -> { // здесь выполнение будет переданно нашему Executor ('newSingleThreadExecutor')
            System.out.println("Completed in thread - #2, Message: " + s);
        }, executor);
        future.complete("Completed async");
    }

    /**
     * Пример: чтение файла асинхронно
     */
    @Test
    public void test3() {
//        readFileAsync("schema.sql",
        readFileAsync("CallableFuturesTest.java",
                System.out::println,
                Throwable::printStackTrace);

//        // можно с контентом который выполняется уже асинхронно, мы хотим сделать еще что-то асинхронно... (запихнуть свои вложенные callback-и):
//        readFileAsync("CallableFuturesTest.java",
//                path -> readFileAsync("CallableFuturesTest.java",
//                        System.out::println,
//                        Throwable::printStackTrace),
//                Throwable::printStackTrace);

//        // решаем эту проблему с помощью 'CompletableFuture'
//        CompletableFuture.supplyAsync(() -> readFileAsync("CallableFuturesTest.java", System.out::println, Throwable::printStackTrace))
//                .thenApplyAsync(readFileAsync("CallableFuturesTest.java", System.out::println, Throwable::printStackTrace))
//                .thenApplyAsync(() -> println("File successfully read"));
//        });
    }

    private void readFileAsync(String path,
                               Consumer<String> onSuccess,
                               Consumer<Throwable> onFailure) {
        ForkJoinPool.commonPool().submit(() -> {
            try {
                List<String> lines = Files.readAllLines(Paths.get(path));
                String content = lines.stream().collect(joining("\n"));
                onSuccess.accept(content);
            } catch (IOException ioe) {
                onFailure.accept(ioe);
            }
        });
    }

    /**
     * Нпример: синхронное чтение и веб-сервис
     */
    @Test
    public void test4() {

    }

    @GET
    @javax.ws.rs.Path("sync-content")
    @Produces(MediaType.TEXT_PLAIN)
    public void acyncGet(@Suspended final AsyncResponse ar) {
//        CompletableFuture<String> path = readFileAsync("CallableFuturesTest.java");
//        CompletableFuture<String> content = readFileAsync("schema.sql");
//        path.thenCombineAsync(content, (p,c) -> p + " - " + c)
//                .thenApplyAsync(ar::resume)
//                .exceptionally(ar::resume);
    }
    private String readFileAsync(String path) {
        String content = null;
        ForkJoinPool.commonPool().submit(() -> {
            try {
                List<String> lines = Files.readAllLines(Paths.get(path));
//                content = lines.stream().collect(joining("\n"));
            } catch (IOException ioe) {
            }
        });
        return content;
    }
}