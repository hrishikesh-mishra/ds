package com.hrishikesh.dsjava.stack.core;

/**
 * Created by hrishikesh.mishra on 11/02/16.
 */
public interface Queue<E> {

    /**
     * Number of element  in queue
     * @return
     */
    int size();

    /**
     * Check is queue is empty.
     * @return
     */
    boolean isEmpty();

    /**
     * Insert an element at rear of queue
     * @param e
     */
    void enqueue(E e);

    /**
     * Return but doesn't remove, the first element of the queue (null if empty)
     * @return
     */
    E first();

    /**
     * Remove and return first element of queue (null if empty)
     * @return
     */
    E dequeue();

}

