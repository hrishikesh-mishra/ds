package com.hrishikeshmishra.dsjava.map_n_heap.core;

import com.hrishikeshmishra.dsjava.head_n_priority_queue.core.Entry;

/**
 * Created by hrishikesh.mishra on 26/03/16.
 */
public interface Map<K,V> {

    /**
     * Returns the number of entries in map
     * @return
     */
    int size();

    /**
     * Returns a boolean indicating whether map is empty
     * @return
     */
    boolean isEmpty();

    /**
     * Returns the values v associated with key k, if such an entry exists;
     * otherwise returns null
     * @param key
     * @return
     */
    V get(K key);

    /**
     * If map does not have an entry with key equal to k, then add entry
     * (k,v) to map and returns null; else replace with v the existing
     * value of the entry with key equal to k and returns the old value.
     * @param key
     * @param value
     */
    V put(K key, V value);

    /**
     * Removes from map the entry with key equal to k, and returns its values;
     * if map has no such entry, then return null
     * @param key
     * @return
     */
    V remove(K key);

    /**
     * Returns an iterable collection containing all the keys stored in map.
     * @return
     */
    Iterable<K> keySet();

    /**
     * Returns an iterable collection containing all the values of entries
     * stored in map (with repetition if multiple keys map to the same value).
     * @return
     */
    Iterable<V> values();

    /**
     * Returns an iterable collection containing all the key-value entries in
     * map
     * @return
     */
    Iterable<Entry<K,V>> entrySet();
}
