package com.hrishikesh.dsjava.head_n_priority_queue.core;

/**
 *
 * Interface for a key-value pair
 *
 * Created by hrishikesh.mishra on 25/03/16.
 */
public interface Entry<K, V> {
    K getKey();
    V getValue();
}
