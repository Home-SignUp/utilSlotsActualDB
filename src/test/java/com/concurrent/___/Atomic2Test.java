package com.concurrent.___;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @see https://baptiste-wicht.com/posts/2010/09/java-concurrency-atomic-variables.html
 *      *******************************************************************************
 * AtomicInteger
 * AtomicLong
 * AtomicBoolean
 * AtomicReference
 */

public class Atomic2Test {

    private void increment(AtomicInteger integer){
        while(true){
            int current = integer.get();
            int next = current + 1;

            if(integer.compareAndSet(current, next)){
                return;
            }
        }
    }

}


class AtomicCounter {
    private final AtomicInteger value = new AtomicInteger(0);

    public int getValue(){
        return value.get();
    }

    public int getNextValue(){
        return value.incrementAndGet();
    }

    public int getPreviousValue(){
        return value.decrementAndGet();
    }
}