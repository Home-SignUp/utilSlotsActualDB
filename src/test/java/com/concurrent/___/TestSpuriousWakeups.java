package com.concurrent.___;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @see http://www.javenue.info/post/91
 */

public class TestSpuriousWakeups {
    static final int MAX_THREADS = 1000;

    static final Object mutex = new Object();

    static final CountDownLatch allThreadsStarted = new CountDownLatch(MAX_THREADS);
    static final CountDownLatch allThreadsFinished = new CountDownLatch(1);

    static final AtomicInteger processedThreads = new AtomicInteger();
    static final AtomicInteger notifiedThreads = new AtomicInteger();

    static volatile boolean continueCondition = true;

    static final Random sleepRandom = new Random();

    static class Worker extends Thread {
        public void run() {
            try {
                synchronized (mutex) {
                    allThreadsStarted.countDown();

                    mutex.wait();
                }

                continueCondition = true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                processedThreads.incrementAndGet();
            }
        }
    }

    static class Notifier extends Thread {
        public void run() {
            while (true) {
                // remove block to achieve starvation
                while (!continueCondition)
                    doStuff();

                // all threads finished their execution
                if (processedThreads.get() == MAX_THREADS)
                    break;

                synchronized (mutex) {
                    doStuff();

                    mutex.notify();
                    continueCondition = false;
                    notifiedThreads.incrementAndGet();
                }
            }

            allThreadsFinished.countDown();
        }

        // just to emulate some activity
        void doStuff() {
            try { Thread.sleep(sleepRandom.nextInt(5)); }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < MAX_THREADS; i++)
            new Worker().start();

        // wait for all workers to start execution
        allThreadsStarted.await();

        new Notifier().start();

        // wait for all workers and notifier to finish execution
        allThreadsFinished.await();

        System.out.println("Spurious wakeups count: " + (MAX_THREADS - notifiedThreads.get()));
    }
}
