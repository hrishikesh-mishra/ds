package com.hrishikesh.practices.binaryheap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * Binary Heap:
 * A Binary Heap is a Binary Tree with following properties
 * - It’s a complete tree (All levels are completely filled except possibly the last level and the last
 * level has all keys as left as possible). This property of Binary Heap makes them suitable to be stored in an array.
 * - A Binary Heap is either Min Heap or Max Heap. In a Min Binary Heap, the key at root must be minimum among
 * all keys present in Binary Heap. The same property must be recursively true for all nodes in Binary Tree.
 * Max Binary Heap is similar to Min Heap
 * For Array Binary
 * - If parent is at ith position then
 * - - Left child will be at 2*i + 1
 * - - Right child will be at 2*i + 2
 * - If a child is at ith position then,
 * - - Parent will be at i / 2
 *
 * @author hrishikesh.mishra
 * @video https://www.youtube.com/watch?v=WCm3TqScBM8
 * @link http://hrishikeshmishra.com/binary-heap-implementation-array/
 */
public class ArrayBinaryHeap<E> {

    private E[] array;
    private int capacity;
    private int lastIndex;
    private Comparator<E> comparator;

    ArrayBinaryHeap(int capacity, Comparator<E> comparator) {
        this.array = (E[]) new Object[capacity];
        this.capacity = capacity;
        this.lastIndex = -1;
        this.comparator = comparator;
    }

    public E getMin() {
        return isEmpty() ? null : array[0];

    }

    public void insert(E element) {
        /** if array is full**/
        if (isFull()) {
            throw new RuntimeException("Heap is full.");
        }

        array[++lastIndex] = element;
        bottomUpHeapify(lastIndex);
    }


    public E extractMin() {
        E element = getMin();
        if (Objects.isNull(element)) {
            return element;
        }

        array[0] = array[lastIndex];
        array[lastIndex--] = null;

        if (size() == 0) {
            return element;
        }

        topDownHeapify(0);
        return element;

    }


    private void topDownHeapify(int rootIndex) {
        //@// TODO: 11/12/16 This implementation is not optimized
        /** If it reached to end  or invalid index of array then don't do anything **/
        if (rootIndex == lastIndex || !isValidIndex(rootIndex)) {
            return;
        }

        int leftChildIndex = getLeft(rootIndex);
        int rightChildIndex = getRight(rootIndex);

        /** It means reached at end of list **/
        if (!isValidIndex(leftChildIndex) ) {
            return;
        }

        E rootElement = array[rootIndex];
        E leftElement = array[leftChildIndex];


        int leftRootCompare = comparator.compare(rootElement, leftElement);

        if (isValidIndex(rightChildIndex)) {
            E rightElement = array[rightChildIndex];

            /** If left has small value than root  **/
            if (leftRootCompare >= 0) {
                int leftRightCompare = comparator.compare(leftElement, rightElement);

                /** Here left child value is lesser than parent and right child, so move it to parent **/
                if (leftRightCompare <= 0) {
                    /** Swap left value with root **/
                    swap(rootIndex, leftChildIndex);

                    /** Recursively call left child **/
                    topDownHeapify(leftChildIndex);

                    return;

                } else {
                    /** Swap right value with root **/
                    swap(rootIndex, rightChildIndex);

                    /** Recursively call left child **/
                    topDownHeapify(rightChildIndex);
                    return;
                }
            } else {

                int rightRootCompare = comparator.compare(rootElement, rightElement);

                /** If right root has smaller value than right child **/
                if (rightRootCompare > 0) {
                    /** Swap right value with root **/
                    swap(rootIndex, rightChildIndex);

                    /** Recursively call left child **/
                    topDownHeapify(rightChildIndex);
                    return;
                }

            }
        } else {

            /** Here left child value is lesser than parent and right child, so move it to parent **/
            if (leftRootCompare > 0) {
                /** Swap left value with root **/
                swap(rootIndex, leftChildIndex);

                /** Recursively call left child **/
                topDownHeapify(leftChildIndex);
                return;

            }
        }
    }

    private void swap(int index1, int index2) {
        E temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private void bottomUpHeapify(int index) {
        //@// TODO: 11/12/16 This implementation is not optimized
        int parentIndex = getParent(index);

        /** Checking if we reached at parent or not. **/
        if (index == 0 || !isValidIndex(parentIndex)) {
            return;
        }

        E childElement = array[index];
        E parentElement = array[parentIndex];

        /** Comparing parent element with child **/
        int result = comparator.compare(parentElement, childElement);

        /** If parent is less than or equal to child then don't do anything. **/
        if (result <= 0) {
            return;
        }

        /** If parent is greater than child then, swap value **/
        array[index] = parentElement;
        array[parentIndex] = childElement;

        /** And recursively validate parent with grandparent **/
        bottomUpHeapify(parentIndex);
    }

    private int getLeft(int index) {
        return 2 * index + 1;
    }

    private int getRight(int index) {
        return 2 * index + 2;
    }

    private int getParent(int index) {
        return (index - 1) / 2;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index <= lastIndex;
    }

    public boolean isEmpty() {
        return lastIndex == -1;
    }

    public boolean isFull() {
        return lastIndex + 1 == capacity;
    }

    public int size() {
        return lastIndex + 1;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}


class ArrayBinaryHeapTest {

    public static void main(String[] args) {
        ArrayBinaryHeap<Integer> binaryHeap = new ArrayBinaryHeap<>(8, (Integer o1, Integer o2) -> Integer.compare(o1, o2));
        binaryHeap.insert(10);
        binaryHeap.insert(12);
        binaryHeap.insert(6);
        binaryHeap.insert(5);
        binaryHeap.insert(20);
        binaryHeap.insert(7);
        binaryHeap.insert(8);
        binaryHeap.insert(3);

        System.out.println(binaryHeap);

        System.out.println("Extract Min: " + binaryHeap.extractMin());
        System.out.println(binaryHeap);
        System.out.println("Extract Min: " + binaryHeap.extractMin());
        System.out.println(binaryHeap);
        System.out.println("Extract Min: " + binaryHeap.extractMin());
        System.out.println(binaryHeap);
        System.out.println("Extract Min: " + binaryHeap.extractMin());
        System.out.println(binaryHeap);
        System.out.println("Extract Min: " + binaryHeap.extractMin());
        System.out.println(binaryHeap);
        System.out.println("Extract Min: " + binaryHeap.extractMin());
        System.out.println(binaryHeap);
        System.out.println("Extract Min: " + binaryHeap.extractMin());
        System.out.println(binaryHeap);
        System.out.println("Extract Min: " + binaryHeap.extractMin());
        System.out.println(binaryHeap);

    }
}