package com.hrishikeshmishra.practices.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.hrishikeshmishra.practices.dynamicprogramming.LongestIncreasingSubsequence.getLIS;

/**
 * Problem:
 * Longest Increasing Subsequence Using Dynamic Programming
 * ;
 * Formula:
 * - <code>array[j] < array[i] && dpArray[i] < dpArray[j] + 1</code>
 * ;
 * ;
 * Algorithm:
 * - Create an array of dpArray of same size
 * - Populate dpArray with 1 at all places
 * - Iterate i from 1 to array.length
 * - - Iterate j from 0 to i
 * - - - If <code>array[j] < array[i] && dpArray[i] < dpArray[j] + 1 </code> then
 * - - - - <code>dpArray[i] = dpArray[j] + 1</code>
 * - Find max in dpArray
 * - Return max
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/longest-increasing-subsequence/
 */
public class LongestIncreasingSubsequence {

    public static List<Integer> getLIS(int[] array) {
        int[] dpArray = new int[array.length];
        int[] parentArray = new int[array.length];

        /** Update default values for all **/
        for (int i = 0; i < array.length; i++) {
            dpArray[i] = 1;
            parentArray[i] = -1;
        }

        /** Iterating for all element starting from index 1 **/
        for (int i = 1; i < array.length; i++) {

            /** Checking previous elements of an array **/
            for (int j = 0; j < i; j++) {

                /**
                 * If array[i] is greater than previous element i.e. array[j] and
                 * Count dpArray[i] less than dpArray[j] + 1
                 */
                if (array[j] < array[i] && dpArray[i] < dpArray[j] + 1) {
                    /** Update element count **/
                    dpArray[i] = dpArray[j] + 1;
                    /** Update previous element **/
                    parentArray[i] = j;
                }
            }
        }

        int max = dpArray[0];
        int elementIndex = 0;

        /** Finding max value **/
        for (int i = 1; i < dpArray.length; i++) {
            if (max < dpArray[i]) {
                max = dpArray[i];
                elementIndex = i;
            }
        }

        List<Integer> result = new ArrayList<>();

        /** Add number to result list **/
        while (elementIndex != -1) {
            result.add(0, array[elementIndex]);
            elementIndex = parentArray[elementIndex];
        }

        return result;

    }
}

class LongestIncreasingSubsequenceTest {
    public static void main(String[] args) {
        int array1[] = {2, 7, 1, 3, 23, 2};
        int array2[] = {1, 2, 3, 4, 23, 110};
        int array3[] = {110, 12, 3, 2, 1};
        System.out.println("Array :" + Arrays.toString(array1) + " LIS:" + getLIS(array1));
        System.out.println("Array :" + Arrays.toString(array2) + " LIS:" + getLIS(array2));
        System.out.println("Array :" + Arrays.toString(array3) + " LIS:" + getLIS(array3));
    }
}
