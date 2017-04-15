package com.hrishikesh.practices.sort;

import java.util.Arrays;

/**
 * Problem:
 * Reverse Quick Sort
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/reverse-quick-sort/
 */
public class ReverseQuickSort {

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int p, int r) {
        if (p < r) {
            int q = partition(array, p, r);
            quickSort(array, p, q - 1);
            quickSort(array, q + 1, r);
        }
    }

    private static int partition(int[] array, int p, int r) {
        int i = p - 1;
        int j = p;
        while (j < r) {
            if (array[j] > array[r]) {
                swap(array, i + 1, j);
                i++;
            }
            j++;
        }
        swap(array, i + 1, r);
        return i + 1;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}


class ReverseQuickSortTest {
    public static void main(String[] args) {
        int[] array = {2, 8, 7, 1, 3, 5, 6, 4};
        System.out.println("Before Array: " + Arrays.toString(array));
        ReverseQuickSort.quickSort(array);
        System.out.println("After Array: " + Arrays.toString(array));

    }
}