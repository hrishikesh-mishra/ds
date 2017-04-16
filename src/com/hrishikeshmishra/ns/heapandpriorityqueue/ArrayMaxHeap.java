package com.hrishikeshmishra.ns.heapandpriorityqueue;

/**
 * Problem:
 * Array based max heap
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/array-based-max-heap-implementation/
 */
public class ArrayMaxHeap<E extends Comparable> implements IMaxHeap<E> {

    protected E[] repository;
    protected int size;
    protected int capacity;

    @SuppressWarnings("unchecked")
    public ArrayMaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.repository = (E[]) new Comparable[capacity];
    }

    public void insert(E data) {
        if (isFull()) increaseCapacity();
        repository[size++] = data;
        percolateUp(size - 1);
    }

    public E deleteMax() {
        if (isEmpty()) return null;
        E data = repository[0];
        size--;
        if (size == 0) {
            repository[0] = null;
            return data;
        }

        repository[0] = repository[size];
        repository[size] = null;
        percolateDown(0);
        return data;
    }

    @Override
    public void destroy() {
        this.size = 0;
        this.repository = null;
    }

    @Override
    public E getMax() {
        if (isEmpty()) return null;
        return repository[0];
    }

    @SuppressWarnings("unchecked")
    protected void increaseCapacity() {
        int newCapacity = capacity * 2;
        E[] newRepo = (E[]) new Comparable[newCapacity];
        for (int i = 0; i < size; i++)
            newRepo[i] = repository[i];
        this.repository = newRepo;
        this.capacity = newCapacity;
    }

    protected void percolateDown(int index) {
        if (index >= size) return;

        int leftChildIndex = getLeftChild(index),
                rightChildIndex = getRightChild(index),
                maxChildIndex = index;

        /** if left child exists and it has greater value than root **/
        if (isValidNode(leftChildIndex) && hasLargerValue(leftChildIndex, index))
            maxChildIndex = leftChildIndex;

        /** if right child exists and it has greater value than root **/
        if (isValid(rightChildIndex) && hasLargerValue(rightChildIndex, maxChildIndex))
            maxChildIndex = rightChildIndex;

        /** if root has smaller value than child **/
        if (maxChildIndex != index)
            swapValues(maxChildIndex, index);
        else
            return;

        percolateDown(maxChildIndex);
    }

    protected void percolateUp(int index) {
        /** Reached on top of tree **/
        if (index <= 0) return;

        int parentIndex = getParentIndex(index);
        if (hasSmallerValue(parentIndex, index))
            swapValues(parentIndex, index);
        else
            return;

        percolateUp(parentIndex);
    }


    private boolean hasLargerValue(int index1, int index2) {
        return (repository[index1].compareTo(repository[index2])) > 0;
    }

    private boolean hasSmallerValue(int index1, int index2) {
        return (repository[index1].compareTo(repository[index2])) < 0;
    }

    private void swapValues(int index1, int index2) {
        E temp = repository[index1];
        repository[index1] = repository[index2];
        repository[index2] = temp;
    }

    @Override
    public int getParentIndex(int index) {
        if (!isValid(index)) return -1;
        return (index - 1) / 2;
    }

    @Override
    public int getLeftChild(int index) {
        int leftIndex = 2 * index + 1;
        if (!isValid(leftIndex)) return -1;
        return leftIndex;
    }

    @Override
    public int getRightChild(int index) {
        int rightIndex = 2 * index + 2;
        if (!isValid(rightIndex)) return -1;
        return rightIndex;
    }

    private boolean isValid(int index) {
        return (index >= 0 && index < size);
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private boolean isValidNode(int index) {
        return (index > -1);
    }

    private boolean isFull() {
        return size == capacity;
    }

}


class ArrayMinHeapTest {

    public static void main(String[] args) {
        IMaxHeap<Integer> maxHeap = new ArrayMaxHeap<>(4);
        maxHeap.insert(13);
        maxHeap.insert(2);
        maxHeap.insert(12);
        maxHeap.insert(24);
        maxHeap.insert(34);

        System.out.println("Max: " + maxHeap.getMax());
        System.out.println("Delete Max : " + maxHeap.deleteMax());
        System.out.println("Delete Max : " + maxHeap.deleteMax());
        System.out.println("Delete Max : " + maxHeap.deleteMax());
        System.out.println("Delete Max : " + maxHeap.deleteMax());

        maxHeap.insert(100);
        maxHeap.insert(23);
        maxHeap.insert(17);

        System.out.println("Delete Max : " + maxHeap.deleteMax());
        System.out.println("Delete Max : " + maxHeap.deleteMax());
        System.out.println("Delete Max : " + maxHeap.deleteMax());
    }
}


