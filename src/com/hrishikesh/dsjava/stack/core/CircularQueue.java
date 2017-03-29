package com.hrishikesh.dsjava.stack.core;

/**
 * Created by hrishikesh.mishra on 11/02/16.
 */
public interface CircularQueue <E> extends Queue<E> {
    /**
     * Rotates the front element of the queue to the back of the queue.
     * This does nothing if the queue is empty.
     */
    void rotate();
}
