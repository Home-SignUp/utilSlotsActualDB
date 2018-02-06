package com.java;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class MyHeritageTest {

    @Test
    public void test0() {
        int[] A = {1, 3, 6, 4, 1, 2}; // 5
        System.out.println( solution0(A) );

        A = new int[]{1, 2, 3}; // 4
        System.out.println( solution0(A) );

        A = new int[]{-1, -3}; // 1
        System.out.println( solution0(A) );

        //////////////////////////////
        List<Integer> intList = null;

        intList.stream().mapToInt(i->i).toArray();            // конвертировать:  List<Integer> -> int[]
        IntStream.of(A).boxed().collect(Collectors.toList()); // конвертировать:  int[] -> List<Integer>
    }

    /**
    This is a demo task. Write a function:

    class Solution { public int solution(int[] A); }

    that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.
    For example:
     Given A = [1, 3, 6, 4, 1, 2], the function should return 5.
     Given A = [1, 2, 3], the function should return 4.
     Given A = [−1, −3], the function should return 1.

    Assume that:
     N is an integer within the range [1..100,000];
     each element of array A is an integer within the range [−1,000,000..1,000,000].

    Complexity:
     expected worst-case time complexity is O(N);
     expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
    */
    public int solution0(int[] A) {
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

    /**
     * Write a function
     * class Solution { public int[] solution(int N); }
     * that, given an integer N (1 ≤ N ≤ 100), returns an array containing N distinct integers that sum up to 0. The function can return any such array.
     * For example, given N = 4, the function could return [1, 0, −3, 2], and for N = 3 one of the possible answers is [−1, 0, 1] (but there are many more correct answers).
     */
    // https://www.pluralsight.com/guides/java-and-j2ee/java-8-stream-api-part-2
    // http://www.deadcoderising.com/2015-05-19-java-8-replace-traditional-for-loops-with-intstreams
    // https://stackoverflow.com/questions/960431/how-to-convert-listinteger-to-int-in-java
    @Test
    public void test1() {
//        UUID uuid = UUID.randomUUID();
//        System.out.println("Random UUID String = " + uuid.toString());
//        System.out.println("UUID version       = " + uuid.version());
//        System.out.println("UUID variant       = " + uuid.variant());
//
////        for (int idx=0; idx<10; ++idx){
////            UID userId = new UID();
////            System.out.println("User Id: " + userId);
////        }

//        IntStream.generate(() -> ThreadLocalRandom.current().nextInt(10))
//                .limit(10)
//                .forEach(System.out::println);

        for (int s: solution1(10)) System.out.print(s + " ");
    }

    /*
    Compilation successful.

    Example test:    4
    WRONG ANSWER  (result array sums up to -2
    Output: [1, -1, -2, 0])

    Example test:    3
    WRONG ANSWER  (result array sums up to -3
    Output: [0, -2, -1])
     */
    private int[] solution1(int N) {
        int num1 = (N/2) - N;
        int num2 = num1 + N;
        List<Integer> intList = IntStream.range(num1, num2).boxed().collect(Collectors.toList());

        Collections.shuffle(intList);
        return intList.stream().mapToInt(i->i).toArray();
    }

    /**
     * A zero-indexed array A consisting of N integers is given. We are looking for pairs of elements of the array that are equal but that occupy different positions in the array. More formally, a pair of indices (P, Q) is called identical
     *  if 0 ≤ P < Q < N and A[P] = A[Q]. The goal is to calculate the number of identical pairs of indices.
     * For example, consider array A such that:
     *   A[0] = 3
     *   A[1] = 5
     *   A[2] = 6
     *   A[3] = 3
     *   A[4] = 3
     *   A[5] = 5
     *
     * There are four pairs of identical indices: (0, 3), (0, 4), (1, 5) and (3, 4). Note that pairs (2, 2) and (5, 1) are not counted since their first indices are not smaller than their second.
     * Write a function:
     *   class Solution { public int solution(int[] A); }
     * that, given a zero-indexed array A of N integers, returns the number of identical pairs of indices.
     * If the number of identical pairs of indices is greater than 1,000,000,000, the function should return 1,000,000,000.
     * For example, given:
     *   A[0] = 3
     *   A[1] = 5
     *   A[2] = 6
     *   A[3] = 3
     *   A[4] = 3
     *   A[5] = 5
     * the function should return 4, as explained above.
     * Assume that:
     * - N is an integer within the range [0..100,000];
     * - each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
     * Complexity:
     * - expected worst-case time complexity is O(N*log(N));
     * - expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
     */
    // http://www.techiedelight.com/check-duplicates-array-java
    // http://www.codermag.net/2016/05/remove-duplicates-in-java-8-using-stream-distinct.html
    // https://stackoverflow.com/questions/1073919/how-to-convert-int-into-listinteger-in-java
    @Test
    public void test2() {
        int[] A = {3, 5, 6, 3, 3, 5};
//        int[] A = {67, 8, 4, 21, 25, 64, 76, 52, 4, 21, 21};
        System.out.print( solution2(A) );
    }

    public long solution2(int[] A) {
//        List<Integer> a = Arrays.stream(A).boxed().collect(Collectors.toList());
        List<Integer> a = IntStream.of(A).boxed().collect(Collectors.toList());

        Stream<Integer> filter = a.stream().distinct();
        long duplicates = (a.size() - filter.count());

        return duplicates<1000000000
                ? duplicates
                : 1000000000;
    }

    /**
     * Alexander and Thomas share a common passion for movies, but unfortunately they do not really like each other. A movie festival is taking place in another town, and both of them want to participate in it. The problem is that neither of them is willing to meet the other during the event.
     * After some not very pleasant negotiations, they agree that the fairest way to fulfill both goals is to pick two intervals of time so as to maximise the total number of movies that they can both see on their own. Keeping in mind that Alexander and Thomas each want to spend several days in a row at the festival (as the town in which the event is taking place is not close by, so they do not want to travel there more than once), help them count the maximal number of movies that can be seen by both of them independently.
     * Write a function:
     *   class Solution { public int solution(int[] movies, int K, int L); }
     * that, given an array movies  consisting of N integers that specifies the number of movies on each day of the festival, and integers K and L that specify the number of days that Alexander and Thomas, respectively, want to spend at the festival, returns the maximal number of movies that can be seen by both of them, or −1 if a solution is not possible.
     * For example, given movies  = [6, 1, 4, 6, 3, 2, 7, 4], K = 3, L = 2, your function should return 24, because Alexander can participate in the festival from the third day and see 4 + 6 + 3 = 13 movies, and Thomas can participate from the seventh day and see 7 + 4 = 11 movies. Thus, they will see a combined total of 13 + 11 = 24 movies, and that is the maximal number that can be achieved.
     * Given movies  = [10, 19, 15], K = 2, L = 2, your function should return −1, because it is not possible for Alexander and Thomas to participate in the event in such a way as to avoid meeting each other.
     * Assume that:
     * - N is an integer within the range [2..];
     * - K and L are integers within the range [1..N − 1];
     * - each element of array movies is an integer within the range [1..500].
     * In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
     */
    @Test
    public void test3() {
        int[] movies = {6, 1, 4, 6, 3, 2, 7, 4};
        System.out.print( solution3(movies, 3, 7) );
    }

    public int solution3(int[] movies, int K, int L) {
        Set<Integer> m = IntStream.of(movies).boxed().collect(Collectors.toSet());

        return m.stream()
                .filter(i -> K<=i)
                .mapToInt(i->i)
                .sum();
    }

}
