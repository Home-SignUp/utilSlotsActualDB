package com.generic;

/**
 * Ограничения обобщённого / универсального параметра (Bounded type parameters/argument)
 *
 * Верхняя граница ограничения (Upper bound)
 * *****************************************
 * Чтобы объявить ограниченный параметр типа нужно после имени параметра указать ключевое слово 'extends' а затем указать верхнюю границу (Upper bound)
 * С помощью выражения  <T extends IAccount>  — указываем что используемый тип 'T' должен представлять интерфейс (или класс)
 *
 * @see https://metanit.com/java/tutorial/3.11.php
 */

public class Bank2<T extends IAccount> {
    T[] accounts;

    public Bank2(T[] accounts){
        this.accounts = accounts;
    }

    // вывод информации о всех акаунтах
    public void accountsInfo() {
        for (IAccount account: accounts){
            System.out.println(account.getId());
        }
    }
}


interface IAccount {
    int getId();
}

class Account implements IAccount {
    private int _id; // номер счета

    public Account(int id){
        this._id = id;
    }

    @Override
    public int getId() {
        return _id;
    }
}
