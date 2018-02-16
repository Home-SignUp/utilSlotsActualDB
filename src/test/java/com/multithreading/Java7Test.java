package com.multithreading;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class Java7Test {

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

        ForkJoinPool fjp = ForkJoinPool.commonPool();
        Future<Integer> future = fjp.submit(r);

        Integer res = future.get();
        System.out.println("testFuture is finished: " + res);
    }
}
