package com.hrishikeshmishra.dsjava.map_n_heap.core.impl;

import com.hrishikeshmishra.dsjava.map_n_heap.core.AbstractHashMap;
import com.hrishikeshmishra.dsjava.head_n_priority_queue.core.Entry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by hrishikesh.mishra on 27/03/16.
 */
public class ProbeHashMap<K,V> extends AbstractHashMap<K,V> {

    /**
     * a fixed array of entries (all initially null)
     */
    private MapEntry<K,V> [] table;

    /**
     * Sentinel
     */
    private MapEntry<K,V> DEFUNCT = new MapEntry<>(null, null);

    public ProbeHashMap(int cap, int p) {
        super(cap, p);
    }

    public ProbeHashMap(int capacity) {
        super(capacity);
    }

    /**
     * Create an empty table having length equal to current capacity
     */
    @Override
    protected void createTable() {
        table = (MapEntry<K,V>[]) new MapEntry[capacity];
    }

    /**
     * Return true if location is either
     * empty or "defunct" sentinel
     * @param j
     * @return
     */
    private boolean isAvailable(int j){
        return (Objects.isNull(table[j]) || table[j] == DEFUNCT);
    }

    /**
     * Returns index with key k, or -(a+1) such that k could be added
     * at index a
     * @param h
     * @param k
     * @return
     */

    private int findSlot(int h, K k) {
        int avail =  -1;
        int j = h;
        do {
            if(isAvailable(j)){
                if(avail == -1) avail = j;
                if(Objects.isNull(table[j])) break;
            }else if(table[j].getKey().equals(k))
                return j;
            j = (j+1) % capacity;
        }while (j != h);
        return -(avail+1);
    }

    /**
     * Returns value associated with key k in bucket with has value h
     * or else null.
     * @param h
     * @param k
     * @return
     */
    @Override
    protected V bucketGet(int h, K k) {
        int j = findSlot(h, k);
        if(j< 0) return null;
        return table[j].getValue();
    }

    /**
     * Associated key k with value v in bucket with hash value h; returns old value
     * @param h
     * @param k
     * @param v
     * @return
     */
    @Override
    protected V bucketPut(int h, K k, V v) {
        int j = findSlot(h,k);
        if(j >=0)
            return table[j].setValue(v);
        table[-(j+1)] = new MapEntry<>(k,v);
        n++;
        return null;
    }

    /**
     * Removes entry having key k from bucket with hash value h (if any)
     * @param h
     * @param k
     * @return
     */
    @Override
    protected V bucketRemove(int h, K k) {
        int j = findSlot(h,k);
        if(j<0) return null;
        V answer = table[j].getValue();
        table[j] = DEFUNCT;
        n--;
        return answer;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K,V>> buffer = new ArrayList<>();
        for(int h =0; h< capacity; h++){
            if(!isAvailable(h)) buffer.add(table[h]);
        }
        return buffer;
    }
}
