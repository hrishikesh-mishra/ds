package com.hrishikesh.practices.array;

import java.util.Arrays;

/**
 * Problem:
 * Sum of two elements in array
 * For an sorted (in ascending order) array of integers, find two numbers such that they add up to a specific target number.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/sum-two-elements-array/
 */
public class SumOfTwoElementsInArray {

    public static int[] find(int[] a, int sum) {
        int i = 0;
        int j = a.length - 1;

        while (i <= j && i < a.length && j >= 0) {
            int localSum = a[i] + a[j];

            if (sum == localSum) {
                return new int[]{i + 1, j + 1};
            } else if (localSum < sum) {
                i++;
            } else {
                j--;
            }
        }

        return null;
    }
}

class SumOfTwoElementsInArrayTest {
    public static void main(String[] args) {
        int[] array = {2, 7, 11, 15};
        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("Sum Indices : " + Arrays.toString(SumOfTwoElementsInArray.find(array, 13)));
    }
}