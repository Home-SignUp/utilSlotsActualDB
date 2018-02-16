package com.multithreading;

import org.junit.Test;

import java.util.concurrent.*;

public class Java5Test {

    /**
     * @see https://www.youtube.com/watch?v=SWKvCA8eClg
     */
    class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            int i = 0;
            for (int j=0; j<10; j++, i++) {
                Thread.sleep(300);
            }
            return i;
        }
    }

    @Test
    public void test1() throws Exception {
        Callable<Integer> callable = new MyCallable();
        FutureTask task = new FutureTask(callable);
        new Thread(task).start();

        System.out.println( "task = " + task.get() );
    }


    class MyCallable2 implements Callable<Integer> {
        @Override
        public Integer call() {
            return 2;
        }
    }

    @Test
    public void test2()
            throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);

        Future<Integer> future = service.submit(new MyCallable2());
        System.out.println( future.get() );

        service.shutdown();
    }

    public Integer slowInit() {
        System.out.println("started task slowInit()");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        return 1;
    }

    @Test
    public void testFuture()
            throws InterruptedException, ExecutionException {
        Callable<Integer> r = this::slowInit;

        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Integer> future = es.submit(r);

        Integer res = future.get();
        System.out.println("testFuture is finished: " + res);
    }
}
