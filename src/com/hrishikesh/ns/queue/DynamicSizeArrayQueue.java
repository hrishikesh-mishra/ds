package com.hrishikesh.ns.queue;

import java.util.StringJoiner;

/**
 * Problem:
 * Dynamic size array based queue.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/dynamic-size-array-based-queue/
 */
public class DynamicSizeArrayQueue<E> implements Queue<E> {

    public static final int MIN_CAPACITY = 2;
    private int front;
    private int rear;
    private int size;
    private int capacity;
    private E[] repository;


    public DynamicSizeArrayQueue(int capacity) {
        if (capacity < MIN_CAPACITY)
            throw new RuntimeException("Minimum capacity is " + MIN_CAPACITY);

        this.capacity = capacity;
        this.size = 0;
        this.front = 0;
        this.rear = 0;
        this.repository = (E[]) new Object[capacity];
    }

    public DynamicSizeArrayQueue() {
        this(MIN_CAPACITY);
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public void enQueue(E element) {
        if (size == capacity)
            increaseCapacity();

        repository[rear] = element;
        rear = (rear + 1) % capacity;
        size++;
    }

    private void increaseCapacity() {
        int newCapacity = capacity << 1;

        E[] temp = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++)
            temp[i] = repository[(front + i) % capacity];

        repository = temp;
        front = 0;
        rear = size;
        capacity = newCapacity;
    }

    @Override
    public E deQueue() {
        if (isEmpty())
            throw new RuntimeException("Queue is empty.");

        E data = repository[front];
        repository[front] = null;
        front = (front + 1) % capacity;
        size--;
        reduceCapacity();
        return data;
    }

    private void reduceCapacity() {
        int minLimitToReduce = capacity >> 1;

        if (size < minLimitToReduce && size > MIN_CAPACITY) {
            E[] temp = (E[]) new Object[minLimitToReduce];
            for (int i = 0; i < size; i++)
                temp[i] = repository[(front + i) % capacity];

            repository = temp;
            front = 0;
            rear = size;
            capacity = minLimitToReduce;
        }
    }

    @Override
    public E front() {
        if (isEmpty())
            throw new RuntimeException("Queue is empty");
        return repository[front];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        if (!isEmpty())
            for (int j = 0; j < size; j++)
                joiner.add(String.valueOf(this.repository[((front + j) % capacity)]));

        return joiner.toString();
    }
}

class DynamicSizeArrayQueueTest {

    public static void main(String[] args) {
        Queue<Integer> queue = new DynamicSizeArrayQueue<Integer>(4);
        System.out.println("Capacity : " + ((DynamicSizeArrayQueue) queue).getCapacity() + " , Size: " + queue.size());
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(66);
        queue.enQueue(77);
        queue.enQueue(88);
        System.out.println("Capacity : " + ((DynamicSizeArrayQueue) queue).getCapacity() + " , Size: " + queue.size());
        System.out.println("Queue after enqueue (1, 2, 3, 66, 77, 88) : " + queue);
        System.out.println("Queue after dequeue : " + queue.deQueue() + ", Queue: " + queue);
        System.out.println("Queue after dequeue : " + queue.deQueue() + ", Queue: " + queue);
        System.out.println("Capacity : " + ((DynamicSizeArrayQueue) queue).getCapacity() + " , Size: " + queue.size());
        queue.enQueue(4);
        queue.enQueue(5);
        System.out.println("Queue after dequeue : " + queue.deQueue() + ", Queue: " + queue);
        System.out.println("Queue after dequeue : " + queue.deQueue() + ", Queue: " + queue);
        System.out.println("Queue after dequeue : " + queue.deQueue() + ", Queue: " + queue);
        System.out.println("Queue after dequeue : " + queue.deQueue() + ", Queue: " + queue);
        System.out.println("Queue after dequeue : " + queue.deQueue() + ", Queue: " + queue);
        System.out.println("Capacity : " + ((DynamicSizeArrayQueue) queue).getCapacity() + " , Size: " + queue.size());
        queue.enQueue(6);

    }
}
