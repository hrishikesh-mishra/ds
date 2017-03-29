package com.hrishikesh.practices.dynamicprogramming;

import static com.hrishikesh.practices.dynamicprogramming.StaircaseProblem.countSteps;

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

    public static int countSteps(int n) {
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

}


class StaircaseProblemTest {
    public static void main(String[] args) {

        System.out.println("When n == 1 then, count = " + countSteps(1));
        System.out.println("When n == 2 then, count = " + countSteps(2));
        System.out.println("When n == 3 then, count = " + countSteps(3));
        System.out.println("When n == 4 then, count = " + countSteps(4));
        System.out.println("When n == 5 then, count = " + countSteps(5));
        System.out.println("When n == 6 then, count = " + countSteps(6));
        System.out.println("When n == 7 then, count = " + countSteps(7));


    }
}