package com.generic;

/**
 * Обобщённый / универсальный тип (bounded type)
 * *********************************************
 * @see https://metanit.com/java/tutorial/3.11.php
 */

public class Bank1<T> {
    private T[] _clients;

    public Bank1(T[] clients) {
        this._clients = clients;
    }
}
