package com.java;

import org.junit.Test;

import java.util.Arrays;

/*
Before you begin
There are 3 tasks in the test. You can solve them in any order.
There's no option to pause. Make sure you will not be interrupted for 90 minutes.
Do not use any ready-made solutions. Cheating is easy for us to discover.
Play the game. Read our Code of Honour.
Your solutions should consider all possible corner cases and handle large input efficiently. Passing the example test does not indicate that your solution is correct. The example test is not part of your final score.
If you accidentally close your browser, use the invitation link to get back to your test.
Hint: you can use your own IDE and use copy-paste, but make sure your solution compiles in Codility's environment.
You can write your solutions in C, C++, C#, Go, Java, JavaScript, Lua, Objective-C, Pascal, Perl, PHP, Python, Ruby, Scala, Swift 2, Swift 3 or VB.NET*.
 */

public class MyHeritageTest {

/*
This is a demo task.

Write a function:

class Solution { public int solution(int[] A); }

that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.

For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

Given A = [1, 2, 3], the function should return 4.

Given A = [−1, −3], the function should return 1.

Assume that:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000..1,000,000].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 */
    @Test
    public void test1() {
        int[] A = {1, 3, 6, 4, 1, 2}; // 5
        System.out.println( solution(A) );

        A = new int[]{1, 2, 3}; // 4
        System.out.println( solution(A) );

        A = new int[]{-1, -3}; // 1
        System.out.println( solution(A) );
    }

    public int solution(int[] A) {
        int smallestInteger = 0,
                nextExpectedInteger = 1;

        Arrays.sort(A);
        for (int a: A) {
            if (nextExpectedInteger<a) {
                smallestInteger = a - 1;
                nextExpectedInteger = a + 1;
            } else if (nextExpectedInteger==a)
                nextExpectedInteger++;
        }

        return smallestInteger == 0
                ? nextExpectedInteger
                : smallestInteger;
    }
}
