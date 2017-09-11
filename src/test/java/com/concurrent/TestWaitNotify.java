package com.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @see http://www.javenue.info/post/91
 */

public class TestWaitNotify extends Thread {
    // should be >= 3
    static final int THREAD_COUNT = 3;

    static final CyclicBarrier barrier =
            new CyclicBarrier(THREAD_COUNT);
    static final Object mutex = new Object();
    static final AtomicInteger counter = new AtomicInteger(0);

    static volatile boolean exitCondition = false;

    TestWaitNotify(String name) {
        super(name);
    }

    public void run() {
        try {
            barrier.await();

            synchronized (mutex) {
                System.out.println(getName() + ": monitor acquired");

                if (counter.getAndIncrement() < THREAD_COUNT - 2) {
                    System.out.println(getName() + ": invoking wait");

                    while (!exitCondition)
                        mutex.wait();
                    exitCondition = false;

                    System.out.println(getName() + ": woken up");
                }

                exitCondition = true;
                System.out.println(getName() + ": invoking notify");
                mutex.notify();
            }
            System.out.println(getName() + ": monitor released");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread t = new TestWaitNotify("Thread-" + i);
            t.start();
        }
    }
}
