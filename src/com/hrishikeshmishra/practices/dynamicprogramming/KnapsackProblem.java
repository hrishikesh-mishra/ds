package com.hrishikeshmishra.practices.dynamicprogramming;

import java.util.Arrays;

import static com.hrishikeshmishra.practices.dynamicprogramming.KnapsackProblem.solveByDP;
import static com.hrishikeshmishra.practices.dynamicprogramming.KnapsackProblem.solveRecursive;

/**
 * Problem:
 * Knapsack Problem:
 * ;
 * Given a set of items, each with a weight and a value, determine the number of each item
 * to include in a collection so that the total weight is less than or equal to a given limit
 * and the total value is as large as possible.
 * ;
 * ;
 * Solution:
 * - Max between taking an item or excluding an item
 * ;
 * Recursive Formula:
 * - <code>
 * Max {
 * value[index] + solveRecursiveHelper(weight, value, capacity - weight[index], index - 1) ,
 * solveRecursiveHelper(weight, value, capacity, index - 1
 * }
 * </code>
 * ;
 * ;
 * DP Formula:
 * - If weight < weights[i - 1] then
 * - - table[i][weight] = table[i - 1][weight];
 * - Else
 * - - <code> table[i][weight] = Math.max(
 * values[i - 1] + table[i - 1][weight - weights[i - 1]],
 * table[i - 1][weight]
 * )
 * </code>
 * ;
 * ;
 * ;
 * Recursive Algorithm:
 * - If index or capacity is zero then
 * - - return 0
 * - If weights[index] > capacity then
 * - - return 0
 * - Return Max {values[index] + recursive call with   capacity - weights[index
 * or recursive call without current item) }
 * ;
 * ;
 * Dynamic Programming Algorithm:
 * - Create DP table of size (weights.length + 1) X (capacity + 1)
 * - Iterate index i 0 to weights.length + 1
 * - - Iterate index j 0 to capacity + 1
 * - - - If i or j equal to zero then
 * - - - - Set table[i][weight] = 0;
 * - - - Else If weight < weights[i - 1] then
 * - - - - Set  table[i][weight] = table[i - 1][weight]
 * - - - Else
 * - - - - table[i][weight] = Math.max( values[i - 1] + table[i - 1][weight - weights[i - 1]], table[i - 1][weight] )
 *
 * @author hrishikesh.mishra
 * @link https://en.wikipedia.org/wiki/Knapsack_problem
 * @link http://hrishikeshmishra.com/knapsack-problem/
 */
public class KnapsackProblem {

    public static int solveRecursive(int[] weights, int[] values, int capacity) {
        return solveRecursiveHelper(weights, values, capacity, weights.length - 1);
    }

    public static int solveRecursiveHelper(int[] weights, int[] values, int capacity, int index) {

        /** Base case when capacity is zero **/
        if (index == 0 || capacity == 0) {
            return 0;
        }

        /** When weight is heigher than capacity **/
        if (weights[index] > capacity) {
            return 0;
        }

        /** Get max between including item and excluding a item **/
        return Math.max(values[index] + solveRecursiveHelper(weights, values, capacity - weights[index], index - 1),
                solveRecursiveHelper(weights, values, capacity, index - 1));
    }

    public static int solveByDP(int[] weights, int[] values, int capacity) {

        /** Total number Of items **/
        int totalNumberOfItems = weights.length;

        /** DP Table to hold all best capacity for all item from 1 to n and all capacity from 1 to capacity **/
        int[][] table = new int[totalNumberOfItems + 1][capacity + 1];

        /** Iteration all items one by one **/
        for (int i = 0; i < totalNumberOfItems + 1; i++) {

            /** Checking for all weight **/
            for (int weight = 0; weight < capacity + 1; weight++) {

                /** Base case: when capacity is zero or weight is zero **/
                if (i == 0 || weight == 0) {
                    table[i][weight] = 0;
                }
                /** When capacity is less than current weight **/
                else if (weight < weights[i - 1]) {
                    table[i][weight] = table[i - 1][weight];
                }
                /** Max of taking an item or excluding that item **/
                else {
                    table[i][weight] = Math.max(
                            values[i - 1] + table[i - 1][weight - weights[i - 1]],
                            table[i - 1][weight]
                    );
                }
            }
        }
        return table[weights.length][capacity];
    }

}


class KnapsackProblemTest {
    public static void main(String[] args) {
        int capacity = 10;
        int weight[] = {5, 4, 6, 3};
        int value[] = {10, 40, 30, 50};
        System.out.println("Weights: " + Arrays.toString(weight));
        System.out.println("Values: " + Arrays.toString(value));
        System.out.println("Capacity: " + capacity);
        System.out.println("Max Value (By Recursion): " + solveRecursive(weight, value, capacity));
        System.out.println("Max Value (By DP): " + solveByDP(weight, value, capacity));

    }
}


