package com.hrishikesh.ns.sorting;

/**
 * Problem:
 * Quick Sort
 * ;
 * ;
 * Sort Algorithm:
 * - If left >= right the
 * - - return;
 * - Call partition function to get pivot index
 * - Recursively call sort function with left to pivot - 1 index
 * - Recursively call sort function with pivot + 1 to right index
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
 * @link "http://hrishikeshmishra.com/quick-sort/"
 */
public class QuickSort {

    public void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private void sort(int[] array, int left, int right) {
        /** Base condition for invalid index and only one item in array **/
        if (left >= right) return;

        int pivotIndex = partition(array, left, right);
        sort(array, left, pivotIndex - 1);
        sort(array, pivotIndex + 1, right);
    }

    private int partition(int[] array, int left, int right) {
        int pivotIndex = left,
                pivot = array[right];

        for (int i = left; i < right; i++) {
            if (array[i] < pivot) {
                swap(array, i, pivotIndex);
                pivotIndex++;
            }
        }

        swap(array, pivotIndex, right);
        return pivotIndex;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}


class QuickSortTest {

    public static void main(String[] args) {
        int[] array = {7, 3, 18, 9, 1, 2, 15, 12, 5};
        QuickSort quickSort = new QuickSort();

        System.out.print("Before sort: ");
        print(array);

        quickSort.sort(array);

        System.out.print("\nAfter sort: ");
        print(array);
    }

    private static void print(int[] array) {
        for (int i = 0; i < array.length; i++) System.out.print(array[i] + " ");
    }
}