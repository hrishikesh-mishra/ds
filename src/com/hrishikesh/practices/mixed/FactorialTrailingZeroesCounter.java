package com.hrishikesh.practices.mixed;

import static com.hrishikesh.practices.mixed.FactorialTrailingZeroesCounter.count;

/**
 * Problem:
 * For a given number n count trailing zeroes of n!.
 * ;
 * ;
 * Fact:
 * - Zero comes with 10 and
 * - 10 comes with multiplication of 2 and 5 multiples.
 * - And 2's will always be higher than 5 multiple in n!
 * - so, count only number of 5.
 * ;
 * ;
 * Algorithm:
 * - Iterate number from 5 to n
 * - - Counter number for 5 occurred
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/given-number-n-count-trailing-zeroes-n/
 */
public class FactorialTrailingZeroesCounter {

    public static int count(int n) {
        int fiveCounter = 0;

        for (int i = 5; i <= n; i++) {
            fiveCounter += count5(i);
        }

        return fiveCounter;
    }

    public static int count5(int n) {
        int count = 0;
        while (n % 5 == 0) {
            count++;
            n = n / 5;
        }
        return count;
    }
}

class FactorialTrailingZeroesCounterTest {

    public static void main(String[] args) {
        System.out.println("Trailing zeroes 2! :" + count(2));
        System.out.println("Trailing zeroes 5! :" + count(5));
        System.out.println("Trailing zeroes 6! :" + count(6));
        System.out.println("Trailing zeroes 10! :" + count(10));
        System.out.println("Trailing zeroes 15! :" + count(15));
        System.out.println("Trailing zeroes 25! :" + count(25));
    }
}
