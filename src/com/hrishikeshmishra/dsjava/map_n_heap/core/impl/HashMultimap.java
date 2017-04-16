package com.hrishikeshmishra.dsjava.map_n_heap.core.impl;


import java.util.*;

/**
 * Created by hrishikesh.mishra on 27/03/16.
 */
public class HashMultimap<K,V>   {
    /**
     * The Primary map
     */
    java.util.Map<K, List<V>> map = new HashMap<>();
    int total = 0 ;

    public int size(){
        return total;
    }

    public boolean isEmpty() {
        return total==0;
    }

    public Iterable<V> get(K key) {
        List<V> secondary = map.get(key);
        if(Objects.isNull(secondary))
            return secondary;
        return new ArrayList<>();
    }


    public void put(K key, V value) {
        List<V> secondary = map.get(key);
        if(Objects.isNull(secondary)){
            secondary = new ArrayList<>();
            /** begin using new list as secondary structure **/
            map.put(key, secondary);
        }
        secondary.add(value);
        total++;
    }


    public boolean remove(K key, V value) {
        boolean wasRemoved = false;
        List<V> secondary = map.get(key);
        if(!Objects.isNull(secondary)){
            wasRemoved = secondary.remove(value);
            if(wasRemoved){
                total--;
                if(secondary.isEmpty()){
                    map.remove(key);
                }
            }
        }
        return wasRemoved;
    }

    public Iterable<V> removeAll(K key){
        List<V> secondary = map.get(key);
        if(secondary != null){
            total -= secondary.size();
            map.remove(key);
        }else {
            secondary = new ArrayList<>();
        }
        return secondary;
    }

    public Iterable<Map.Entry<K, V>> entrySet() {
        List<Map.Entry<K,V>> result = new ArrayList<>();
//        for(Map.Entry<K, List<V>> secondary: map.entrySet()){
//            K key = secondary.getKey();
//            for(V value: secondary.getValue()){
//                return result.add(new AbstractMap.SimpleEntry<K,V>(key, value));
//            }
//
//        }
        return result;
    }
}
