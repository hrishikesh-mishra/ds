package com.hrishikesh.ns.selection;

/**
 * Problem:
 * Find the k-smallest elements in an array of A of n elements.
 * ;
 * ;
 * Kth Index Algorithm:
 * - Find pivot_index using partition
 * - Iterate till k-l != pivot_index
 * - - if k-1 < pivot_index then
 * - - - Set end = pivot_index - 1
 * - - else
 * - - - Set start = pivot_index + 1
 * - - Recursively call with start and end
 * ;
 * ;
 * Partition Algorithm:
 * - Set pivot = array[end]
 * - Set pivot_index = start
 * - Iterate from start to end of
 * - - If array[i] < pivot then
 * - - - Swap ith index data with pivot_index data
 * - - - Increase pivot_index by 1
 * - Swap pivot_index  with end_index
 * - return pivot_index
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/k-smallest-elements-in-an-array/
 */
public class KSmallestFinder {

    public int getKthIndex(int[] array, int k) {
        return getKthIndex(array, k, 0, array.length - 1);
    }

    private int getKthIndex(int[] array, int k, int start, int end) {
        int pivotIndex = partition(array, start, end);

        while (pivotIndex != k - 1) {
            if (k - 1 < pivotIndex)
                end = pivotIndex - 1;
            else
                start = pivotIndex + 1;
            pivotIndex = partition(array, start, end);
        }
        return k - 1;
    }

    /*
    Second way to find pivot in array.

    private int partition(int[] array, int start, int end) {
        int pivot = array [start],
            pivotPosition = start++;

        while (start <= end){
            while ((start <= end) && (array[start]  < pivot))
                start++;

            while (( end >= start) && (array[end] >= pivot))
                end --;

            if(start > end)
                swap(array, pivotPosition, end);
            else
                swap(array, start, end);
        }

        return end;
    }
 */

    private int partition(int[] array, int start, int end) {
        int pivot = array[end],
                pivotIndex = start,
                i;

        for (i = start; i < end; i++) {
            if (array[i] < pivot) {
                swap(array, i, pivotIndex);
                pivotIndex++;
            }
        }

        swap(array, pivotIndex, end);
        return pivotIndex;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;

    }
}


class KSmallestFinderTest {

    public static void main(String[] args) {
        int[] array = {1, 23, 12, 13, 23, 6, 7, 232, 45, 8, 10};

        System.out.print("\nArray: ");
        print(array, array.length - 1);
        KSmallestFinder kSmallestFinder = new KSmallestFinder();
        int kIndex = kSmallestFinder.getKthIndex(array, 5);
        System.out.println("kIndex : " + kIndex);
        System.out.print("\nArray: ");
        print(array, kIndex);

        array = new int[]{1, 4, 2, 3, 2, 4, 5, 6, 8, 4, 2};
        System.out.print("\nArray: ");
        print(array, array.length - 1);
        kSmallestFinder = new KSmallestFinder();
        kIndex = kSmallestFinder.getKthIndex(array, 5);
        System.out.println("Index : " + kIndex);
        System.out.print("\nArray: ");
        print(array, kIndex);

    }

    private static void print(int[] array, int end) {
        for (int i = 0; i <= end; i++) System.out.print(array[i] + " ");
    }
}