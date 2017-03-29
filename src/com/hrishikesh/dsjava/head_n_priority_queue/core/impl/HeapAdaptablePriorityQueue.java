package com.hrishikesh.dsjava.head_n_priority_queue.core.impl;

import com.hrishikesh.dsjava.head_n_priority_queue.core.AdaptablePriorityQueue;
import com.hrishikesh.dsjava.head_n_priority_queue.core.Entry;

import java.util.Comparator;

/**
 * An implementation of an adaptable priority queue using an array-based heap.
 *
 * Created by hrishikesh.mishra on 26/03/16.
 */
public class HeapAdaptablePriorityQueue <K,V> extends HeapPriorityQueue<K,V> implements AdaptablePriorityQueue<K,V>{

   /** ------------------------- nested AdaptablePQEntryClass ---------------------------**/
    /**
     * Extension of the PQEntry to include location information
     */
    protected static class AdaptablePQEntry<K,V> extends PQEntry<K,V>{
        private int index;
        public AdaptablePQEntry(K key, V value, int j){
            super(key,value);
            index = j;
        }

        public int getIndex(){
            return index;
        }

        public void setIndex(int j){
            index =j;
        }
    }

    public HeapAdaptablePriorityQueue(Comparator<K> comparator){
        super(comparator);
    }

    /**
     *
     * Validates and entry to ensure it is location-aware
     *
     * @param entry
     * @return
     * @throws IllegalArgumentException
     */
    protected AdaptablePQEntry<K,V> validate(Entry<K,V> entry) throws IllegalArgumentException {
        if(!(entry instanceof AdaptablePQEntry)) throw new IllegalArgumentException("Invalid entry");
        AdaptablePQEntry<K,V> locator = (AdaptablePQEntry<K,V>) entry;
        int j= locator.getIndex();
        if(j >= heap.size() || heap.get(j) != locator) throw new IllegalArgumentException("Invalid entry");
        return locator;
    }

    /**
     * Exchanges the entries at indices i and j of the array list
     * @param i
     * @param j
     */
    protected void swap(int i, int  j){
        super.swap(i, j);
        ((AdaptablePQEntry<K,V>)heap.get(i)).setIndex(i);  // reset entry's index
        ((AdaptablePQEntry<K,V>)heap.get(j)).setIndex(j);  // reset entry's index
    }

    /**
     * Restores the heap property by moving the entry at index j
     * upward / downward
     * @param j
     */
    protected void bubble(int j){
        if(j > 0 && compare(heap.get(j), heap.get(parent(j)))<0) upheap(j);
        else downheap(j);
    }

    /**
     * Inserts a key-value pair
     * and returns the entry created.
     *
     * @param key
     * @param value
     * @return
     */
    public Entry<K,V> insert(K key, V value){
        checkKey(key);
        Entry<K,V> newest = new AdaptablePQEntry<K,V>(key,value,heap.size());
        heap.add(newest);
        upheap(heap.size()-1);
        return newest;
    }

    /**
     * Removes the given entry from the priority queue
     * @param entry
     * @throws IllegalArgumentException
     */
    public void remove(Entry<K,V> entry) throws IllegalArgumentException {
        AdaptablePQEntry<K,V> locator = validate(entry);
        int j = locator.getIndex();
        if(j == heap.size() -1) heap.remove(heap.size()-1);
        else {
            swap(j, heap.size()-1);
            heap.remove(heap.size()-1);
            bubble(j);
        }
    }

    /**
     * Replace the key of an entry
     * @param entry
     * @param key
     * @throws IllegalArgumentException
     */
    public void  replaceKey(Entry<K,V> entry, K key) throws IllegalArgumentException{
        AdaptablePQEntry<K,V> locator = validate(entry);
        checkKey(key);
        locator.setKey(key);
        bubble(locator.getIndex());
    }


    public void replaceValue(Entry<K,V> entry, V value)throws IllegalArgumentException{
        AdaptablePQEntry<K,V> locator = validate(entry);
        locator.setValue(value);
    }



}
