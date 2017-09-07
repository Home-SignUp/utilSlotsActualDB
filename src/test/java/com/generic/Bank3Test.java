package com.generic;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Подстановки — если захотим определить метод который бы принимал параметр вышеопределенного класса и который был бы типизирован определенными типами
 * Суть подстановок заключается в использовании символа '?' вместо определения типа (как и универсальный параметр, подстановка может передавать любой тип)
 *
 * Ограничение снизу (Lower bounded wildcard)
 * ******************************************
 * Суть заключается в том что необходимо реализовывать метод не только для Т но и для его родительских типов
 * Пример использования: метод нахождения максимума в коллекции  <T extends Comparable<? super T>> T max(Collection<T> c){ … }
 *
 * @see http://java-online.ru/java-generic.xhtml
 */

public class Bank3Test {

//    <T extends Comparable<T>> T max(Collection<T> collection) { // Ошибка
    <T extends Comparable<? super T>> T max(Collection<T> collection) { // теперь можно заполнить List<Integer>, List<Number> или List<Object>
        T c = null;
        for (T t: collection){
            c = t;
        }
        return c;
    }

    @Test
    public void test() {
        List<Integer> iList = new ArrayList<Integer>();
        iList.add(1);
        iList.add(2);
        iList.add(3);
        Integer i = max(iList);
        System.out.println("max(i) = " + i);

        List<Bank3> banks = new ArrayList<Bank3>();
        banks.add(new Bank3(1));
        banks.add(new Bank3(2));
        banks.add(new Bank3(3));
        Bank3 bank = max(banks); // (Ошибка)
        System.out.println("max(bank) = " + bank);
    }

    class Bank3 implements Comparable<Object> {
        private int _id;

        public Bank3(int id) {
            this._id = id;
        }

        public int getId(){
            return _id;
        }

        @Override
        public int compareTo(Object o) {
            if(((Bank3)o).getId()<getId())
                return 1;
            else if(getId()<((Bank3)o).getId())
                return -1;
            return 0;
        }

        @Override
        public String toString() {
            return "{id:" + _id + '}';
        }
    }
}
