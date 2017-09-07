package com.generic;

import org.junit.Test;

/**
 * Подстановки — если захотим определить метод который бы принимал параметр вышеопределенного класса и который был бы типизирован определенными типами
 * Суть подстановок заключается в использовании символа '?' вместо определения типа (как и универсальный параметр, подстановка может передавать любой тип)
 *
 * @see https://metanit.com/java/tutorial/3.11.php
 */

public class Operation3Test {

    private void setOperation(Operation3<? extends IAccount, ?> operation) {
        operation.getInfo();
    }

    @Test
    public void test(){
        Account account = new Account(21);
        Operation3<Account, Integer> operation = new Operation3(account, 100);
        setOperation(operation);
    }
}


class Operation3<T extends IAccount, S>{
    private T account;
    private S sum;

    public Operation3(T account, S sum){
        this.account = account;
        this.sum = sum;
    }
    void getInfo(){
        System.out.printf("Клиент №%s вывел — %s рублей \n", account.getId(), String.valueOf(sum));
    }
}
