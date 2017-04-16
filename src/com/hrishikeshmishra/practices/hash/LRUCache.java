package com.hrishikeshmishra.practices.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Problem :
 * Implement LRU (Least Recently Used) Cache
 * The class has two methods get and
 * set which are defined as follows.
 * get(x)   : Gets the value of the key x if the key exists in the cache otherwise returns -1
 * set(x,y) : inserts the value if the key x is not already present. If the cache reaches
 * its capacity it should invalidate the least recently used value before inserting the new value.
 * In the constructor of the class the size of the cache should be intitialized.
 * ;
 * ;
 * Solution :
 * - LRU can be implemented in various ways like using priority queue etc .
 * - Here is we will implement by using Queue (implemented on DLL) and hashMap bacause we are solving hash questions
 * - - For this we maintain hashmap key will be key and value will be corresponding queue node, it provide direct access
 * - - - of node.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/implement-lru-least-recently-used-cache-using-queue-hash/
 */
public class LRUCache<K, V> {

    /**
     * Queue Nodes as Doubly linked list node
     **/
    private static class QueueNode<K, V> {

        /**
         * Key specially used to cleanup hash map
         */
        private K key;

        /**
         * value of node
         */
        private V value;

        /**
         * Next pointer
         */
        private QueueNode next;

        /**
         * Previous pointer
         */
        private QueueNode previous;

        public QueueNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Modified queue implementation based on Doubly linked list
     **/
    private class Queue<K, V> {
        /**
         * Front of queue
         **/
        private QueueNode front;

        /**
         * Rear of queue
         **/
        private QueueNode rear;

        /**
         * Queue Capacity
         */
        private int capacity;

        /**
         * Current Queue Size
         */
        private int size;

        public Queue(int capacity) {
            this.capacity = capacity;
        }

        /**
         * Enqueue a queue node and return if any node remove due size constraints
         *
         * @param queueNode
         * @return
         */
        public QueueNode enqueue(QueueNode queueNode) {
            QueueNode removedNode = null;

            /** If full then, remove least recent used value **/
            if (isFull()) {
                removedNode = removeLeastRecentUsedItem();
            }

            /** If queue is empty then update front and rear pointer **/
            if (isEmpty()) {
                front = queueNode;
                rear = queueNode;
            } else {
                /** Else update front pointer because it will inserted at front of queue **/
                front.previous = queueNode;
                queueNode.next = front;
                front = queueNode;
            }

            /** Increase size **/
            size++;

            /** Return removed so it will clean from hash map **/
            return removedNode;

        }

        /**
         * Is queue full
         *
         * @return
         */
        private boolean isFull() {
            return capacity == size;
        }

        /**
         * Is queue empty
         *
         * @return
         */
        private boolean isEmpty() {
            return size == 0;
        }

        /**
         * Rear element but that's one is least recently used
         *
         * @return
         */
        private QueueNode removeLeastRecentUsedItem() {
            QueueNode temp = rear;

            /** Move rear pointer to one step previous  **/
            rear = rear.previous;

            /** Remove all link to last node **/
            rear.next.previous = null;
            rear.next = null;

            /** Decrease size **/
            size--;

            return temp;
        }

        /**
         * Move currently accessed to the front of queue
         *
         * @param queueNode
         */
        private void moveItFront(QueueNode queueNode) {

            /** if node is already at front then don't do anything **/
            if (front == queueNode) {
                return;
            }

            /**  Remove this node from list **/
            queueNode.previous.next = queueNode.next;

            /** If node is last node **/
            if (queueNode == rear) {
                rear = queueNode.previous;
            } else {
                queueNode.next.previous = queueNode.previous;
            }

            /** Now add node at front of queue **/
            front.previous = queueNode;
            queueNode.previous = null;
            queueNode.next = front;
            front = queueNode;
        }

        /**
         * String representation of queue from front pointer and rear pointer
         *
         * @return
         */
        @Override
        public String toString() {

            StringJoiner frontJoiner = new StringJoiner(",", "Front[", "], ");
            QueueNode temp = front;

            while (!Objects.isNull(temp)) {
                frontJoiner.add(temp.value.toString());
                temp = temp.next;

            }

            StringJoiner rearJoiner = new StringJoiner(",", "Rear[", "]");
            temp = rear;


            while (!Objects.isNull(temp)) {
                rearJoiner.add(temp.value.toString());
                temp = temp.previous;

            }
            return frontJoiner.toString().concat(rearJoiner.toString());
        }
    }

    /**
     * Queue
     */
    private Queue<K, V> queue;

    /**
     * A relationship between queue node and key
     */
    private Map<K, QueueNode<K, V>> hashMap;

    public LRUCache(int capacity) {
        this.queue = new Queue<>(capacity);
        hashMap = new HashMap<>();
    }

    public V get(K key) {
        /** If value present in queue **/
        if (hashMap.containsKey(key)) {
            QueueNode queueNode = hashMap.get(key);

            /** Move it to front of queue **/
            queue.moveItFront(queueNode);

            return (V) queueNode.value;
        }

        return null;
    }


    public void set(K key, V value) {
        /** If it present in queue or not **/
        if (hashMap.containsKey(key)) {
            /** If present update node value and move it first position **/
            QueueNode queueNode = hashMap.get(key);
            queueNode.value = value;
            queue.moveItFront(queueNode);

        } else {
            /** If not present in queue then add to queue **/

            /** Node to insert in queue **/
            QueueNode<K, V> newNode = new QueueNode(key, value);

            /** enqueue node **/
            QueueNode removedNode = queue.enqueue(newNode);
            hashMap.put(key, newNode);

            /**
             * During enqueue process if any node removed due to queue size constraint then remove it
             * from hashmap as well
             **/
            if (!Objects.isNull(removedNode)) {
                hashMap.remove(removedNode.key);
            }
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}


class LRUCacheTest {
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(4);
        System.out.println(cache);
        cache.set(1, "hrishikesh");
        cache.set(2, "Kumar");
        cache.set(3, "Mishra");
        System.out.println(cache);
        System.out.println(cache.get(2));
        System.out.println(cache);
        cache.set(10, "India");
        System.out.println(cache);
        cache.set(11, "Programming");
        System.out.println(cache);
        cache.set(12, "java");
        System.out.println(cache);
        System.out.println(cache.get(2));
        System.out.println(cache);
        System.out.println(cache.get(2));
        System.out.println(cache);
    }
}