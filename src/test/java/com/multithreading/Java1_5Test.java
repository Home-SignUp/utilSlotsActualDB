package com.multithreading;

import org.junit.Test;

import java.util.concurrent.*;

public class Java1_5Test {

    /**
     * @see https://www.youtube.com/watch?v=vVyjcJLFNWQ
     */
    class MyRunnable2 implements Runnable {
        @Override public void run() {
            System.out.println(1);
        }
    }

    @Test
    public void test()
            throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);

        service.submit(new MyRunnable2());

        service.shutdown(); // sleep...
    }
}
