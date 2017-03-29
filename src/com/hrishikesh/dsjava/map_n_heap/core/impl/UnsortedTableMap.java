package com.hrishikesh.dsjava.map_n_heap.core.impl;

import com.hrishikesh.dsjava.map_n_heap.core.AbstractMap;
import com.hrishikesh.dsjava.head_n_priority_queue.core.Entry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by hrishikesh.mishra on 26/03/16.
 */
public class UnsortedTableMap <K,V> extends AbstractMap<K,V>{

    /**
     * Underlying storage for the map of entries.
     */
    private List<MapEntry<K,V>> table = new ArrayList<>();

    /**
     * Returns the index of an entry with equal key,
     * or -1 if none found.
     * @param key
     * @return
     */
    private int findIndex(K key){
        int n = table.size();
        for(int j=0; j<n ; j++)
            if(table.get(j).getKey().equals(key)) return j;

        return -1;
    }

    @Override
    public int size() {
        return table.size();
    }

    @Override
    public V get(K key) {
        int j = findIndex(key);
        if (j == -1) return null;
        return table.get(j).getValue();
    }

    @Override
    public V put(K key, V value) {
        int j = findIndex(key);
        if(j == -1 ) {
            table.add(new MapEntry<K, V>(key, value));
            return null;
        } else
            return table.get(j).setValue(value);
    }

    @Override
    public V remove(K key) {
        int j = findIndex(key);
        int n = size();
        if(j == -1) return null;
        V answer = table.get(j).getValue();
        if( j != n-1)
            table.set(j, table.get(n-1)); // relocate last entry to 'hole' created by removal
        table.remove(n-1);
        return answer;
    }

    /** Support for public entrySet method **/
    private class EntryIterator implements Iterator<Entry<K,V>>{
        private int j=0;
        @Override
        public boolean hasNext() {
            return j < table.size();
        }

        @Override
        public Entry<K, V> next() {
            if(j == table.size()) throw  new NoSuchElementException();
            return table.get(j++);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    private class EntryIterable implements Iterable<Entry<K,V>>{

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }
    }

    public Iterable<Entry<K,V>> entrySet(){
        return new EntryIterable();
    }
}
