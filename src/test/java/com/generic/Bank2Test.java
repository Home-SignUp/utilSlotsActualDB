package com.generic;

import org.junit.Test;
import org.junit.Before;

public class Bank2Test {

    Account[] accounts;

    @Before
    public void init() {
        accounts = new Account[]{
                new Account(1857), new Account(2225), new Account(33232)
        };
    }

    @Test
    public void test() {
        Bank2<Account> bank = new Bank2(accounts);
        bank.accountsInfo();
    }

}
