package com.prepare.sort;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Само-сортирующиеся структуры...
 */

//public class Fruit2 {
public class Fruit2 implements Comparable<Fruit2> {

    private int id;
    private String name;
    private int quantity;

    public Fruit2(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static final String format = "%1$-8s %2$-20s %3$-10s\n";
    public String[] toformat() {
        return new String[]{
                "id:" + id,
                "name:'" + name + "'",
                "quantity:" + quantity
        };
    }

    @Override
    public int compareTo(Fruit2 o) {
        if (o.getId() < id)
            return 1;
        else if (id < o.getId())
            return -1;
        return 0;
    }

    /**
     * Дополнительные способы сортировки можно устанавливать с помощью интерфейса 'Comparator'!
     */
    public static final Comparator<Fruit2> QuantityComparator = new Comparator<Fruit2>() {
        @Override
        public int compare(Fruit2 o1, Fruit2 o2) {
            if (o1.quantity > o2.getQuantity())
                return 1;
            else if (o1.quantity < o2.quantity)
                return -1;
            return 0;
        }
    };

    public static final Comparator<Fruit2> NameComparator = new Comparator<Fruit2>() {
        @Override
        public int compare(Fruit2 o1, Fruit2 o2) {
            return o1.getName().compareTo(o2.getName()); // ascending order
//            return o2.getName().compareTo(o1.getName()); // descending order
        }
    };
//    public final SortedSet<Fruit2> NameComparator = new TreeSet<>();
}
