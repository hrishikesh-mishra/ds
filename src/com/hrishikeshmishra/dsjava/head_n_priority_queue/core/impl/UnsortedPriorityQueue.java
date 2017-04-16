package com.hrishikeshmishra.dsjava.head_n_priority_queue.core.impl;

import com.hrishikeshmishra.dsjava.list_adt.core.Position;
import com.hrishikeshmishra.dsjava.list_adt.core.impl.LinkedPositionalList;
import com.hrishikeshmishra.dsjava.list_adt.core.impl.PositionalList;
import com.hrishikeshmishra.dsjava.head_n_priority_queue.core.AbstractPriorityQueue;
import com.hrishikeshmishra.dsjava.head_n_priority_queue.core.Entry;

import java.util.Comparator;

/**
 * An implementation a priority queue with an unsorted list
 *
 * Created by hrishikesh.mishra on 25/03/16.
 */
public class UnsortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    private PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();

    /**
     * Creates an empty priority queue based on the
     * natural ordering of its keys
     */
    public UnsortedPriorityQueue() {
        super();
    }

    /**
     * Creates an empty priority queue using the given comparator to order
     * keys
     * @param comparator
     */
    public UnsortedPriorityQueue(Comparator<K> comparator){
        super(comparator);
    }

    /**
     * Returns the position of an entry having minimal key.
     * @return
     */
    private Position<Entry<K, V>> findMin(){
        Position<Entry<K, V>> small = list.first();
        for(Position<Entry<K, V>> walk: list.positions())
            if(compare(walk.getElement(), small.getElement()) < 0)
                small = walk;

        return small;
    }

    public Entry<K, V> insert(K key, V value){
        //auxiliary key-checking method (could throw exception)
        checkKey(key);
        Entry<K, V> newest = new PQEntry<K, V>(key, value);
        list.addLast(newest);
        return newest;
    }

    public Entry<K, V> min(){
        if(list.isEmpty()) return null;
        return findMin().getElement();
    }

    public Entry<K, V> removeMin(){
        if(list.isEmpty()) return null;
        return list.remove(findMin());
    }

    public int size(){
        return list.size();
    }


}
