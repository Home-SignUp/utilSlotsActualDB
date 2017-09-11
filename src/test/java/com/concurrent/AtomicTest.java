//package com.concurrent;
//
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.stream.IntStream;
//
//import org.junit.Test;
//
//public class AtomicTest {
//
////    @Test
////    public void test0(){
////        Executors.newFixedThreadPool(2)
////                .invokeAll([ new Summer(data: [1,2,3,4,5]), new Summer(data: [-1,-2,-3,-4,-5]) ])
////
//////        print("Sum: ${Sum.sum}")
////    }
//
//    /**
//     * @see https://tproger.ru/translations/java8-concurrency-tutorial-3
//     *      ************************************************************
//     *      использование AtomicInteger вместо обычного Integer позволило нам корректно увеличить число, распределив работу сразу по двум потокам.
//     *      'incrementAndGet()' является атомарной операцией
//     * Used only JDK-1.8
//     */
//    @Test
//    public void test1(){
//        AtomicInteger atomicInt = new AtomicInteger(0);
//
//        ExecutorService executor = Executors.newFixedThreadPool(2);
//
//        IntStream.range(0, 1000)
//                .forEach(i -> executor.submit(atomicInt::incrementAndGet));
//
////        stop(executor);
//
//        System.out.println(atomicInt.get());    // => 1000
//    }
//
//}
//
////class Sum {
////    static Object monitor = new Object();
////    static volatile long sum = 0;
////}
////
////class Summer implements Callable {
////    long[] data;
////
////    @Override
////    public Object call()
////            throws Exception {
////        for (long d : data){
////            synchronized (Sum.monitor) {
////                System.out.println("${Thread.currentThread().name}: add ${it} to ${Sum.sum}");
////                Sum.sum += Sum.sum;
////            }
////        }
////        return null;
////    }
////}