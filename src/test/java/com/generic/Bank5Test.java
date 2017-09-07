package com.generic;

import org.junit.Test;

public class Bank5Test {

    @Test
    public void test() {
        Bank5<Account, Integer> bank
//                = new Bank5(new Account(21), new Integer(100));
                = new Bank5(new Account(21), new Double(100.5));
        bank.accountInfo();
    }

}
