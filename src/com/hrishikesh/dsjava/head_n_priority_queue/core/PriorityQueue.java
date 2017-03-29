package com.hrishikesh.dsjava.head_n_priority_queue.core;

/**
 * Created by hrishikesh.mishra on 25/03/16.
 */
public interface PriorityQueue<K, V> {

    /**
     * Returns the number of entries in the priority queues
     * @return
     */
    int size();

    /**
     * Returns a boolean indicating whether the priority queue
     * is empty
     * @return
     */
    boolean isEmpty();

    /**
     * Create an entry with key y and value u in the priority queue.
     * @param key
     * @param value
     * @return
     * @throws IllegalArgumentException
     */
    Entry<K, V> insert(K key, V value) throws IllegalArgumentException;

    /**
     * Returns (but does not remove) a priority queue entry (k,v)
     * having minimal key; returns null if the priority queue is empty.
     * @return
     */
    Entry<K, V> min();

    /**
     * Removes and returns an entry (k,v) having minimal key form
     * the priority queue; returns null if the priority queue is empty.
     * @return
     */
    Entry<K, V> removeMin();

}
