package com.hrishikeshmishra.practices.array;

import java.util.Arrays;

import static com.hrishikeshmishra.practices.array.MissingOneNumberInArray.findMissing;

/**
 * Problem:
 * Missing One Number In Array
 * ;
 * An array contains all the integer from 0 to n, expect for one number which is missing.
 * Find missing number in O(n) in O(1) extra space.
 * ;
 * Fact:
 * - Sum of firs n natural number [i.e. Sum (1+2+3+.........+n)] = n(n+1)/2
 * ;
 * ;
 * Algorithm:
 * - Find sum of array's of elements
 * - Find sum of n natural number
 * - Return sum_of_natural_number - sum_of_array's_element
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/missing-one-number-array/
 */
public class MissingOneNumberInArray {

    public static int findMissing(int[] array, int n) {
        int sum = sum(array);
        int sumOfN = getSumOfNNaturalNumber(n);
        return sumOfN - sum;
    }

    public static int sum(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

    public static int getSumOfNNaturalNumber(int n) {
        return n * (n + 1) / 2;
    }
}


class MissingOneNumberInArrayTest {
    public static void main(String[] args) {
        int[] array = {2, 1, 5, 3};
        int n = 5;

        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("N: " + n);
        System.out.println("Missing Number: " + findMissing(array, n));
    }
}