package com.prepare;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @see http://www.parshinpn.pro/content/voprosy-i-otvety-na-sobesedovanii-po-teme-java-collection-framework-chast-3
 *      ************************************************************************************************************
 *      Простой пример реализации кэша с очисткой старых значений при превышении указанного порога
 *
 * Стоит заметить, что LinkedHashMap не позволяет полностью реализовать LRU-алгоритм,
 * поскольку при вставке уже имеющегося в коллекции элемента порядок итерации не меняется.
 */

public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private static final int MAX_ENTRIES = 10;

    public LRUCache(int initialCapacity) {
        // Изменяем порядок итерации на access order.
        super(initialCapacity, 0.85f, true);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > MAX_ENTRIES;
    }

}
