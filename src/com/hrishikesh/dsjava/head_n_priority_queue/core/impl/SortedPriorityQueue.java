package com.hrishikesh.dsjava.head_n_priority_queue.core.impl;

import com.hrishikesh.dsjava.list_adt.core.Position;
import com.hrishikesh.dsjava.list_adt.core.impl.LinkedPositionalList;
import com.hrishikesh.dsjava.list_adt.core.impl.PositionalList;
import com.hrishikesh.dsjava.head_n_priority_queue.core.AbstractPriorityQueue;
import com.hrishikesh.dsjava.head_n_priority_queue.core.Entry;

import java.util.Comparator;
import java.util.Objects;

/**
 * An implementation of a priority queue with a
 * sorted list
 *
 * Created by hrishikesh.mishra on 25/03/16.
 */
public class SortedPriorityQueue <K, V> extends AbstractPriorityQueue<K, V> {

    /**
     * Primary collection of priority queue entries
     */
    private PositionalList<Entry<K,V>> list = new LinkedPositionalList<>();

    public SortedPriorityQueue(){
        super();
    }

    public SortedPriorityQueue(Comparator<K> comparator){
        super(comparator);
    }

    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException{
        //Auxiliary key-checking method (could throw exception)
        checkKey(key);
        Entry<K, V> newest = new PQEntry<>(key, value);
        Position<Entry<K,V>> walk = list.last();
        while (!Objects.isNull(walk) && compare(newest, walk.getElement()) <0){
            walk = list.before(walk);
            if(Objects.isNull(walk)) list.addFirst(newest);
            else list.addAfter(walk, newest);
        }

        return newest;
    }

    public Entry<K, V> min(){
        if(list.isEmpty()) return null;
        return list.first().getElement();
    }

    public Entry<K, V> removeMin(){
        if(list.isEmpty()) return null;
        return list.remove(list.first());
    }

    public int size(){
        return list.size();
    }
}
