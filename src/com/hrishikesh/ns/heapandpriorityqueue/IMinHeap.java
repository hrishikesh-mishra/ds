package com.hrishikesh.ns.heapandpriorityqueue;

/**
 * Min heap interface
 *
 * @author hrishikesh.mishra
 */
public interface IMinHeap<E extends Comparable> extends IHeap {

    E getMin();

    E deleteMin();

    default HeapType getHeapType() {
        return HeapType.MIN_HEAP;
    }
}
