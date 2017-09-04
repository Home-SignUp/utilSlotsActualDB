package com.prepare.sort;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @see https://www.mkyong.com/java/java-object-sorting-example-comparable-and-comparator
 *      https://javadevblog.com/primer-sortirovki-s-pomoshh-yu-java-comparable-i-comparator.html
 *      *********************************************************************************
 */

public class FruitTest {

    private Fruit pineappale,
            apple,
            orange,
            banana;

    @Before
    public void init() {
        pineappale = new Fruit(1, "Pineapple",70);
        apple = new Fruit(2,"Apple", 100);
        orange = new Fruit(3,"Orange", 80);
        banana = new Fruit(4,"Banana", 90);
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

}
