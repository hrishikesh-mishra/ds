package com.hrishikeshmishra.practices.sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Problem:
 * Quick Sort with Randomized Partition
 * ;
 * Algorithm:
 * - Same as quick partition but,
 * - Before partitioning swap one random index
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/quick-sort-randomized-partition/
 */
public class QuickSortWithRandomizedPartition {

    public static void sort(int[] array) {
        sortHelper(array, 0, array.length - 1);
    }

    private static void sortHelper(int[] array, int p, int r) {
        if (p < r) {
            int q = randomizedPartition(array, p, r);
            sortHelper(array, p, q - 1);
            sortHelper(array, q + 1, r);
        }
    }

    private static int randomizedPartition(int[] array, int p, int r) {
        int randomIndex = ThreadLocalRandom.current().nextInt(p, r + 1);
        swap(array, randomIndex, r);
        return partition(array, p, r);

    }

    private static int partition(int[] array, int p, int r) {
        int i = p - 1;
        int j = p;
        while (j < r) {
            if (array[j] <= array[r]) {
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


class QuickSortWithRandomizedPartitionTest {
    public static void main(String[] args) {
        int[] array = {2, 8, 7, 1, 3, 5, 6, 4};
        System.out.println("Before Array: " + Arrays.toString(array));
        QuickSortWithRandomizedPartition.sort(array);
        System.out.println("After Array: " + Arrays.toString(array));
    }
}