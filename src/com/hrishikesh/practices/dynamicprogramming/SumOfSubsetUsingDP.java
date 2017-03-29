package com.hrishikesh.practices.dynamicprogramming;

import java.util.Arrays;

import static com.hrishikesh.practices.dynamicprogramming.SumOfSubsetUsingDP.isSumExists;

/**
 * Problem:
 * Sum of subset using dynamic programming
 * Given a set (i.e. an array) of n distinct positive numbers, find a subset whose sum of elements is m.
 * <code>SubsetSumUsingBacktracking</code>
 * ;
 * ;
 * Algorithm:
 * - Create for dp table bottom to up approach of size (array.length + 1) X (sum + 1)
 * - Set true for all value for columns zero (0) because sum zero can be create from any set
 * - Set false for all value for row one (1) because sum zero can be created by any set except zero.
 * - Populate dp tale with following configuration
 * - - if col < array[row-1] then
 * - - - table [row][col] = table [row-1][col] (i.e without taking current element)
 * - - else
 * - - - table [row]col] =  table [row-1][col] || table[row-1] [col - array[row - 1] ] [
 * (i.e. without taking current element or with taking current element)
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/sum-subset-using-dynamic-programming/
 */
public class SumOfSubsetUsingDP {

    public static boolean isSumExists(int[] array, int sum) {

        /** Table to hold dp values **/
        boolean[][] table = new boolean[array.length + 1][sum + 1];

        /** Base case to get sum 0, with any set **/
        for (int i = 0; i < array.length + 1; i++) {
            table[i][0] = true;
        }

        /** Base case to get sum with empty set **/
        for (int i = 1; i < sum + 1; i++) {
            table[0][i] = false;
        }

        for (int i = 1; i < array.length + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {

                if (j < array[i - 1]) {
                    boolean withoutTakingCurrentElement = table[i - 1][j];
                    table[i][j] = withoutTakingCurrentElement;

                } else {
                    boolean withoutTakingCurrentElement = table[i - 1][j];
                    boolean withTakingCurrentElement = table[i - 1][j - array[i - 1]];

                    table[i][j] = withoutTakingCurrentElement || withTakingCurrentElement;
                }
            }
        }

        return table[array.length][sum];
    }
}


class SumOfSubsetUsingDPTest {
    public static void main(String[] args) {
        int[] array = {8, 3, 2, 10, 7};
        System.out.println("Array : " + Arrays.toString(array));
        System.out.println("Sum 5 ? " + isSumExists(array, 5));
        System.out.println("Sum 12 ? " + isSumExists(array, 12));
        System.out.println("Sum 25 ? " + isSumExists(array, 25));
        System.out.println("Sum 27 ? " + isSumExists(array, 27));
        System.out.println("Sum 50 ? " + isSumExists(array, 50));
        System.out.println("Sum 20 ? " + isSumExists(array, 20));
    }
}

