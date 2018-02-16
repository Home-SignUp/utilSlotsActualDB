package com.multithreading;

import org.junit.Test;

import java.util.concurrent.*;

public class Java8Test {

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
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(this::slowInit);

        Integer res = future.get();
        System.out.println("testFuture is finished: " + res);
    }
}
