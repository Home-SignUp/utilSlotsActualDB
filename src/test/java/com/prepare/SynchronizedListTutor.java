package com.prepare;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @see http://becomejavasenior.com/blog/2015/07/11/java-task-multithreading-4
 *      **********************************************************************
 * 1) Попробуйте запустить программу.
 *    Почему программа (периодически) падает с ArrayIndexOutOfBoundException?
 *    Что надо сделать, чтобы этого не происходило?
 * 2) Теперь попробуйте уменьшить количество циклов в run() до 10 и добавить вывод на печать print() после добавления нового элемента.
 *    Почему происходит ConcurrentModificationException?
 *    Что сделать, чтобы этого не происходило?
 */

public class SynchronizedListTutor {

    static String [] langs =
            {"SQL", "PHP", "XML", "Java", "Scala", "Python", "JavaScript", "ActionScript", "Clojure", "Groovy", "Ruby", "C++"};
    List<String> randomLangs =
            new ArrayList<>();

    public String getRandomLangs() {
        int index = (int)(Math.random()*langs.length);
        return langs[index];
    }

    class TestThread implements Runnable {
        String threadName;

        public TestThread(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            for (int i=0; i<100000; i++){
                randomLangs.add(getRandomLangs());
            }
        }
    }

//    public void print(Collection<?> c) {
//        StringBuilder builder = new StringBuilder();
//        Iterator<?>  iterator = c.iterator();
//        while(iterator.hasNext()) {
//            builder.append(iterator.next());
//            builder.append(" ");
//        }
//        System.out.println(builder.toString());
//    }

    @Test
    public void testThread() {
        List<Thread> threads = new ArrayList<>();

        for (int i=0; i<100; i++){
            threads.add(new Thread(new TestThread("t"+i)));
        }
        System.out.println("Starting threads");

        for (int i=0;i<100;i++) {
            threads.get(i).start();
        }
        System.out.println("Waiting for threads");

        try {
            for (int i=0; i<100; i++){
                threads.get(i).join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
