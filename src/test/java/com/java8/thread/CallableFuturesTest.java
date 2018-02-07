package com.java8.thread;

import org.junit.Test;

import java.util.concurrent.*;


public class CallableFuturesTest {

    /**
     * @see https://www.youtube.com/watch?v=SWKvCA8eClg
     */
    @Test
    public void test1() throws Exception {
        Callable<Integer> callable = new MyCallable();
        FutureTask task = new FutureTask(callable);
        new Thread(task).start();

        System.out.println( "task = " + task.get() );
    }

    /**
     * @see https://www.youtube.com/watch?v=vVyjcJLFNWQ
     */
    @Test
    public void test2() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);

        service.submit(new MyRunnable2());

        Future<Integer> future = service.submit(new MyCallable2());
        System.out.println( future.get() );

        service.shutdown();
    }

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

    class MyRunnable2 implements Runnable {
        @Override
        public void run() {
            System.out.println(1);
        }
    }

    class MyCallable2 implements Callable<Integer> {
        @Override
        public Integer call() {
            return 2;
        }
    }
}
