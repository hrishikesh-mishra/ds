package com.hrishikeshmishra.practices.queue;

import java.util.StringJoiner;

/**
 * Problem:
 * Array Queue Implementation
 * ;
 * Queue is a linear structure which follows a particular order in which the operations are performed.
 * The order is First In First Out (FIFO).
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/array-queue-implementation/
 *
 */
public class ArrayQueue<E> {

    private E[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public ArrayQueue(int capacity) {
        this.queue = (E[]) new Object[capacity];
        this.capacity = capacity;
        this.front = -1;
        this.rear = -1;
        this.size = 0;
    }

    public void enqueue(E data) {
        if (isFull()) {
            throw new RuntimeException("Queue is full");
        }
        rear = (rear + 1) % capacity;
        queue[rear] = data;
        if (front == -1) {
            front = rear;
        }
        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is full ");
        }

        E data = queue[front];
        front = (front + 1) % capacity;
        size--;

        if (isEmpty()) {
            rear = -1;
            front = -1;
        }

        return data;
    }

    public E rear() {
        return isEmpty() ? null : queue[rear];
    }

    public E front() {
        return isEmpty() ? null : queue[front];
    }


    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");

        if (isEmpty()) {
            return joiner.toString();
        }

        int next = front;
        while (next != rear) {
            joiner.add(String.valueOf(queue[next]));
            next = (next + 1) % capacity;
        }

        joiner.add(String.valueOf(queue[rear]));
        return joiner.toString();


    }
}


class ArrayQueueTest {
    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(4);
        System.out.println("Queue " + queue);
        queue.enqueue(10);
        System.out.println("Queue " + queue);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println("Queue " + queue);
        queue.enqueue(40);
        System.out.println("Queue " + queue);
        System.out.println("DeQueue " + queue.dequeue());
        System.out.println("DeQueue " + queue.dequeue());
        System.out.println("DeQueue " + queue.dequeue());
        System.out.println("Queue " + queue);
        queue.enqueue(50);
        System.out.println("Queue " + queue);
        System.out.println("DeQueue " + queue.dequeue());
        System.out.println("DeQueue " + queue.dequeue());
        System.out.println("Queue " + queue);
        queue.enqueue(60);
        System.out.println("Queue " + queue);




    }
}