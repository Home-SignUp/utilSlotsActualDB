package com.prepare.luxoft;

import java.util.*;
import org.junit.Test;
import org.junit.Before;

public class SortAlgorithmTest {

    /**
     * Задача найти все совпадающие элементы в массие самым кротчайшим путем
     */
    @Test
    public void test0() {
        String value1 = "qsc";
        String value2 = "eszs";

        int _count = 0;

        for (char val1 : value1.toCharArray()) {
            for (char val2 : value2.toCharArray()) {
                if (val1==val2){
                    System.out.println(val1 + "==" + val2);
//                    break;
                }
                _count++;
            }
            _count++;
        }

        System.out.println("\n\rcount = " + _count);

//        System.out.println("------------------------");
//
////        char[] chars = value1.toCharArray();
////        List<Character> lValue1 = Arrays.asList(chars);
//        List<Character> lValue1 = new ArrayList<>();
//        for (char c : value1.toCharArray()) {
//            lValue1.add(c);
//        }
//        List<Character> lValue2 = new ArrayList<>();
//        for (char c : value2.toCharArray()) {
//            lValue2.add(c);
//        }
//        Iterator<Character> ilValue1 = lValue1.iterator();
//        Iterator<Character> ilValue2 = lValue2.iterator();
//
//        while (ilValue1.hasNext())
//
    }

    /**
     * метод 'get(i)' для LinkedList устанавливает в позицию на середину списка...
     */
    @Test
    public void test1() {
        LinkedList<Integer> ll = new LinkedList<>();

        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(4);
        ll.add(5);
        ll.add(6);

        System.out.println("get(4) = " + ll.get(4));

        for (Integer i : ll){
            System.out.print(i + " ");
        }
    }

}
