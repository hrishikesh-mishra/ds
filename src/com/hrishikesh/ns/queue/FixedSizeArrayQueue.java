package com.hrishikesh.ns.queue;

import java.util.StringJoiner;

/**
 * Problem
 * Fixed size array queue using circular array to optimize memory.
 *
 * @author hrishikesh.mishra
 * @link  http://hrishikeshmishra.com/fixed-size-array-based-queue/
 */
public class FixedSizeArrayQueue<E> implements Queue<E> {

    private E[] repository;
    private int front, rear;
    private int size;
    private int capacity;

    public FixedSizeArrayQueue(int capacity) {
        this.size = 0;
        this.rear = 0;
        this.front = 0;
        this.capacity = capacity;
        this.repository = (E[]) new Object[capacity];
    }

    public FixedSizeArrayQueue() {
        this(8);
    }

    @Override
    public void enQueue(E element) {
        if (size == repository.length)
            throw new RuntimeException("Queue is full.");

        this.repository[this.rear] = element;
        this.rear = (this.rear + 1) % capacity;
        this.size++;
    }

    @Override
    public E deQueue() {
        if (this.size == 0)
            throw new RuntimeException("Queue is empty.");

        E data = this.repository[this.front];
        this.repository[this.front] = null;
        this.front = (this.front + 1) % capacity;
        this.size--;
        return data;
    }

    @Override
    public E front() {
        if (this.size == 0)
            throw new RuntimeException("Queue is empty.");

        return this.repository[this.front];
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

class FixedSizeArrayQueueTest {
    public static void main(String[] args) {
        Queue<Integer> queue = new FixedSizeArrayQueue<Integer>(4);
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        System.out.println("Queue after enqueue (1, 2, 3) : " + queue);
        System.out.println("Queue after dequeue : " + queue.deQueue() + ", Queue: " + queue);
        System.out.println("Queue after dequeue : " + queue.deQueue() + ", Queue: " + queue);
        queue.enQueue(4);
        queue.enQueue(5);
        System.out.println("Queue after dequeue : " + queue.deQueue() + ", Queue: " + queue);
        queue.enQueue(6);
        System.out.println("Queue after dequeue : " + queue.deQueue() + ", Queue: " + queue);
        System.out.println("Queue after dequeue : " + queue.deQueue() + ", Queue: " + queue);
        System.out.println("Queue after dequeue : " + queue.deQueue() + ", Queue: " + queue);
    }
}