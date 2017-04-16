package com.hrishikeshmishra.practices.binaryheap;

import java.util.Arrays;

/**
 * Problem:
 * Implement heap sort.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/implement-heap-sort/
 */
public class HeapSort {

    public static void sort(int[] array) {

        /**
         * Find last internal node (which will be parent of last leaf node).
         * Start top-down heafication from that node till root (0)
         */

        int lastLeafNodeIndex = array.length - 1;
        int lastInternalNode = getParentIndex(lastLeafNodeIndex);

        for (int i = lastInternalNode; i >= 0; i--) {
            heapify(array, array.length, i);
        }


        /** Take max value from array[0] and swap with last one, and do heapfication with till last -1 **/
        for (int i = lastLeafNodeIndex; i > 0; i--) {

            /** Swap largest value with i index **/
            swap(array, 0, i);

            /** Heapify till ith index  **/
            heapify(array, i, 0);
        }

    }

    /**
     * Top down Heapification
     *
     * @param array
     * @param size
     * @param rootIndex
     */
    private static void heapify(int array[], int size, int rootIndex) {

        int leftChildIndex = getLeftChild(rootIndex);
        int rightChildIndex = getRightChild(rootIndex);

        /** Nothing to do, we already reached limit **/
        if (leftChildIndex >= size) {
            return;
        }

        int maxValueIndex = rootIndex;

        /** If left child has larger value than root **/
        if (leftChildIndex < size && array[rootIndex] < array[leftChildIndex]) {
            maxValueIndex = leftChildIndex;
        }

        /** If right child has larger value than root **/
        if (rightChildIndex < size && array[maxValueIndex] < array[rightChildIndex]) {
            maxValueIndex = rightChildIndex;
        }

        /** If Max value Index is not root index, it means either left or right child has higher value **/
        if (maxValueIndex != rootIndex) {

            /** Swap max value with root **/
            swap(array, maxValueIndex, rootIndex);

            /** Recursively call heapify for child value **/
            heapify(array, size, maxValueIndex);
        }
    }


    private static int getLeftChild(int index) {
        return 2 * index + 1;
    }

    private static int getRightChild(int index) {
        return 2 * index + 2;
    }

    private static int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }


}


class HeapSortTest {
    public static void main(String[] args) {
        int[] array = {10, 3, 4, 12, 5, 17, 34, 56, 2, 16};

        System.out.println("Before sorting : " + Arrays.toString(array));
        HeapSort.sort(array);

        System.out.println("After Sorting : " + Arrays.toString(array));
    }
}