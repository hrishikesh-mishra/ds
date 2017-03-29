package com.hrishikesh.practices.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem:
 * Find the largest sub-array with 0 sum
 * Given an array of integers, find length of the largest subarray with sum equals to 0.
 * ;
 * For Example:
 * Input: arr[] = {15, -2, 2, -8, 1, 7, 10, 23};
 * Output: 5
 * The largest sub-array with 0 sum is -2, 2, -8, 1, 7
 * ;
 * Input: arr[] = {1, 2, 3}
 * Output: 0
 * There is no sub-array with 0 sum
 * ;
 * Input: arr[] = {1, 0, 3}
 * Output: 1
 * ;
 * ;
 * Solution:
 *  - Iterate from start to end of array
 *  - And each element in array
 *  - And put current sum with index in map
 *  - - Now more interesting part is - before putting current sum to map check is map contain this sum or not
 *  - - If it contains that means whatever we have add between these two sums is zero and that is what we are looking for
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/find-largest-sub-array-zero-sum/
 */
public class LargestSubArrayWithSumZero {

    public static int getMaxLengthNotOptimized(int[] array) {
        int sum;
        int maxLen = 0;

        for (int i = 0; i < array.length; i++) {

            sum = 0;
            int length = 0;

            for (int j = i; j < array.length; j++) {
                sum += array[j];
                length++;

                if (sum == 0 && length > maxLen) {
                    maxLen = length;
                }
            }
        }

        return maxLen;
    }


    public static int getMaxLengthOptimized(int[] array) {

        /** Map to hold sum and index value of that sum **/
        Map<Integer, Integer> sumMap = new HashMap<>();

        int sum = 0;
        int maxLength = 0;

        for (int i = 0; i < array.length; i++) {
            sum += array[i];

            /** If current sum is a sum that occurred that means between these two sum is zero sum **/
            if (sumMap.containsKey(sum)) {
                int previousSumIndex = sumMap.get(sum);
                if (i - previousSumIndex > maxLength) {
                    maxLength = i - previousSumIndex;
                }
            } else {
                sumMap.put(sum, i);
            }
        }

        return maxLength;
    }
}


class LargestSubArrayWithSumZeroTest {
    public static void main(String[] args) {
        int[] array = {15, -2, 2, -8, 1, 7, 10, 23};

        System.out.println("Array : " + Arrays.toString(array));
        System.out.println("Length (not optimized) : " + LargestSubArrayWithSumZero.
                getMaxLengthNotOptimized(array));
        System.out.println("Length (optimized) : " + LargestSubArrayWithSumZero.
                getMaxLengthOptimized(array));

        array = new int[]{1, 2, 3};
        System.out.println("\n\nArray : " + Arrays.toString(array));
        System.out.println("Length (not optimized) : " + LargestSubArrayWithSumZero.
                getMaxLengthNotOptimized(array));
        System.out.println("Length (optimized) : " + LargestSubArrayWithSumZero.
                getMaxLengthOptimized(array));

        array = new int[]{1, 0, 3};
        System.out.println("\n\nArray : " + Arrays.toString(array));
        System.out.println("Length (not optimized) : " + LargestSubArrayWithSumZero.
                getMaxLengthNotOptimized(array));
        System.out.println("Length (optimized) : " + LargestSubArrayWithSumZero.
                getMaxLengthOptimized(array));


    }
}