package com.prepare.sort;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * @see http://echuprina.blogspot.com/2012/02/comparable-comparator.html
 *      https://www.mkyong.com/java8/java-8-lambda-comparator-example/
 *
 * @see https://www.mkyong.com/java/java-object-sorting-example-comparable-and-comparator
 *      https://javadevblog.com/primer-sortirovki-s-pomoshh-yu-java-comparable-i-comparator.html
 *
 * @see https://stackoverflow.com/questions/23365307/java-treeset-order
 *      https://stackoverflow.com/questions/4447461/in-treeset-sorting-uniqueness-of-custom-objects-based-on-different-properties
 */

public class FruitTest {

    private Fruit pineappale,
            apple,
            orange,
            banana;
    private Fruit2 pineappale2,
            apple2,
            orange2,
            banana2;

    @Before
    public void init() {
        pineappale = new Fruit(1, "Pineapple",70);
        apple = new Fruit(2,"Apple", 100);
        orange = new Fruit(3,"Orange", 80);
        banana = new Fruit(4,"Banana", 90);

        pineappale2 = new Fruit2(1, "Pineapple",70);
        apple2 = new Fruit2(2,"Apple", 100);
        orange2 = new Fruit2(3,"Orange", 80);
        banana2 = new Fruit2(4,"Banana", 90);
    }

    /**
     * java.lang.ClassCastException: com.prepare.sort.Fruit cannot be cast to java.lang.Comparable
     */
    @Test
    public void sortArrayObject() {
        Fruit[] fruits = new Fruit[4];

        fruits[0] = pineappale;
        fruits[1] = apple;
        fruits[2] = orange;
        fruits[3] = banana;

        Arrays.sort(fruits);

        for (Fruit fruit: fruits)
            System.out.format(Fruit.format, fruit.toformat());
    }

    @Test
    public void sortListObject() {
        List<Fruit> fruits = new ArrayList<>();

        fruits.add(pineappale);
        fruits.add(apple);
        fruits.add(orange);
        fruits.add(banana);

//        Collections.sort(fruits);
        Collections.sort(fruits, Fruit.NameComparator);
//        Collections.sort(fruits, Fruit.QuantityComparator);

        for (Fruit fruit: fruits)
            System.out.format(Fruit.format, fruit.toformat());
    }

    /**
     * java.lang.ClassCastException: com.prepare.sort.Fruit2 cannot be cast to java.lang.Comparable
     */
    @Test
    public void sortTreeSet() {
//        Set<Fruit2> fruits = new TreeSet<>();
        Set<Fruit2> fruits = new TreeSet<>(new Comparator<Fruit2>() {
            @Override
            public int compare(Fruit2 o1, Fruit2 o2) {
                if (o2.getId() < o1.getId())
                    return 1;
                else if (o1.getId() < o2.getId())
                    return -1;
                return 0;
            }
        });

        fruits.add(pineappale2);
        fruits.add(apple2);
        fruits.add(orange2);
        fruits.add(banana2);

        for (Fruit2 fruit: fruits)
            System.out.format(Fruit.format, fruit.toformat());
    }

    @Test
    public void sortTreeSetObject() {
//        Set<Fruit2> fruits = new TreeSet<>();
        Set<Fruit2> fruits = new TreeSet<>(Fruit2.NameComparator); //SortedSet<Fruit2> fruits = new TreeSet<>(Fruit2.NameComparator);

        fruits.add(pineappale2);
        fruits.add(apple2);
        fruits.add(orange2);
        fruits.add(banana2);

        for (Fruit2 fruit: fruits)
            System.out.format(Fruit.format, fruit.toformat());
    }
}
