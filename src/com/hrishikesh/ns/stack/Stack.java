package com.hrishikesh.ns.stack;

/**
 * Stack contracts
 * Created by hrishikesh.mishra
 */
public interface Stack<E> {

    /**
     * Insert data onto stack
     * @param data
     */
    void push(E data);

    /**
     * Removes and returns the last inserted element from the stack
     * @return
     */
    E pop();

    /**
     * Returns the last inserted element without removing it.
     * @return
     */
    E top();

    /**
     * Returns the number of elements stored in stack
     * @return
     */
    int size();

    /**
     * Indicates whether any elements are stored in stack or not.
     * @return
     */
    boolean isEmpty();

    /**
     * Indicates whether the stack stack is full or not.
     * @return
     */
    boolean isStackFull();

}
