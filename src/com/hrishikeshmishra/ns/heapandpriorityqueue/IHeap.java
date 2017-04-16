package com.hrishikeshmishra.ns.heapandpriorityqueue;

/**
 * Heap interface
 *
 * @author hrishikesh.mishra
 */
public interface IHeap<E> {

    HeapType getHeapType();

    /**
     * Gget parent index
     *
     * @param index
     * @return
     */
    int getParentIndex(int index);

    /**
     * Get left child index
     *
     * @param index
     * @return
     */
    int getLeftChild(int index);

    /**
     * Get right child index
     *
     * @param index
     * @return
     */
    int getRightChild(int index);

    /**
     * @param data
     */
    void insert(E data);

    /**
     * Destroy
     */
    void destroy();
}
