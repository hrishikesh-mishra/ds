package com.hrishikeshmishra.practices.dynamicprogramming;

import java.util.Arrays;

/**
 * Problem:
 * Range Sum Query
 * How to find sum of elements from given index interval (i, j) in constant time?
 *
 * @author hrishikesh.mishra
 * @link http://stackoverflow.com/questions/2473114/how-to-find-sum-of-elements-from-given-index-interval-i-j-in-constant-time
 */
public class RangeSumQuery {

    private final int[] sumArray;

    public RangeSumQuery(int[] array) {
        sumArray = new int[array.length];
        init(array);
    }

    private void init(int[] array) {
        sumArray[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            sumArray[i] = sumArray[i - 1] + array[i];
        }
    }

    public int getSumOfItoJ(int i, int j) {
        int sumTillJ = sumArray[j];
        int sumTillI = 0;
        if (i > 0) {
            sumTillI = sumArray[i - 1];
        }

        return sumTillJ - sumTillI;
    }
}


class RangeSumQueryTest {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        RangeSumQuery rangeSumQuery = new RangeSumQuery(array);

        System.out.println("Array :" + Arrays.toString(array));
        System.out.println("Sum (0, 9): " + rangeSumQuery.getSumOfItoJ(0, 9));
        System.out.println("Sum (1, 9): " + rangeSumQuery.getSumOfItoJ(1, 9));
        System.out.println("Sum (1, 4): " + rangeSumQuery.getSumOfItoJ(1, 4));
    }
}