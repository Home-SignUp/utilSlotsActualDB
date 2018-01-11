package com.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ExtendTest {

    @Test
    public void test() {
        ////////////////////////////////////
        /*
         * Java работает только на ПОВЫШЕНИЯ ТИПОВ (явное-приведение типов)!
         */
        A a1 = new B("B1",1);
        System.out.println("a1: " + a1);

//        B b2 = (B) new A("A2"); // checked-проверяемое исключение java.lang.ClassCastException //здесь нужно явное приведение типов
//        System.out.println("b2: " + b2);
        ////////////////////////////////////
        /*
         * Java работает только на ПОВЫШЕНИЯ ТИПОВ (явное-приведение типов)!
         */
        A aa = new A("A3");
        B bb = new B("B3",3);

        aa = bb;
        System.out.println("aa: " + aa);

////        bb = (B) aa; // checked-проверяемое исключение java.lang.ClassCastException // если из меньшего в больший  - это явное приведение типов (КОГДА В ПОСЛЕДНЕМ КЛАССЕ ПОЛЕЙ ПОЛЕЙ НЕХВАТАЕТ)
//        bb = new B();
//        bb.name = aa.name;
//        System.out.println("bb: " + bb);
        System.out.println();
        ////////////////////////////////////
        /*
         * Для коллекций приведение типов работает на ПОВЫШЕНИЯ и ПОНИЖЕНИЕ ТИПОВ!
         * (Но для этого НУЖНО указывать ТИП КОЛЛЕКЦИИ, можно указывать общий интерфейс)
         */
        List<A> aList = new ArrayList<>();
        aList.add(new A("A11"));
        aList.add(new A("A12"));
        System.out.println("aList:  " + aList);
        List<B> bList = new ArrayList<>();
        bList.add(new B("B11",11));
        bList.add(new B("B12",12));
        System.out.println("bList:  " + bList);

//        List<A> aList1 = (ArrayList) bList;
        List<A> aList1 = (List) bList;
        System.out.println("aList1: " + aList1);

//        List<B> bList1 = (ArrayList) aList;
        List<B> bList1 = (List) aList;
        System.out.println("bList1: " + bList1);
        System.out.println();
        ////////////////////////////////////
        /*
         * Для коллекций приведение типов работает на ПОВЫШЕНИЯ и ПОНИЖЕНИЕ ТИПОВ!
         * (Но для этого НУЖНО указывать ТИП КОЛЛЕКЦИИ, можно указывать общий интерфейс)
         */
        List<A> aaList = new ArrayList<>();
        aaList.add(new A("A11"));
        aaList.add(new A("A12"));
        System.out.println("aaList: " + aaList);
        List<B> bbList = new LinkedList<>();
        bbList.add(new B("B11",11));
        bbList.add(new B("B12",12));
        System.out.println("bbList: " + bbList);

//        List<A> aaList1 = (LinkedList) bbList;
//        List<A> aaList1 = (ArrayList) bbList; // java.lang.ClassCastException
        List<A> aaList1 = (List) bbList;
        System.out.println("aaList1: " + aaList1);

//        List<B> bbList1 = (ArrayList) aaList;
//        List<B> bbList1 = (LinkedList) aaList; // java.lang.ClassCastException
        List<B> bbList1 = (List) aaList;

        System.out.println("bbList1: " + bbList1);
        System.out.println();
    }


    class A {
        String name;
        A(){}
        A(String name){ this.name = name; }
        public String toString(){ return "A{name=" + name + "}"; }
    }

    class B extends A {
        String name;
        int ego;
        B(){}
        B(String name, int ego){ this.name = name; this.ego = ego; }
        public String toString(){ return "B{name=" + name + ", ego=" + ego +"}"; }
    }

    class C {
        int ego;

        public int hashCode() { return ego; }

        public boolean equals(Object o) {
            if (this == o) return true; // Рефлексивность: сравнение объекта самого-себя
            // Симметричность: если два объкекты 'A' и 'B' равны - тогда при обратном условии сравнения (либо 'A==B' либо 'B==A') гарантируется их равенство
            // Транзитивность: три объекта 'A','B','C'; при двух условиях равенства объектов если 'A==B' и 'B==C' - тогда гарантируется равенство между 'A==C'
            if (o == null || getClass() != o.getClass()) return false;

            C c = (C) o;

            return ego == c.ego;
        }
    }
}
