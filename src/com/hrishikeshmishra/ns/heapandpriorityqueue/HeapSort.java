package com.hrishikeshmishra.ns.heapandpriorityqueue;

/**
 * Problem:
 * Heap Sort
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/heap-sort/
 */
public class HeapSort<E extends Comparable> extends ArrayMaxHeap<E> {

    public HeapSort(int capacity) {
        super(capacity);
    }

    public void buildHeap(E[] array) {


        /** destroy existing heap **/
        destroy();
        makeCapacity(array.length);
        int i;

        /** Insert into heap **/
        for (i = 0; i < array.length; i++) {
            repository[i] = array[i];
        }


        /** update size of heap **/
        size = array.length;

        /**
         * heapify only all non-leaf element,
         * because leaf element never violate heap property.
         */

        int lastNonLeafIndex = (size - 1) / 2;

        /** percolate up **/
        for (i = lastNonLeafIndex; i >= 0; i--)
            percolateUp(i);
    }

    public void makeCapacity(int capacity) {
        repository = (E[]) new Comparable[capacity];
    }

    protected E[] sort(E[] array) {
        /** first build heap **/
        buildHeap(array);
        for (int i = size - 1; i >= 0; i--) {
            array[i] = deleteMax();
        }
        return array;
    }


}


class HeapSortTest {

    public static void main(String[] args) {
        HeapSort<Integer> heapSort = new HeapSort<>(10);
        Integer[] sortedData = heapSort.sort(new Integer[]{17, 3, 32, 2, 234, 54, 12, 1, 21, 132});

        System.out.print("Sorted : ");
        for (int i = 0; i < sortedData.length; i++) {
            System.out.print(sortedData[i] + ", ");
        }
    }
}