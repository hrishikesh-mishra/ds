package com.hrishikesh.dsjava.map_n_heap.core;

import com.hrishikesh.dsjava.head_n_priority_queue.core.Entry;

import java.util.Iterator;

/**
 * Created by hrishikesh.mishra on 26/03/16.
 */
public abstract class AbstractMap<K,V> implements Map<K,V>{

    public boolean isEmpty() {
        return size() == 0 ;
    }

    /** ---------------------------  Nested MapEntry class --------------------------- **/
    protected static class MapEntry<K,V> implements Entry<K,V>{
        private K k;
        private V v;

        public MapEntry(K k, V v) {
            this.k = k;
            this.v = v;
        }

        public K getKey(){
            return k;
        }

        public V getValue(){
            return v;
        }

        public void setKey(K key){
            this.k = key;
        }

        public V setValue(V value){
            V old = v;
            v = value;
            return old;
        }
    }

    /** Support for public KeySet Method **/
    private class KeyIterator implements Iterator<K>{

        private Iterator<Entry<K,V>> entries = entrySet().iterator();

        public boolean hasNext(){
            return entries.hasNext();
        }

        public K next() {
            return entries.next().getKey();
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    private class KeyIterable implements Iterable<K>{

        public Iterator<K> iterator(){
            return new KeyIterator();
        }
    }

    public Iterable<K> keySet(){
        return new KeyIterable();
    }

    /** Support for public values method **/

    private class ValueIterator implements Iterator<V>{
        private Iterator<Entry<K,V>> entries = entrySet().iterator();

        public boolean hasNext() {
            return entries.hasNext();
        }

        @Override
        public V next() {
            return entries.next().getValue();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public class ValueIterable implements Iterable<V>{
        @Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }
    }

    public Iterable<V> values(){
        return new ValueIterable();
    }
}

