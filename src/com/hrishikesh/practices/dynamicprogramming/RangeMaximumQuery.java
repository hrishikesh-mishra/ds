package com.hrishikesh.practices.dynamicprogramming;

import java.util.Arrays;

import static com.hrishikesh.practices.dynamicprogramming.RangeMaximumQuery.buildDPTable;

/**
 * Problem
 * Range Maximum Query
 *
 * @author hrishikesh.mishra
 */
public class RangeMaximumQuery {

    public static void buildDPTable(int[] array) {
        int[][] table = new int[array.length][array.length];

        for (int i = 0; i < array.length; i++) {
            table[i][i] = array[i];
            for (int j = i + 1; j < array.length; j++) {
                table[i][j] = Math.max(table[i][j - 1], array[j]);
            }
        }


        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(table[i]));
        }
    }
}

class RangeMaximumQueryTest {
    public static void main(String[] args) {
        int[] array = {4, 6, 1, 5, 7, 3};
        System.out.println("Array: " + Arrays.toString(array));

        System.out.println("DP : ");
        buildDPTable(array);


    }
}

