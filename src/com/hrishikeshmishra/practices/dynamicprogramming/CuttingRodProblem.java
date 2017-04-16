package com.hrishikeshmishra.practices.dynamicprogramming;

import static com.hrishikeshmishra.practices.dynamicprogramming.CuttingRodProblem.solveByDP;
import static com.hrishikeshmishra.practices.dynamicprogramming.CuttingRodProblem.solveByRecursion;

/**
 * Problem:
 * Cutting A Rod Problem
 * What is a best way to cut a rod of length of n meter into smaller pieces in to maximize the revenue.
 * ;
 * ;
 * Formula:
 * <code>Solve(n) = Max [price(n) + Solve(n-i-1) for all i in {0, 1, .... n-1}] </code>
 * ;
 * ;
 * Dynamic Programming Algorithm:
 * - Create DP table of size = n +1
 * - Set table[0] = 0
 * - Iterate i from 1 to n
 * - - Set max = INFINITY
 * - - Iterate j from 0 to 1
 * - - - Set max = Max {max, price [i] + table[i-j-1] }
 * - - Set table[i] = max
 * - Return table[n]
 *
 * @author hrishikesh.mishra
 * @video https://www.youtube.com/watch?v=IRwVmTmN6go
 * @video https://www.youtube.com/watch?v=h_x9rvRji90
 * @link http://hrishikeshmishra.com/cutting-rod-problem-2/
 */
public class CuttingRodProblem {

    public static int solveByRecursion(int[] array, int n) {

        /** Base case : When n = 0 **/
        if (n <= 0) {
            return 0;
        }

        int maxValue = Integer.MIN_VALUE;

        /** Try for all possible size for max value **/
        for (int i = 0; i < n; i++) {
            maxValue = Math.max(maxValue, array[i] + solveByRecursion(array, n - i - 1));
        }

        return maxValue;
    }

    public static int solveByDP(int[] prices, int n) {
        int[] table = new int[n + 1];
        table[0] = 0;

        for (int i = 1; i < n + 1; i++) {
            int maxValue = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                maxValue = Math.max(maxValue, prices[j] + table[i - j - 1]);
            }

            table[i] = maxValue;
        }

        return table[n];
    }
}

class CuttingRodProblemTest {

    public static void main(String[] args) {
        int[] array = {1, 5, 8, 9, 10, 17, 17, 20};
        print(array);

        System.out.println("\nMax Value (n=8, recursive) : " + solveByRecursion(array, 8));
        System.out.println("Max Value (n=8, DP) : " + solveByDP(array, 8));
    }


    private static void print(int[] array) {
        System.out.print("\nLength: ");
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%5d", i + 1);
        }

        System.out.print("\nPrice:  ");
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%5d", array[i]);
        }

    }
}