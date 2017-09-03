package com.prepare;

import org.junit.Test;


/**
 * @see http://www.parshinpn.pro/content/voprosy-i-otvety-na-sobesedovanii-po-teme-java-collection-framework-chast-3
 *      ************************************************************************************************************
 * Стоит заметить, что LinkedHashMap не позволяет полностью реализовать LRU-алгоритм,
 * поскольку при вставке уже имеющегося в коллекции элемента порядок итерации не меняется.
 */

public class LRUCacheTest {

    @Test
    public void test() {
        LRUCache<String, Integer> cache = new LRUCache<>(100);

        cache.put("Sasha", 1978);
        cache.put("Yana", 1977);
        cache.put("Jenya", 1998);
        cache.put("Alesya", 2012);

        System.out.println( "Jenya = " + cache.get("Jenya") );
    }

}
