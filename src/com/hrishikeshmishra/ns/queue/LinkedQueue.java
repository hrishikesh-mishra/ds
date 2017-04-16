package com.hrishikeshmishra.ns.queue;


import com.hrishikeshmishra.ns.stack.ListNode;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Problem:
 * Linked Queue
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/linked-queue/
 */
public class LinkedQueue<E> implements Queue<E> {

    private ListNode<E> front;
    private ListNode<E> rear;
    private int size;

    @Override
    public void enQueue(E element) {
        ListNode<E> node = new ListNode<E>(element);
        if (isEmpty())
            rear = front = node;
        else {
            rear.setNext(node);
            rear = node;
        }
        size++;
    }

    @Override
    public E deQueue() {
        if (isEmpty())
            throw new IllegalStateException("Queue is empty");

        ListNode<E> node = front;
        front = front.getNext();
        node.setNext(null);
        size--;
        return node.getData();
    }

    @Override
    public E front() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty");
        return front.getData();
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
        if (!isEmpty()) {
            ListNode<E> node = front;
            while (!Objects.isNull(node)) {
                joiner.add(String.valueOf(node.getData()));
                node = node.getNext();
            }
        }
        return joiner.toString();
    }
}

class LinkedQueueTest {
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
