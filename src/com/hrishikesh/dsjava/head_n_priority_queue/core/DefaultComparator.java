package com.hrishikesh.dsjava.head_n_priority_queue.core;

import java.util.Comparator;

/**
 * Created by hrishikesh.mishra on 25/03/16.
 */
public class DefaultComparator<E> implements Comparator<E> {

    @Override
    public int compare(E o1, E o2) {
        return ((Comparable<E>) o1).compareTo(o2);
    }
}
