package com.prepare.sort;

import java.util.Comparator;

/**
 * Структуры с кастомерской сортировкой...
 */

//public class Fruit {
public class Fruit implements Comparable<Fruit> {

    private int id;
    private String name;
    private int quantity;

    public Fruit(int id, String name, int quantity) {
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

    /**
     * С помощью интерфейса 'Comparable' можно устанавливать (дефолтную) сортировку по умолчанию!
     */
    @Override
    public int compareTo(Fruit fruit) {
        if (id > fruit.id)
            return 1;
        else if (id < fruit.id)
            return -1;
        return 0;
    }

    /**
     * Дополнительные способы сортировки можно устанавливать с помощью интерфейса 'Comparator'!
     */
    public static final Comparator<Fruit> QuantityComparator = new Comparator<Fruit>() {
        @Override
        public int compare(Fruit o1, Fruit o2) {
            if (o1.quantity > o2.quantity)
                return 1;
            else if (o1.quantity < o2.quantity)
                return -1;
            return 0;
        }
    };

    public static final Comparator<Fruit> NameComparator = new Comparator<Fruit>() {
        @Override
        public int compare(Fruit o1, Fruit o2) {
            String name1 = o1.getName();
            String name2 = o2.getName();

            return name1.compareTo(name2); // ascending order
//            return name2.compareTo(name1); // descending order
        }
    };
}
