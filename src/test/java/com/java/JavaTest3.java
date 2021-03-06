package com.java;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class JavaTest3 {

    /**
     * @see http://www.techiedelight.com/convert-map-array-java
     * @see https://stackoverflow.com/questions/1026723/how-to-convert-a-map-to-list-in-java
     */
    @Test
    public void test1() {
        ////////////////////////////////////////
        Map<String, String> map = new HashMap<>();
        map.put("Hello", "World");
        map.put("Apple", "3.14");
        map.put("Another", "Element");

        // Java 7
        List<String> list = new ArrayList<>(map.keySet());
        System.out.println(list);

        // Java 8
        List<String> values = map.values().stream()
                .collect(Collectors.toList());
        System.out.println(values);

        String[] arr = map.values().toArray(new String[0]);
        for (String a: arr) System.out.print(a + ", ");

        String[][] arr2 = { map.keySet().toArray(new String[0]), map.values().toArray(new String[0]) };
        for (String[] a2: arr2) System.out.print(a2[0] + "..." + a2[1] + ",  ");

        System.out.println();
        System.out.println();
        ////////////////////////////////////////
        Map<MyUser, String>users = new TreeMap<>(new Comparator<MyUser>() {
            @Override
            public int compare(MyUser o1, MyUser o2) {
                if (o2.getAge()<o1.getAge()) return 1;
                if (o1.getAge()<o2.getAge()) return -1;
                return 0;
            }
        });
        users.put(new MyUser(13, "Aaa"), "aaa");
        users.put(new MyUser(11, "Bbb"), "bbb");
        users.put(new MyUser(12, "Ccc"), "ccc");

        System.out.println(users);
    }

    /**
     * @see https://www.mkyong.com/java/how-to-convert-list-to-set-arraylist-to-hastset
     */
    @Test
    public void test2() {
        ////////////////////////////////////////
        List<Integer> rolles = Arrays.asList(new Integer[]{0,1,2,3,4,5,6,7,8,9,0,});

        System.out.println(rolles);

        Collections.shuffle(rolles);
//        rolles.remove(0); // java.lang.UnsupportedOperationException
        System.out.println(rolles);

        List<Integer> newRolles = rolles.subList(0,6);
        System.out.println(newRolles);

//        Collections.shuffle(rolles);
//        rolles.remove(0);
//        System.out.println(rolles);
//
//        Collections.shuffle(rolles);
//        rolles.remove(0);
//        System.out.println(rolles);

        Set<Integer> set = new HashSet<>(rolles);
        System.out.println(set);
    }

    @Test
    public void testRandom() {
        List<?> rolles = Arrays.asList(new Integer[]{0,1,2,3,4,5,6,7,8,9,0,0,1,2,3,4,5,});

        System.out.println( getRandom(new HashSet<>(rolles)) );
    }

    private List<?> getRandom(Set<?> set) {
//        List<Integer> collection = (List) set; // java.lang.ClassCastException: java.util.HashSet cannot be cast to java.util.List
        List<?> collection = new ArrayList<>(set);
        Collections.shuffle(collection);
        return collection.subList(0,6);
    }

    class MyUser {
        private int age;
        private String name;

        public MyUser(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "MyUser{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }



    interface A {}

    interface AA {
        int a1 = 0; // для интерфейса все поля являются static finul (по умолчанию)
        static int a2 = 0;
        int a();
//        static int b(); // НЕЛЬЗЯ делать интерфейсный метод static
    }

    interface AAA {
        default int a() {
            return 0;
        }
    }

    static interface AAAA {} // МОЖНО делать интерфейс static
//    final interface AAAAA {} // НЕЛЬЗЯ делать интерфейс final

//    // поля внутри интерфейса являются final
//    interface AAAAAA {
//        int a;
//
//        class X {
//            public X() {
//                a = 100;
//            }
//        }
//    }

    abstract class B {}

    abstract class BB {
        abstract int a();
    }

    abstract class BBB {
        int a() {
            return 0;
        }
    }

    abstract class BBBB {
        int a1; // МОЖНО делать обычное поле (для абстрактного класса)
        final int a2 = 0; // МОЖНО делать поле final (для абстрактного класса)
//        static int a3; // НЕЛЬЗЯ делать поле static (для абстрактного класса)
//        static abstract int a(); // НЕЛЬЗЯ делать абстрактный метод static (для абстрактного класса)
//        static int b() { // и НЕЛЬЗЯ делать обычный метод static (для абстрактного класса)
//            return 0;
//        }
    }
}
