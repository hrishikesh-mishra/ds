package com.hrishikesh.practices.sort;

import java.util.Arrays;

/**
 * Problem:
 * Quick Sort Using Tail Recursion
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/quick-sort-using-tail-recursion/
 */
public class QuickSortUsingTailRecursion {

    public static void sort(int[] array) {
        tailRecursion(array, 0, array.length - 1);
    }

    private static void tailRecursion(int[] array, int p, int r) {

        while (p < r) {
            int q = partition(array, p, r);
            tailRecursion(array, p, q - 1);
            p = q + 1;
        }
    }

    private static int partition(int[] array, int p, int r) {
        int i = p - 1;
        int j = p;

        while (j < r) {
            if (array[j] <= array[r]) {
                swap(array, ++i, j);
            }
            j++;
        }

        swap(array, ++i, j);
        return i;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}


class QuickSortUsingTailRecursionTest {
    public static void main(String[] args) {
        int[] array = {2, 8, 7, 1, 3, 5, 6, 4};
        System.out.println("Before Array: " + Arrays.toString(array));
        QuickSortUsingTailRecursion.sort(array);
        System.out.println("After Array: " + Arrays.toString(array));
    }
}