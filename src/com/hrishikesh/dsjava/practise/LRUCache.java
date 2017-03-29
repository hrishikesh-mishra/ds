package com.hrishikesh.dsjava.practise;


import java.util.*;

import static java.lang.Thread.sleep;

/**
 *
 * LRU Cache using queue and hash map.
 *
 * Queue is used to maintain aging of item.
 *
 * Created by hrishikesh.mishra
 */
public class LRUCache<K,V> {

    private final Queue<K> queue;
    private final Map<K,V> map;
    private final int MAX_SIZE;

    public LRUCache(int size) {
        this.MAX_SIZE = size;
        this.queue = new LinkedList<>();
        this.map = new HashMap<>(size);
    }

    /**
     * Add item to cache.
     * @param key
     * @param value
     */
    public void add(K key, V value){

        /** Remove a queue to update aging of item **/
        if(isKeyExists(key))
            queue.remove(key);

        /** remove old item, if cache is full **/
        while(queue.size() >= MAX_SIZE){
            K oldKey = queue.remove();
            map.remove(oldKey);
        }

        /** insert into map & queue **/
        map.put(key, value);
        queue.add(key);
    }

    /**
     * Get item
     * @param key
     * @return
     */
    public V get(K key){
        /** Key doesn't exists. **/
        if(isKeyExists(key))
            return null;

        /** update item age **/
        queue.remove(key); queue.add(key);
        return map.get(key);
    }

    /**
     * Remove item
     * @param key
     * @return
     */
    public V remove(K key){
        /** Key doesn't exists. **/
        if(!isKeyExists(key))
            return null;

        queue.remove(key);
        return map.remove(key);
    }

    private boolean isKeyExists(K key){
        return map.containsKey(key);
    }


    @Override
    public String toString() {
        StringBuilder returnText = new StringBuilder();
        Arrays.asList(queue.toArray()).forEach(x -> {
            returnText.append("[" + x+ "]\n" );
        });
        return returnText.toString();
    }
}


class LRUCacheTest{
    public static void main(String[] args){
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        List<Integer> list = Arrays.asList(new Integer [] {1, 2, 4, 5, 1, 2, 4, 5, 1, 2, 3, 4, 1, 3, 4, 5});
        list.forEach(x -> {
            cache.add(x, String.valueOf(x));
            System.out.println("After insert : " + x + " Queue is : \n" + cache);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}

