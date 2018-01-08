package com.prepare.luxoft;

public class Test1 {

    public static void main(String[] args) {
        //////////////////////////
        MyA.func();
        MyB.func();

        System.out.println();
        //////////////////////////
        MyA myA = new MyA();
        MyB myB = new MyB();
        MyA myX = new MyB();

        myA.func();
        myB.func();
        myX.func();

        System.out.println();
        //////////////////////////
        myA = null;
        myB = null;

        myA.func();
        myB.func();
    }


    /**
     * Статические классы вместе со статическими методами МОЖНО НАСЛЕДОВАТЬ!
     * При этом использование операторов 'new' и 'null' НЕбудет играть никакой роли...потому-что оператор 'static' уже создаст единственный экземпляр для этого приложения И все зависит только от объявленного типа переменной-класса
     */
    public static class MyA {
        public static void func() {
            System.out.println("MyA");
        }
    }

    public static class MyB extends MyA {
        public static void func() {
            System.out.println("MyB");
        }
    }
}
