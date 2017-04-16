package com.hrishikeshmishra.practices.queue;


import java.util.LinkedList;

import static com.hrishikeshmishra.practices.queue.KthMultipleNumberOfThreeFiveAndSeven.getKthMultiple;

/**
 * Problem:
 * Kth multiple number;
 * Kth number with only prime factors of 3, 5 and 7.
 * ;
 * ;
 * Fact:
 * Kth Multiple of 3, 5 and 7 = 3^k * 5^k * 7^k
 * ;
 * ;
 * Solution:
 * - Take 3 queues
 * - - Queue-3 to hold 3 multiples
 * - - Queue-5 to hold 5 multiples
 * - - Queue-7 to hold 7 multiples
 * - Remove minimum value from all three queues
 * - If we removed from Q3 then
 * - - add Q3 <- min * 3, Q5 <- min * 5, Q7 <- min * 7
 * - If we removed from Q5 then
 * - - add Q5 <- min * 5, Q7 <- min * 7
 * - If we removed from Q7 then
 * - - add Q7 <- min * 7
 * - - We'll not add to Q3 and Q5 because it will be duplicate
 * ;
 * ;
 * Algorithm:
 * - Take three queue namely Q3, Q5 and Q7
 * - Add 1 to Q3
 * - Iterate 1 to K
 * - - Fetch minimum_value from all three Queues
 * - - If minimum_value == Q3.peek() then
 * - - - Q3.removeMin()
 * - - - Add Q3 <- minimum_value * 3, Q5 <- minimum_value * 5 and Q7 <- minimum_value * 7
 * - - If minimum_value == Q5.peek() then
 * - - - Q5.removeMin()
 * - - - Add Q5 <- minimum_value * 5 and Q7 <- minimum_value * 7
 * - - If minimum_value == Q7.peek() then
 * - - - Q7.removeMin()
 * - - - Add Q7 <- minimum_value * 7
 * - return minimum_value
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/kth-number-prime-factors-3-5-7/
 */
public class KthMultipleNumberOfThreeFiveAndSeven {

    public static int getKthMultiple(int k) {
        /** Take 3 queues one 3, 5 and 7 **/
        java.util.Queue<Integer> q3 = new LinkedList<>();
        java.util.Queue<Integer> q5 = new LinkedList<>();
        java.util.Queue<Integer> q7 = new LinkedList<>();

        q3.add(1);
        int min = Integer.MIN_VALUE;

        for (int counter = 0; counter < k; counter++) {
            int v3 = (q3.size() > 0) ? q3.peek() : Integer.MAX_VALUE;
            int v5 = (q5.size() > 0) ? q5.peek() : Integer.MAX_VALUE;
            int v7 = (q7.size() > 0) ? q7.peek() : Integer.MAX_VALUE;

            min = Math.min(v3, Math.min(v5, v7));
            if (min == v3) {
                q3.remove();
                q3.add(min * 3);
                q5.add(min * 5);
            } else if (min == v5) {
                q5.remove();
                q5.add(min * 5);
            } else {
                q7.remove();
            }

            q7.add(min * 7);
        }

        return min;
    }
}


class KthMultipleNumberOfThreeFiveAndSevenTest {
    public static void main(String[] args) {
        System.out.println("When k==1 then Multiple = " + getKthMultiple(1));
        System.out.println("When k==2 then Multiple = " + getKthMultiple(2));
        System.out.println("When k==3 then Multiple = " + getKthMultiple(3));
        System.out.println("When k==4 then Multiple = " + getKthMultiple(4));
        System.out.println("When k==5 then Multiple = " + getKthMultiple(5));
        System.out.println("When k==6 then Multiple = " + getKthMultiple(6));
        System.out.println("When k==7 then Multiple = " + getKthMultiple(7));
        System.out.println("When k==8 then Multiple = " + getKthMultiple(8));


    }
}
