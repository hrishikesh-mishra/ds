package com.hrishikesh.practices.array;

import java.util.Arrays;

import static com.hrishikesh.practices.array.LargestContiguousSum.sum;

/**
 * Problem:
 * Largest Contiguous Sum in a array
 * In an array of integers, find the contiguous sequences with largest sum.
 * ;
 * Fact:
 * - If sum is positive then continue to add more element else reset sum to zero
 * ;
 * ;
 * Algorithm:
 * - Set maxSum = 0 and sum = 0
 * - Iterate all element of array one by one
 * - - sum += sum
 * - - if maxSum < sum then
 * - - - Set maxSum = sum
 * - - Else If sum < 0 then
 * - - - Set sum = 0
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/largest-contiguous-sum-array/
 */
public class LargestContiguousSum {

    public static int sum(int[] array) {
        int maxSum = 0;
        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            sum += array[i];

            if (maxSum < sum) {
                maxSum = sum;
            } else if (sum < 0) {
                sum = 0;
            }
        }

        return maxSum;
    }
}

class LargestContiguousSumTest {

    public static void main(String[] args) {
        int[] array = {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("Sum: " + sum(array));

        array = new int[]{2, -8, 3, -2, 4, -10};
        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("Sum: " + sum(array));
    }
}
