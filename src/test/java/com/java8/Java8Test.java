package com.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @see https://habrahabr.ru/post/256057
 *      https://habrahabr.ru/post/203026
 *      https://ru.stackoverflow.com/questions/561224/как-решить-задачу-с-помощью-stream-api
 */
public class Java8Test {

    @Test
    public void test(){
        // Очень вкусное нововведение, позволяющее заменить анонимные классы на функции
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

//        numbers.forEach(value -> System.out.println(value));
//        numbers.forEach(System.out::println);


        // Создание потока
//        new Thread(SomeClass::run).start();


        // Сортировка с компаратором
        Collections.sort(numbers, (n1, n2) -> n2.compareTo(n1));

        numbers.forEach(System.out::print); //numbers.forEach(value -> System.out.print(value));

        // Stream - Допустим, нам нужно посчитать сумму всех чисел в списке, умноженных на два
        // старый способ Java-7
//        int sum = 0;
//        for (int value : numbers) {
//            sum += value*2;
//        }
        // новый способ Java-8
        int result = numbers.stream()
                .mapToInt(value -> value*2)
                .sorted()
                .flatMap()
                .sum();
        System.out.println(
                numbers.stream()
                        .mapToInt(value -> value*2)
                        .sum()
        );
        // Допустим, поступил заказ модифицировать код и обрабатывать лишь четные, а нечетные отфильтровывать:
        System.out.println(
                numbers.stream()
                        .filter(value -> value%2 == 0)
                        .mapToInt(value -> value*2)
                        .sum()
        );
    }

}

class SomeClass implements Runnable {
    @Override
    public void run() {
        System.out.println("SomeClass");
    }
}

/**
 * @see https://habrahabr.ru/post/203026
 *      ********************************
 *      В Java 8 интерфейс иногда что-то делает
 */
interface IFly {

    void takeOff();

    default void cruise() {
        System.out.println("IFly.cruise");
    }

    void turn();

    void land();

}

/**
 * @see https://habrahabr.ru/post/203026
 *      ********************************
 *      Допустим, класс наследует два интерфейса, и в каждом из них есть default метод.
 *      При компиляции возникнет ошибка!
 */
//interface A {
//    default void hello() {
//        System.out.println("Hello World from A");
//    }
//}
//
//interface B {
//    default void hello() {
//        System.out.println("Hello World from B");
//    }
//}
//
//class D implements A,B {
//}

/**
 * @see https://habrahabr.ru/post/203026
 *      ********************************
 *      Разберем другой пример-треугольник:
 *      В данном случае победит ближайший в иерархии, то есть интерфейс B
 *
 *      Ну а если хочется использовать метод интерфейса А, то нужно явно указать:
 *      A.super.hello();
 */
interface A {
    default void hello() {
        System.out.println("Hello World from A");
    }
}

interface B extends A {
    @Override default void hello() {
        System.out.println("Hello World from B");
    }
}

class C implements B, A {
    public static void main(String... args) {
        new C().hello();
    }
}
