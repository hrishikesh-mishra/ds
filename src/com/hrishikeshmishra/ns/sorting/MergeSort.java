package com.hrishikeshmishra.ns.sorting;

/**
 * Problem:
 * Merge Sort
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/merge-sort/
 */
public class MergeSort {

    public void mergeSort(int[] array) {
        int[] temp = new int[array.length];
        mergeSort(array, temp, 0, array.length - 1);
    }

    public void mergeSort(int[] array, int[] temp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, temp, left, mid);
            mergeSort(array, temp, mid + 1, right);
            merge(array, temp, left, mid, right);
        }
    }

    private void merge(int[] array, int[] temp, int leftStart, int mid, int rightEnd) {
        int leftEnd = mid, rightStart = mid + 1,
                size = rightEnd - leftStart + 1, tempPos = leftStart;

        while ((leftStart <= leftEnd) && (rightStart <= rightEnd))
            if (array[leftStart] <= array[rightStart]) temp[tempPos++] = array[leftStart++];
            else temp[tempPos++] = array[rightStart++];


        /** copy remaining left part **/
        while (leftStart <= leftEnd) temp[tempPos++] = array[leftStart++];

        /** copy remaining right part **/
        while (rightStart <= rightEnd) temp[tempPos++] = array[rightStart++];

        /** update main array **/
        for (int i = 0; i < size; i++) {
            array[rightEnd] = temp[rightEnd];
            rightEnd--;
        }

    }
}


class MergeSortTest {
    public static void main(String[] args) {
        int array[] = {1, 23, 12, 16, 42, 32, 44, 2, 67, 75, 21, 29};

        System.out.print("\nBefore sort:");

        print(array);
        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(array);

        System.out.print("\nAfter sort:");
        print(array);
    }

    private static void print(int[] array) {
        System.out.print("\nArray: ");
        for (int i = 0; i < array.length; i++) System.out.print(array[i] + " ");
    }


}

