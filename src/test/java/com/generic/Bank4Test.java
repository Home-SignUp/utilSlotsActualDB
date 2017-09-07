package com.generic;

import java.util.List;

/**
 * Ограничения обобщённого / универсального параметра (Bounded type parameters/argument)
 *
 * Обманываем компилятор (Wildcard Capture)
 * ****************************************
 * Проблема заключается в том что метод List.set() не может работать с List<?>, так как ему не известен тип List
 * Пример использования: Реализация метода swap в List<?>
 *
 * @see http://java-online.ru/java-generic.xhtml
 */

public class Bank4Test {

    void swap(List<?> list, int i, int j){
//        list.set(i, list.get(j)); // Ошибка
        swapImpl(list, i, j); // обманываем компилятор т.е. для решение этой проблемы используют 'Wildcard Capture'
    }

    <T> void swapImpl(List<T> list, int i, int j){
        list.set(i, list.get(j));
    }

}
