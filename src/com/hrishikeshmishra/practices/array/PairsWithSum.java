package com.hrishikeshmishra.practices.array;

import java.util.Arrays;

import static com.hrishikeshmishra.practices.array.PairsWithSum.findPair;

/**
 * Problem:
 * Pairs with Sum
 * ;
 * Find all pairs within an array which sum to a specified value.
 * ;
 * ;
 * Algorithm:
 * - Sort array in ascending order
 * - Set leftIndex = 0 and rightIndex = 0
 * - Iterate till leftIndex < rightIndex
 * - - Set localSum = array[leftIndex] + array[rightIndex];
 * - - If localSum == sum then
 * - - - Print both indices and increase leftIndex and decrease rightIndex by 1
 * - - Else If localSum < sum then
 * - - - increase leftIndex by 1
 * - - Else
 * - - - decrease rightIndex by 1
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/pairs-with-sum/
 */
public class PairsWithSum {

    public static void findPair(int[] array, int sum) {
        Arrays.sort(array);

        int leftIndex = 0;
        int rightIndex = array.length - 1;

        while (leftIndex < rightIndex) {
            int localSum = array[leftIndex] + array[rightIndex];

            if (localSum == sum) {
                System.out.println("Pair: (" + array[leftIndex] + ", " + array[rightIndex] + ")");
                leftIndex++;
                rightIndex--;
            } else if (localSum < sum) {
                leftIndex++;
            } else {
                rightIndex--;
            }
        }

    }
}

class PairsWithSumTest {
    public static void main(String[] args) {
        int[] array = {-2, -1, 0, 3, 5, 6, 7, 9, 13, 14};
        int k = 13;
        System.out.println(Arrays.toString(array));
        System.out.println("K: " + k);
        findPair(array, k);
    }
}