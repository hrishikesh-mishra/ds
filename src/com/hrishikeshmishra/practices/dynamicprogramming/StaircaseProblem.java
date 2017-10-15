package com.hrishikeshmishra.practices.dynamicprogramming;

import static com.hrishikeshmishra.practices.dynamicprogramming.StaircaseProblem.countStepsBottomUp;
import static com.hrishikeshmishra.practices.dynamicprogramming.StaircaseProblem.countStepsTopDown;

/**
 * Problem:
 * A child going up a staircase with n steps, can hop up 1, 2, or 3 steps at a time.
 * How many ways can the child reach the top?
 * ;
 * ;
 * Solution:
 * - It very similar to Fibonacci problem
 * - So, formula  S(n) = S(n-1) + S(n-2) + S(n-3)
 * - Can be easily solved by recursion but runtime complexity could be O(3^n)
 * - We can you dynamic programming (memoization) and with this it will be O(n)
 * ;
 * ;
 * Algorithm:
 * - Base case : When n < 0 then return 0
 * - Base case: When n == 0 then return 1
 * - When n is already computed then return computed value
 * - Else compute(n) = compute(n-1) + compute(n-2) + compute(n-3)
 * - Store computed value in auxiliary memory and return the same.
 *
 * @author hrishikesh.mishra
 * @video https://www.youtube.com/watch?v=CFQk7OQO_xM
 * @quora https://www.quora.com/A-child-going-up-a-staircase-with-n-steps-can-hop-up-1-2-or-3-steps-at-a-time-How-many-ways-can-the-child-reach-the-top
 * @link http://hrishikeshmishra.com/staircase-problem/
 */
public class StaircaseProblem {

    /**
     * Top Down Approach
     *
     * @param n
     * @return
     */
    public static int countStepsTopDown(int n) {
        return countStepsWithMemoization(n, new int[n + 1]);
    }

    private static int countStepsWithMemoization(int n, int[] memo) {

        if (n < 0) {
            return 0;
        }

        if (n == 0) {
            return 1;
        }

        if (memo[n] > 0) {
            return memo[n];
        } else {
            memo[n] = countStepsWithMemoization(n - 1, memo) +
                    countStepsWithMemoization(n - 2, memo) +
                    countStepsWithMemoization(n - 3, memo);

            return memo[n];
        }
    }


    /**
     * Bottom Up Approach
     *
     * @param n
     * @return
     */
    public static int countStepsBottomUp(int n) {

        int[] dpTable = new int[n + 1];

        /**
         * for 0th floor there is one and only one way
         **/
        dpTable[0] = 1;

        for (int step = 1; step <= n; step++) {

            dpTable[step] = 0;

            for (int j = 1; j <= 3; j++) {
                if (step - j >= 0) {
                    dpTable[step] += dpTable[step - j];
                }
            }
        }

        return dpTable[n];
    }
}


class StaircaseProblemTest {
    public static void main(String[] args) {

        System.out.println("(Top Down) When n == 1 then, count = " + countStepsTopDown(1));
        System.out.println("(Bottom Up) When n == 1 then, count = " + countStepsBottomUp(1));

        System.out.println("(Top Down) When n == 2 then, count = " + countStepsTopDown(2));
        System.out.println("(Bottom Up) When n == 2 then, count = " + countStepsBottomUp(2));

        System.out.println("(Top Down) When n == 3 then, count = " + countStepsTopDown(3));
        System.out.println("(Bottom Up) When n == 3 then, count = " + countStepsBottomUp(3));

        System.out.println("(Top Down) When n == 4 then, count = " + countStepsTopDown(4));
        System.out.println("(Bottom Up) When n == 4 then, count = " + countStepsBottomUp(4));

        System.out.println("(Top Down) When n == 5 then, count = " + countStepsTopDown(5));
        System.out.println("(Bottom Up) When n == 5 then, count = " + countStepsBottomUp(5));

        System.out.println("(Top Down) When n == 6 then, count = " + countStepsTopDown(6));
        System.out.println("(Bottom Up) When n == 6 then, count = " + countStepsBottomUp(6));

        System.out.println("(Top Down) When n == 7 then, count = " + countStepsTopDown(7));
        System.out.println("(Bottom Up) When n == 7 then, count = " + countStepsBottomUp(7));


    }

}