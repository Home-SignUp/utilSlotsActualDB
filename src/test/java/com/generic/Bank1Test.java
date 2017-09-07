package com.generic;

import org.junit.Test;
import org.junit.Before;

public class Bank1Test {

    private Bank1<Integer> bank1;
    private Bank1<String> bank2;

    @Before
    public void init() {
        bank1 = new Bank1(new Integer[]{1, 2, 4, 5, 6 });
        bank2 = new Bank1(new String[]{"13433", "342454", "21432"});
    }
}
