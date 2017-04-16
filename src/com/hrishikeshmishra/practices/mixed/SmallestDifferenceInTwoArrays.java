package com.hrishikeshmishra.practices.mixed;

import java.util.Arrays;

import static com.hrishikeshmishra.practices.mixed.SmallestDifferenceInTwoArrays.findMinimumDifference;

/**
 * Problem:
 * - Find smallest different in given two arrays;
 * ;
 * ;
 * Algorithm:
 * - Sort both arrays
 * - Set minDiff = INFINITY
 * - Set a1Counter = 0 and a2Counter = 0
 * - Iterate till has array has not reached end
 * - - If abs(A1[a1Counter] - A2[a2Counter]) < minDiff then
 * - - - Set minDiff = abs(A1[a1Counter] - A2[a2Counter]
 * - - if A1[a1Counter] <  A2[a2Counter] then
 * - - - Set a1Counter = a1Counter + 1
 * - - else
 * - - - Set a2Counter = a2Counter + 1
 * - return minDiff
 *
 * @author hrishikesh.mishra
 * @link hrishikeshmishra.com/find-smallest-different-given-two-arrays/
 */
public class SmallestDifferenceInTwoArrays {

    public static int findMinimumDifference(int[] array1, int[] array2) {
        Arrays.sort(array1);
        Arrays.sort(array2);
        int minimumDiff = Integer.MAX_VALUE;
        int array1Counter = 0;
        int array2Counter = 0;

        while (array1Counter < array1.length && array2Counter < array2.length) {
            if (Math.abs(array1[array1Counter] - array2[array2Counter]) < minimumDiff) {
                minimumDiff = Math.abs(array1[array1Counter] - array2[array2Counter]);
            }

            if (array1[array1Counter] < array2[array2Counter]) {
                array1Counter++;
            } else {
                array2Counter++;
            }
        }

        return minimumDiff;

    }
}


class SmallestDifferenceInTwoArraysTest {
    public static void main(String[] args) {
        int[] array1 = {1, 3, 15, 11, 2};
        int[] array2 = {23, 127, 235, 19, 8};
        System.out.println("Minimum difference: " + findMinimumDifference(array1, array2));

        int[] array3 = {1, 2, 11, 15};
        int[] array4 = {23, 12, 19, 23, 127};

        System.out.println("Minimum difference: " + findMinimumDifference(array3, array4));
    }
}