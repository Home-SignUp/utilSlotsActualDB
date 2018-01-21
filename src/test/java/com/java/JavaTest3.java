package com.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JavaTest3 {

    /**
     * @see http://www.techiedelight.com/convert-map-array-java
     * @see https://stackoverflow.com/questions/1026723/how-to-convert-a-map-to-list-in-java
     */
    @Test
    public void test1() {
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
    }

}
