package com.hrishikeshmishra.dsjava.head_n_priority_queue.core;


import java.util.Comparator;

/**
 * Created by hrishikesh.mishra on 25/03/16.
 */
public abstract class AbstractPriorityQueue <K, V> implements PriorityQueue<K, V>{

    /**
     * Nested PQEntry Class
     */
    protected static class PQEntry<K, V> implements Entry<K, V>{

        private K k;
        private V v;

        public PQEntry(K k, V v) {
            this.k = k;
            this.v = v;
        }

        public K getKey() {
            return k;
        }

        public void setKey(K k) {
            this.k = k;
        }

        public V getValue() {
            return v;
        }

        public void setValue(V v) {
            this.v = v;
        }
    }

    /**
     * The comparator defining the ordering of keys in the
     * priority queue
     */
    private Comparator<K> comp;

    protected AbstractPriorityQueue(Comparator<K> c){
        this.comp = c;
    }

    protected AbstractPriorityQueue(){
        this(new DefaultComparator<K>());
    }

    protected int compare(Entry<K,V> a, Entry<K, V> b){
        return comp.compare(a.getKey(), b.getKey());
    }

    protected boolean checkKey(K key) throws IllegalArgumentException{
        try{
            //see if key can be compared to itself
            return comp.compare(key, key) == 0;
        }catch (ClassCastException e){
            throw new IllegalArgumentException("Incompatible key");
        }
    }

    public boolean isEmpty(){
        return size() == 0;
    }

}
