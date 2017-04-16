package com.hrishikeshmishra.dsjava.stack.core;

/**
 * Created by hrishikesh.mishra on 11/02/16.
 */
public interface Deque<E> {

    /**
     * Size of queue
     * @return
     */
    int size();

    /**
     * Is queue empty
     * @return
     */
    boolean isEmpty();

    /**
     * Fist element of queue without removable
     * @return
     */
    E first();

    /**
     * Last element of queue without removable
     * @return
     */
    E last();

    /**
     * Inserts an element at the front of the deque
     */
    void addFirst();

    /**
     *  Inserts an element at the back of the deque
     */
    void addLast();

    /**
     *  Removes and returns the first element of the deque (null if empty)
     * @return
     */
    E removeFirst();

    /**
     * Removes and returns the last element of the deque (null if empty).
     * @return
     */
    E removeLast();

}
