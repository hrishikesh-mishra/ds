package com.hrishikeshmishra.dsjava.map_n_heap.core;

import com.hrishikeshmishra.dsjava.head_n_priority_queue.core.Entry;

/**
 * Created by hrishikesh.mishra on 30/03/16.
 */

public interface SortedMap<K,V> extends Map<K,V>{
    /**
     * Returns the entry having the least key (or null if map is empty).
     */
    Entry<K,V> firstEntry();

    /**
     * Returns the entry having the greatest key (or null if map is empty).
     * @return entry with greatest key (or null if map is empty)
     */
    Entry<K,V> lastEntry();

    /**
     * Returns the entry with least key greater than or equal to given key
     * (or null if no such key exists).
     */
    Entry<K,V> ceilingEntry(K key) throws IllegalArgumentException;

    /**
     * Returns the entry with greatest key less than or equal to given key
     * (or null if no such key exists).
     */
    Entry<K,V> floorEntry(K key) throws IllegalArgumentException;

    /**
     * Returns the entry with greatest key strictly less than given key
     * (or null if no such key exists).
     */
    Entry<K,V> lowerEntry(K key) throws IllegalArgumentException;

    /**
     * Returns the entry with least key strictly greater than given key
     * (or null if no such key exists).
     */
    Entry<K,V> higherEntry(K key) throws IllegalArgumentException;

    /**
     * Returns an iterable containing all keys in the range from
     * <code>fromKey</code> inclusive to <code>toKey</code> exclusive.
     */
    Iterable<Entry<K,V>> subMap(K fromKey, K toKey) throws IllegalArgumentException;
}