package com.hrishikesh.ns.queue;

/**
 * Queue ADT
 *
 * Created by hrishikesh.mishra
 */
public interface Queue<E> {

    /**
     * Insert an element at end of queue.
     * @param element
     */
    public void enQueue(E element);

    /**
     * Remove element from front of queue
     * @return
     */
    public E deQueue();

    /**
     * Get element of of front of queue without removing
     * @return
     */
    public E front();

    /**
     * Size of queue
     * @return
     */
    public int size();

    /**
     * Is queue empty?
     * @return
     */
    public boolean isEmpty();

}
