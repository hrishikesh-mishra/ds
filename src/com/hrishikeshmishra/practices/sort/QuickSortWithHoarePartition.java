package com.hrishikeshmishra.practices.sort;

import java.util.Arrays;

/**
 * Problem:
 * Quick Sort With Hoare Partition
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/quick-sort-hoare-partition/
 * @wiki https://en.wikipedia.org/wiki/Quicksort#Hoare_partition_scheme
 */
public class QuickSortWithHoarePartition {

    public static void sort(int[] array) {
        sortHelper(array, 0, array.length - 1);
    }

    private static void sortHelper(int[] array, int p, int r) {
        if (p < r) {
            int q = doHoarePartitioning(array, p, r);
            sortHelper(array, p, q);
            sortHelper(array, q + 1, r);
        }
    }

    private static int doHoarePartitioning(int[] array, int p, int r) {
        int pivot = array[p];
        int i = p - 1;
        int j = r + 1;

        while (true) {

            do {
                i++;
            }
            while (array[i] < pivot);

            do {
                j--;
            }
            while (array[j] > pivot);

            if (i < j) {
                swap(array, i, j);
            } else {
                return j;
            }
        }

    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

class QuickSortWithHoarePartitionTest {

    public static void main(String[] args) {
        int[] array = {2, 8, 7, 1, 3, 5, 6, 4};
        System.out.println("Before Array: " + Arrays.toString(array));
        QuickSortWithHoarePartition.sort(array);
        System.out.println("After Array: " + Arrays.toString(array));
    }
}
