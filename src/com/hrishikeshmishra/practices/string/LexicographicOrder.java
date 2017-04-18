package com.hrishikeshmishra.practices.string;

import java.util.Arrays;

import static com.hrishikeshmishra.practices.string.LexicographicOrder.getNextPermutation;

/**
 * Problem:
 * Lexicographic Order
 * Generates permutations using lexicographic ordering.
 * ;
 * ;
 * Algorithm:
 * - Start from end and find next smaller value between two adjacent
 * - Swap that value with next large number from end
 * - And sort all number from accesending order
 * ;
 * ;
 *
 * @author hrishikesh.mishra
 * @quora https://www.quora.com/How-would-you-explain-an-algorithm-that-generates-permutations-using-lexicographic-ordering
 * @link http://hrishikeshmishra.com/lexicographic-order/
 */
public class LexicographicOrder {

    public static int[] getNextPermutation(int[] array) {

        int x = array.length - 1;

        /** Checking for possibility **/
        while (x > 0 && array[x - 1] >= array[x]) {
            x--;
        }

        /** When all permutation done **/
        if (x <= 0) {
            throw new RuntimeException("This was last permutation.");
        }

        int y = array.length - 1;

        /** Find next largest fox array[x - 1] **/
        while (array[y] <= array[x - 1]) {
            y--;
        }

        /** Swap **/
        swap(array, x - 1, y);

        reverse(array, x, array.length - 1);

        return array;

    }

    private static void reverse(int[] array, int left, int right) {
        while (left < right) {
            swap(array, left, right);
            left++;
            right--;
        }
    }

    private static void swap(int[] array, int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
}

class LexicographicOrderTest {
    public static void main(String[] args) {
        int[] array = {5, 1, 7, 6, 3, 9, 8, 4, 2};

        System.out.println("Array : " + Arrays.toString(array));
        System.out.println("Next permutations : " + Arrays.toString(getNextPermutation(array)));
    }
}
