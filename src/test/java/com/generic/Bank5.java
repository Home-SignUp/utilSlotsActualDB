package com.generic;

/**
 * Можно указать несколько верхних границ
 * **************************************
 * перечисляя их через символ '&'
 * если в списке верхних границ есть класс то он обязательно должен идти первым
 *
 * @see https://urvanov.ru/2016/04/28/java-8-обобщения
 */
//public class Bank5<T extends Integer & IAccount> {
//
//}

/**
 * Использование нескольких универсальных параметров
 */

public class Bank5<T extends IAccount, S extends Number> {
    private T account;
    private S sum;

    public Bank5(T account, S sum){
        this.account = account;
        this.sum = sum;
    }

    public void accountInfo() {
        System.out.printf("Клиент №%s вывел — %s рублей \n", account.getId(), sum);
    }
}
