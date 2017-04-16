package com.hrishikeshmishra.dsjava.map_n_heap.core.impl;

import com.hrishikeshmishra.dsjava.map_n_heap.core.AbstractHashMap;
import com.hrishikeshmishra.dsjava.head_n_priority_queue.core.Entry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by hrishikesh.mishra on 27/03/16.
 */
public class ChainHashMap<K,V> extends AbstractHashMap<K,V> {

    /**
     * initialized within create table
     */
    private UnsortedTableMap<K,V> [] table;

    public ChainHashMap(int cap){
        super(cap);
    }

    public ChainHashMap(int cap, int p){
        super(cap, p);
    }

    /**
     * Create an empty table having length
     * equal to current capacity
     */
    protected void createTable(){
        table = (UnsortedTableMap<K,V>[]) new UnsortedTableMap[capacity];
    }

    /**
     * Returns value associated with key k in bucket with hash value h,
     * or else null
     * @param h
     * @param k
     * @return
     */
    @Override
    protected V bucketGet(int h, K k) {
        UnsortedTableMap<K,V> bucket = table[h];
        if(Objects.isNull(bucket)) return null;
        return bucket.get(k);
    }

    /**
     * Associates key k with value v in bucket with hash value h;
     * return old value
     *
     * @param h
     * @param k
     * @param v
     * @return
     */
    @Override
    protected V bucketPut(int h, K k, V v) {
        UnsortedTableMap<K,V> bucket = table[h];
        if(Objects.isNull(bucket))
            bucket = table[h] = new UnsortedTableMap<>();

        int oldSize = bucket.size();
        V answer = bucket.put(k,v);
        n += (bucket.size() - oldSize);
        return answer;
    }

    /**
     * Removes entry having key k from bucket
     * with hash value h (if any);
     * @param h
     * @param k
     * @return
     */
    @Override
    protected V bucketRemove(int h, K k) {
        UnsortedTableMap<K,V> bucket = table[h];
        if(Objects.isNull(bucket)) return null;

        int oldSize = bucket.size();
        V answer = bucket.remove(k);
        n -= (oldSize - bucket.size());
        return answer;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K,V>> buffer = new ArrayList<>();
        for(int h =0; h <capacity; h++ ){
            if(!Objects.isNull(table[h])){
                for(Entry<K,V> entry : table[h].entrySet()){
                    buffer.add(entry);
                }
            }
        }
        return buffer;
    }
}
