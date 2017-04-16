package com.hrishikeshmishra.dsjava.head_n_priority_queue.core.impl;


import com.hrishikeshmishra.dsjava.head_n_priority_queue.core.AbstractPriorityQueue;
import com.hrishikeshmishra.dsjava.head_n_priority_queue.core.Entry;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * An implementation of a priority queue using an array-based heap
 *
 * Created by hrishikesh.mishra on 26/03/16.
 */
public class HeapPriorityQueue <K,V> extends AbstractPriorityQueue<K, V> {

    /**
     * Primary collection of priority queue entries
     */
    protected List<Entry<K,V>> heap = new ArrayList<>();

    public HeapPriorityQueue(Comparator<K> comp){
        super(comp);
    }

    /**
     * Creates a priority queue initialized with the given key-values
     * pairs
     *
     * @param keys
     * @param values
     */
    public HeapPriorityQueue(K [] keys, V [] values){
        for(int j =0; j < Math.min(keys.length, values.length); j++){
            heap.add(new PQEntry<K, V>(keys[j], values[j]));
        }

        heapify();
    }

    /**
     * Performs a bottom-up construction of the heap in
     * linear time.
     */
    protected void heapify() {
        int startIndex = parent(size()-1);
        for(int j = startIndex; j>=0; j--)
            downheap(j);
    }

    protected int parent(int j){
        return (j-1)/2;
    }

    protected int left(int j){
        return 2*j+1;
    }

    protected int right(int j){
        return 2*j+2;
    }

    protected boolean hasLeft(int j ){
        return left(j)< heap.size();
    }

    protected boolean hasRight(int j){
        return right(j) < heap.size();
    }


    protected void swap(int i, int j){
        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
     * Moves the entry at index j higher,
     * if necessary, to restore to the heap property.
     *
     * @param j
     */
    protected void upheap(int j){
        while (j > 0){
            int p = parent(j);
            if(compare(heap.get(j), heap.get(p)) >= 0) break;
            swap(j, p);
            j = p;
        }
    }

    /**
     * Moves the entry at index j lower, if necessary,
     * to restore the heap property.
     * @param j
     */
    protected void downheap(int j){
        while (hasLeft(j)){
            int leftIndex = left(j);
            int smallChildIndex = leftIndex;

            if(hasRight(j)){
                int rightIndex = right(j);
                if(compare(heap.get(leftIndex), heap.get(rightIndex)) > 0)
                    smallChildIndex = rightIndex;
            }

            //heap property has been restored
            if(compare(heap.get(smallChildIndex), heap.get(j)) >= 0) break;

            swap(j, smallChildIndex);
            j = smallChildIndex;

        }
    }

    /**
     * Returns the number of items in the priority queue
     * @return
     */
    public int size(){
        return heap.size();
    }

    /**
     * Returns (but doesn't remove) an entry with minimal key (if any).
     * @return
     */
    public Entry<K, V> min(){
        if(heap.isEmpty()) return null;
        return heap.get(0);
    }

    /**
     * Inserts a key-value pair and returns the entry created.
     * @param key
     * @param value
     * @return
     */
    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException{
        checkKey(key);
        Entry<K, V> newest = new PQEntry<>(key, value);
        heap.add(newest);
        upheap(heap.size()-1);
        return newest;
    }

    /**
     * Removes and returns an entry
     * with minimal key (if any).
     * @return
     */
    public Entry<K,V> removeMin(){
        if(heap.isEmpty()) return null;
        Entry<K, V> answer = heap.get(0);
        swap(0, heap.size()-1);
        heap.remove(heap.size()-1);
        downheap(0);
        return answer;
    }

}
