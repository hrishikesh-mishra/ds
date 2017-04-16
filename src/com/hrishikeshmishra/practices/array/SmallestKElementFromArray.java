package com.hrishikeshmishra.practices.array;

import java.util.Arrays;
import java.util.Random;

import static com.hrishikeshmishra.practices.array.SmallestKElementFromArray.getKSmallest;

/**
 * Problem:
 * Smallest K Elements from array (Selection Rank Algo)
 * - Find smallest k elements from array using selection rank algorithm.
 * ;
 * ;
 * Solution:
 * - There are various ways to find smallest k elements
 * - - Using sorting
 * - - Using Max Heap
 * - - Using selection rank algorithm (implemented below)
 * ;
 * ;
 *
 * @author hrishikesh.mishra
 * @video https://www.youtube.com/watch?v=kcVk30zzAmUs
 * @link http://hrishikeshmishra.com/smallest-k-elements-array-selection-rank-algorithm/
 */
public class SmallestKElementFromArray {

    private static class PartitionResult {
        private int leftSize;
        private int middleSize;

        public PartitionResult(int leftSize, int middleSize) {
            this.leftSize = leftSize;
            this.middleSize = middleSize;
        }
    }

    public static int[] getKSmallest(int[] array, int k) {
        int pivot = selectionRank(array, 0, array.length - 1, k - 1);
        int[] kSmallestArray = new int[k];

        int count = 0;
        for (int a : array) {
            if (a < pivot) {
                kSmallestArray[count++] = a;
            }
        }

        while (count < k) {
            kSmallestArray[count++] = pivot;
        }

        return kSmallestArray;
    }

    public static int selectionRank(int[] array, int start, int end, int k) {

        int pivot = array[getRandom(start, end)];
        PartitionResult partition = partition(array, start, end, pivot);

        if (k < partition.leftSize) {
            return selectionRank(array, start, start + partition.leftSize - 1, k);
        } else if (k < partition.leftSize + partition.middleSize) {
            return pivot;
        } else {
            return selectionRank(array,
                    start + partition.leftSize + partition.middleSize, end,
                    k - (partition.leftSize + partition.middleSize));
        }
    }

    /**
     * Create 3 buckets
     * - less than pivot
     * - equal to pivot
     * - greater than pivot
     *
     * @param start
     * @param end
     * @param pivot
     */
    private static PartitionResult partition(int[] array, int start, int end, int pivot) {
        /** elements less than pivot **/
        int left = start;

        /** elements equal to pivot **/
        int middle = start;

        /** elements greater than pivot **/
        int right = end;

        while (middle <= right) {
            if (array[middle] == pivot) {
                middle++;
            } else if (array[middle] < pivot) {
                swap(array, left, middle);
                left++;
                middle++;
            } else if (array[middle] > pivot) {
                swap(array, right, middle);
                right--;
            }
        }

        return new PartitionResult(left - start, right - left + 1);
    }

    private static void swap(int[] array, int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    private static int getRandom(int min, int max) {
        Random random = new Random();
        return min + random.nextInt(max + 1 - min);
    }


}


class SmallestKElementFromArrayTest {
    public static void main(String[] args) {
        int[] array = {10, 8, 12, 8, 2, 3, 12, 5};
        System.out.println("Array: " + Arrays.toString(array));
        int[] kSmallest = getKSmallest(array, 5);
        System.out.println("5 smallest array: " + Arrays.toString(kSmallest));
    }


}