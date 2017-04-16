package com.hrishikeshmishra.ns.heapandpriorityqueue;

/**
 * IMax Heap interface
 *
 * @author hrishikesh.mishra
 */
public interface IMaxHeap<E> extends IHeap<E> {

    E getMax();

    E deleteMax();

    default HeapType getHeapType() {
        return HeapType.MIN_HEAP;
    }
}
