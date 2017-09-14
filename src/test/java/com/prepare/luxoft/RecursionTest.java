package com.prepare.luxoft;

import org.junit.Test;

public class RecursionTest {

    public static void main(String[] args) {
//        A.staticFunc(1); // статический метод создается от класса (компилятор подсказывает нормально)
//        B.staticFunc(3); // статический метод создается от класса (компилятор подсказывает нормально)
        A a = new A();
        a.staticFunc(2); // статический метод тоже создается от класса (но компилятор не подсказывает)
        B b = new B();
        b.staticFunc(4); // статический метод тоже создается от класса (но компилятор не подсказывает)
        A c = new B();
        c.staticFunc(5);
    }

    @Test
    public void test(){
        System.out.println("func(2,5) = " + func(2,5));
    }

    int func(int n, int a){
        return 0<n ? a * func(n-1, a) : a; //125
//        return 0<n ? func(n-1, a*a) : a; //625
    }
}



class A {
    /*
     * статический метод работает только для класса верхнего порядка
     * в противном случае, для вложенного класса нужно явно указывать статический класс
     */
    public static void staticFunc(int arg){ //public void staticFunc(int arg){
        System.out.println("A."+arg);
    }
}

class B extends A {
    /*
     * для каждого типа класса будет вызван свой статический метод...
     */
    //@Override // для статических методов переопределение не работает И нельзя делать переопределение для статических-нестатических методов...
    public static void staticFunc(int arg){ //public void staticFunc(int arg){
        System.out.println("B."+arg);
    }
}