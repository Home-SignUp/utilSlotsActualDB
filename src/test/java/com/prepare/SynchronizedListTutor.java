package com.prepare;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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

    public static int ERR = 0;
//    static String[] langs = {"SQL", "PHP", "XML", "Java", "Scala", "Python", "JavaScript", "ActionScript", "Clojure", "Groovy", "Ruby", "C++"};
//    volatile String[] langs = {"SQL", "PHP", "XML", "Java", "Scala", "Python", "JavaScript", "ActionScript", "Clojure", "Groovy", "Ruby", "C++"};
    static volatile String[] langs = {"SQL", "PHP", "XML", "Java", "Scala", "Python", "JavaScript", "ActionScript", "Clojure", "Groovy", "Ruby", "C++"};
    List<String> randomLangs = new ArrayList<>();

    private static int length;

    /*
     * Метода 'length' работает очень медленно!!!
     */
    static {
        length = langs.length;
    }

    /**
     * В каждом потоке выполнется 100.000 раз вызов метода 'length' для объекта 'langs'.
     * Метода 'length' работает очень медленно!!!
     * Кроме этого, этот объект является критической секцией И поэтому здесь большая вероятность встречи коллизий!!!
     *
     * Проблема заключается в том, чтобы уменьшить общее количество создаваемых объектов в heap-памяти джавы и количество вызовов метода 'length'.
     * Для этой цели можно сделать статический объект 'langs' И только один раз делать иннициализацию метода 'length' (в статической блоке...)
     * Кроме этого, каждый раз при выполнении операции для вычисления индекса нужно синхронизировать эту функции ПОТОМУ-ЧТО это НЕатомарная операция
     *
     * @return
     */
    public static synchronized String getRandomLangs() {
//        synchronized (langs) {
            int index = (int) (Math.random() * length); //(int) (Math.random() * langs.length);
            return langs[index];
//        }
    }

    class TestThread implements Runnable {
        String threadName;

        public TestThread(String threadName) {
            this.threadName = threadName;
        }

        /*
         * в цыкле выполняется 100.000 итерация над объектом 'langs' И создается 100.000 объектов...
         */
        @Override
        public void run() {
            for (int i=0; i<100_000; i++){
                try {
                    randomLangs.add(getRandomLangs());
                } catch (ArrayIndexOutOfBoundsException e){
                    ERR++;
                    System.err.println (">>> (ArrayIndexOutOfBoundsException) " + e.getMessage());
                } catch (OutOfMemoryError e){
                    ERR++;
                    System.err.println (">>> (OutOfMemoryError) " + e.getMessage());
                }
            }
        }
    }

//    public void print(Collection<?> c) {
//        StringBuilder builder = new StringBuilder();
//        Iterator<?> iterator = c.iterator();
//        while(iterator.hasNext()) {
//            builder.append(iterator.next());
//            builder.append(" ");
//        }
//        System.out.println(builder.toString());
//    }

    /**
     * >>> (ArrayIndexOutOfBoundsException) 267803
     *
     * в каждом потоке выполняется 100.000 итераций над объектом 'langs'
     */
    @Test
    public void testThread() {
        List<Thread> threads = new ArrayList<>();
        final int length = 90; // 4 - 30 - 40 - 60 - 90 - 100 потоков

        for (int i=0; i<length; i++){
            threads.add(new Thread(new TestThread("t-"+i)));
        }
        System.out.println("> Starting threads (" + length + ")");

        for (int i=0; i<length; i++) {
            threads.get(i).start();
        }
        System.out.println("> Waiting for threads");

        try {
            for (int i=0; i<length; i++){
                threads.get(i).join();
            }
            System.out.println("> Stop threads");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (0<ERR)
            System.err.println ("     ------------------------------\n\r     ERR = " + ERR + ";  Thread(s) = " + length + ";");
        else
            System.out.println ("     ------------------------------\n\t     ERR = " + ERR + ";  Thread(s) = " + length + ";");
    }
}
