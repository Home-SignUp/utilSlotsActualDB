package com.java8;

import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * https://habrahabr.ru/post/216431
 * @see https://javadevblog.com/polnoe-rukovodstvo-po-java-8-stream.html
 * @see https://habrahabr.ru/company/luxoft/blog/270383
 * https://ru.wikipedia.org/wiki/%D0%9C%D0%BE%D0%BD%D0%B0%D0%B4%D0%B0_(%D0%BF%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5)
 */

public class Java8Test2 {

    /**
     * @see https://javadevblog.com/polnoe-rukovodstvo-po-java-8-stream.html
     * @see https://habrahabr.ru/company/luxoft/blog/270383
     */
    @Test
    public void testStreamTerminal(){
        ////////////////////////////////////////////////////////////////////////////////////
        /**
         * Чтобы создать поток:
         * - stream // из существующего списка
         * - of     // из перечисляющих элементов
         */
        Stream.of("dd2", "aa2", "bb1", "bb3", "cc")
                .forEach(System.out::println);

        ////////////////////////////////////////////////////////////////////////////////////
        /**
         * Есть 'терминальные методы' которые ничего НЕвозвращают:
         * ifPresent, forEach
         *
         * Есть 'терминальные методы' которые возвращают:
         * - min, max, average, findFirst, findAny  // Optional
         * - sum, count                             // примитивные типы Java
         * - collect                                // List
         * - reduce                                 // Object
         */
        OptionalInt min = Stream.of(22, 2, 1, 33, 0).mapToInt(Integer::valueOf)
                .min(); // Optional
        OptionalInt max = Stream.of(22, 2, 1, 33, 0).mapToInt(Integer::valueOf)
                .max(); // Optional
        OptionalDouble average = Stream.of(22, 2, 1, 33, 0).mapToInt(Integer::valueOf)
                .average(); // Optional
        OptionalInt findFirst = Stream.of(22, 2, 1, 33, 0).mapToInt(Integer::valueOf)
                .findFirst(); // Optional
        OptionalInt findAny = Stream.of(22, 2, 1, 33, 0).mapToInt(Integer::valueOf)
                .findAny(); // Optional

        int sum = Stream.of(22, 2, 1, 33, 0).mapToInt(Integer::valueOf)
                .sum(); // int
        long count = Stream.of(22, 2, 1, 33, 0).mapToInt(Integer::valueOf)
                .count(); // long

        List<Integer> items = Stream.of(22, 2, 1, 33, 0)
                .collect(Collectors.toList()); // List

        Stream.of(22, 2, 1, 33, 0)
                .forEach(System.out::println);

        min.ifPresent(x -> System.out.println("min = " + x));
        max.ifPresent(x -> System.out.println("max = " + x));
        average.ifPresent(x -> System.out.println("average = " + x));
        findFirst.ifPresent(x -> System.out.println("findFirst = " + x));
        findAny.ifPresent(x -> System.out.println("findAny = " + x));

        System.out.println("sum = " + sum);
        System.out.println("count = " + count);

        items.forEach(System.out::println);

        ////////////////////////////////////////////////////////////////////////////////////
        /**
         * Stream НЕмогут быть использованы повторно (как только вызывается терминальная операция - поток закрывается)
         */
//        Stream<Integer> streamInt = Stream.of(22, 2, 1, 33, 0);
//        min = streamInt.mapToInt(Integer::valueOf).min(); // операция выполнится успешно
//        max = streamInt.mapToInt(Integer::valueOf).max(); // Вылетит Exception

        /**
         * Чтобы избежать этого, мы должны создать новую цепь для каждой терминальной операции.
         * Каждый вызов конструктора get() создает новый поток, с которым мы можем безопасно работать.
         */
        Supplier<Stream<Integer>> streamInt = () -> Stream.of(22, 2, 1, 33, 0);
        min = streamInt.get().mapToInt(Integer::valueOf)
                .min();
        max = streamInt.get().mapToInt(Integer::valueOf)
                .max();
        average = streamInt.get().mapToInt(Integer::valueOf)
                .average();
        findFirst = streamInt.get().mapToInt(Integer::valueOf)
                .findFirst();
        findAny = streamInt.get().mapToInt(Integer::valueOf)
                .findAny();
        sum = streamInt.get().mapToInt(Integer::valueOf)
                .sum();
        count = streamInt.get().mapToInt(Integer::valueOf)
                .count();
    }

    /**
     * @see https://javadevblog.com/polnoe-rukovodstvo-po-java-8-stream.html
     * @see https://habrahabr.ru/company/luxoft/blog/270383
     */
    @Test
    public void testStreamInterim() {
        // filter, map, mapToInt, sorted, limit

        /**
         * Все промежуточные методы возвращают только <Stream>
         * - filter, map, mapToInt, sorted, limit // Stream
         *
         * Некоторые промежуточные методы принимают <FunctionalInterface>
         * - filter, map, mapToInt // <FunctionalInterface>
         */
        Stream.of("dd2", "aa2", "bb1", "bb3", "bb5", "bb2", "cc")
                .filter(s -> s.startsWith("b"))                    // принимает <FunctionalInterface>, возвращает <Stream>
                .sorted((s1,s2) -> s2.compareTo(s1))               // принимает <FunctionalInterface>, возвращает <Stream>
                .limit(3)                                          // принимает 'Long', возвращает <Stream>
                .map(s -> s.replaceAll("b", "")) // принимает <FunctionalInterface>, возвращает <Stream>
                .forEach(System.out::println);

        System.out.println();

        Stream.of(22, 2, 1, 33, 0)
                .sorted()                   // ничего НЕпринимает, возвращает <Stream>
                .mapToInt(Integer::valueOf) // принимает <FunctionalInterface>, возвращает <Stream>
                .forEach(System.out::println);
    }

    /**
     * @see https://javadevblog.com/polnoe-rukovodstvo-po-java-8-stream.html
     *      ****************************************************************
     *      Терминальные (продвинутые) операции:
     *      COLLECT, reduce
     */
    @Test
    public void testCollect() {
        List<Person> persons = Arrays.asList(
                new Person("Andrew", 20),
                new Person("Igor", 23),
                new Person("Ira", 23),
                new Person("Vitia", 12));

        /**
         * Превращает элементы Stream-а в List, Set, Map
         * collect // принимает 'Collector' (который состоит из 4-ех операций: поставщик, аккумулятор, объединитель, финишер)
         */
        Collector<Person, StringJoiner, String> PERSON_COOLECTOR = Collector.of(
                () -> new StringJoiner(" | "), // (1) поставщик
                (j,p) -> j.add(p.name.toUpperCase()),   // (2) аккумулятор
                (j1,j2) -> j1.merge(j2),                // (3) объединитель
                StringJoiner::toString);                // (4) финишер
        String names = persons.stream()
                .collect(PERSON_COOLECTOR);
        System.out.println(names); // ANDREW | IGOR | IRA | VITIA

        List<Person> filtered = persons.stream()
                .collect(Collectors.toList()); // [Andrew, Igor, Ira, Vitia]
        System.out.println(filtered);

        Map<Integer, List<Person>> personsByAge = persons.stream()
                .collect(Collectors.groupingBy(p -> p.age)); // {20=[Andrew], 23=[Igor, Ira], 12=[Vitia]}
        System.out.println(personsByAge);

        Double averageAge = persons.stream()
                .collect(Collectors.averagingInt(p -> p.age)); // 19.5
        System.out.println(averageAge);

        String phrase = persons.stream()
                .filter(p -> p.age >= 18)
                .map(p -> p.name)
                .collect(Collectors.joining(" и ", "В Германии ", " совершеннолетние.")); // В Германии Andrew и Igor и Ira совершеннолетние.
        System.out.println(phrase);
    }

    /**
     * @see https://javadevblog.com/polnoe-rukovodstvo-po-java-8-stream.html
     *      ****************************************************************
     *      Терминальные (продвинутые) операции:
     *      collect, REDUCE
     */
    @Test
    public void testReduce() {
        List<Person> persons = Arrays.asList(
                new Person("Andrew", 20),
                new Person("Xxx", 30),
                new Person("Igor", 23),
                new Person("Ira", 23),
                new Person("Vitia", 12));

        /**
         * работа 'Reduce' очень схожа на 'Comparator' (он проходит по списку, берет 2-а рядом стоящих элемента и сравнивает их...)
         * Только 'Reduce' позволяет еще дополнительно выполнять другие операции и формировать ответ..
         */

        // Вариант #1:
        persons.stream()
                .reduce((p1,p2) ->      // (1) на вход принимает 2-а рядом стоящих элемента из списка
                        p1.age > p2.age // сравнивает их и формирует результат...
                                ? p1
                                : p2)
                .ifPresent(System.out::println); // Xxx

        // Вариант #2:
        Person person = persons.stream()
                .reduce(new Person(),     // (1) указываем ему какой тип результата нужно возвращать на выходе...
                        (p1,p2) -> {      // (2) на вход принимает 2-а рядом стоящих элемента из списка
                            Person p = new Person();
                            p.age += p1.age + p2.age;
                            p.name += p1.name + " " + p2.name;
                            return p; }); // и формирует результат...
        System.out.format("name = %s; \nage = %s;\n", person.name, person.age);

        // Вариант #3:
        Integer ages = persons.stream()
                .reduce(0,            // (1) первичная иннициализация значения...
                        (sum,p) ->            // (2) указываем сам элемент и его значение какое нужно брать...
                                sum += p.age,
                        (sum1,sum2) ->        // (3) на вход принимаетзначение из 2-ух рядом стоящих элементов из списка
                                sum1 + sum2); // и формирует результат...
        System.out.println(ages); // 108
    }

    /**
     * @see https://javadevblog.com/polnoe-rukovodstvo-po-java-8-stream.html
     *      ****************************************************************
     *      Промежуточные (продвинутые) операции:
     *      FLATMAP
     */
    @Test
    public void testFlatMap() {
        /**
         * ('map' - преобразовывает объекты одного потока в другие объекты для другого потока...)
         * 'flatMap' - (похож на 'map', только) преобразует каждый элемент одного потока в в другие объекты для МНОГИХ других потоков...
         */

//        List<Foo> foos = new ArrayList<>();
//
//        // create foos
//         IntStream.range(1, 4)
//                 .forEach(i ->
//                         foos.add(new Foo("Foo" + i)));
//
//        // create bars
//        foos.forEach(f ->
//                IntStream.range(1, 4)
//                        .forEach(i ->
//                                f.bars.add(new Bar("Bar" + i + " <- " + f.name))));
//
//        foos.stream()
//                .flatMap(f ->
//                        f.bars.stream())
//                .forEach(b ->
//                        System.out.println(b.name));

        IntStream.range(1, 4)
                .mapToObj(i -> new Foo("Foo" + i))
                .peek(f -> IntStream.range(1, 4)
                        .mapToObj(i -> new Bar("Bar" + i + " <- " + f.name))
                        .forEach(f.bars::add))
                .flatMap(f -> f.bars.stream())
                .forEach(b -> System.out.println(b.name));
    }
}

class Person {
    String name;
    int age;

    Person(){}

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + ":" + age;
    }
}

class Foo {
    String name;
    List<Bar> bars = new ArrayList<>();

    Foo(String name) {
        this.name = name;
    }
}

class Bar {
    String name;

    Bar(String name) {
        this.name = name;
    }
}
