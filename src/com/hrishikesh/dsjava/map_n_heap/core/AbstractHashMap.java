package com.hrishikesh.dsjava.map_n_heap.core;

import com.hrishikesh.dsjava.head_n_priority_queue.core.Entry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hrishikesh.mishra on 27/03/16.
 */
public abstract class AbstractHashMap<K,V> extends AbstractMap<K,V> {


    private final static int DEFAULT_PRIME = 109345121;
    private final static int DEFAULT_CAPACITY = 109345121;

    /**
     * Number of entries in the dictionary
     */
    protected int n=0;

    /**
     * length of the table
     */
    protected int capacity;

    /**
     * prime factor
     */
    private int prime;

    /**
     * the shift and scaling factors
     */
    private long scale, shift;


    public AbstractHashMap(int  cap, int p){
        prime = p;
        capacity = cap;

        Random random = new Random();
        scale = random.nextInt(prime-1) +1;
        shift = random.nextInt(prime);
        createTable();
    }

    public AbstractHashMap(int capacity){
        this(capacity, DEFAULT_PRIME);
    }

    public AbstractHashMap(){
        this(DEFAULT_CAPACITY);
    }

    public int size(){
        return n;
    }

    public V get(K key){
        return bucketGet(hashValue(key), key);
    }

    public V remove(K key){
        return bucketRemove(hashValue(key), key);
    }

    public V put(K key, V value){
        V answer = bucketPut(hashValue(key), key, value);
        /** Keep load factory <= 0.5 **/
        if(n > capacity / 2)
            resize(2*capacity -1);  /** (or find a nearby prime) **/

        return answer;
    }

    /**
     *
     * MAD (Multiply-Add-and-Divide)
     * [(ai+b) mod p ] mode N]
     *
     * @param key
     * @return
     */
    private int hashValue(K key){
        return (int) ((Math.abs(key.hashCode()*scale + shift) % prime) % capacity);
    }

    private void resize(int newCapacity){
        List<Entry<K,V>> buffer = new ArrayList<>();
        for(Entry<K,V> e: entrySet()) buffer.add(e);
        capacity = newCapacity;

        /** based on updated capacity **/
        createTable();

        /** will be recomputed while reinserting entries **/
        n = 0;
        for(Entry<K,V> e: buffer)
            put(e.getKey(), e.getValue());
    }

    /**
     * protected abstract methods to be implemented by subclasses
     */
    protected abstract void createTable();
    protected abstract V bucketGet(int h, K k);
    protected abstract V bucketPut(int h, K k, V v);
    protected abstract V bucketRemove(int h, K k);
}

