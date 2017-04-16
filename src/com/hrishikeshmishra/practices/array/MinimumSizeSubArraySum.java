package com.hrishikeshmishra.practices.array;

import java.util.Arrays;
import java.util.Objects;

import static com.hrishikeshmishra.practices.array.MinimumSizeSubArraySum.find;
import static com.hrishikeshmishra.practices.array.MinimumSizeSubArraySum.find2;

/**
 * Problem:
 * Minimum Size Subarray Sum
 * Given an array of n positive integers and a positive integer s,
 * find the minimal length of a contiguous subarray of which the sum ≥ s.
 * If there isn't one, return 0 instead.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/minimum-size-subarray-sum/
 */
public class MinimumSizeSubArraySum {


    /**
     * Algorithm based on sliding window
     *
     * @param array
     * @param s
     * @return
     */
    public static int find2(int[] array, int s) {
        int n = array.length;
        int minLength = n;
        boolean isFound = false;
        int sum = 0;
        int start = 0;
        int i = 0;

        while (i <= n) {
            if (sum >= s) {

                /** Sum of length 1, then its already optimized **/
                if (i - start == 1) {
                    return 1;
                }

                /** Checking min length  **/
                minLength = Math.min(minLength, i - start);

                /** Moving sliding window **/
                sum -= array[start];
                start++;

                isFound = true;
            } else {
                if (i == n) {
                    break;
                }

                sum += array[i];

                i++;
            }
        }

        return isFound ? minLength : 0;
    }

    public static int find(int array[], int s) {

        if (Objects.isNull(array) || array.length == 0) {
            return 0;
        }

        int n = array.length;

        /** Iterate from length from size 1 to n **/
        for (int length = 1; length <= n; length++) {

            /** Checking sum above length **/
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1;

                int sum = sum(array, i, j);
                if (sum >= s) {
                    return length;
                }
            }
        }

        return 0;
    }

    private static int sum(int[] array, int from, int to) {
        int sum = 0;
        for (int i = from; i <= to; i++) {
            sum += array[i];
        }
        return sum;
    }

}


class MinimumSizeSubArraySumTest {
    public static void main(String[] args) {
        int[] array = {2, 3, 1, 2, 4, 3};
        int s = 7;
        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("S: " + s);
        System.out.println("Minimum Sum Size: " + find(array, s));
        System.out.println("Minimum Sum Size: " + find2(array, s));

    }


}