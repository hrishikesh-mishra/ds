package com.hrishikesh.practices.array;

/**
 * Problem:
 * Count All 2s between 0 to n.
 * ;
 * ;
 * Algorithm:
 * - Iterate from 2 to n
 * - - for each number count number 2s present in it
 * - - update total_2s_counter
 * - Return total_2s_counter
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/count-all-2s-between-0-to-n/
 */
public class CountAll2s {

    public static int count(int n) {
        int totalTwos = 0;
        for (int i = 2; i <= n; i++) {
            totalTwos += getTwos(i);
        }
        return totalTwos;
    }

    private static int getTwos(int i) {
        int count = 0;
        while (i > 0) {
            if (i % 10 == 2) {
                count++;
            }
            i /= 10;
        }
        return count;
    }
}


class CountAll2sTest {
    public static void main(String[] args) {
        System.out.println("All 2's till n=2: " + CountAll2s.count(2));
        System.out.println("All 2's till n=4: " + CountAll2s.count(4));
        System.out.println("All 2's till n=12: " + CountAll2s.count(12));
        System.out.println("All 2's till n=22: " + CountAll2s.count(22));
        System.out.println("All 2's till n=32: " + CountAll2s.count(32));
    }
}