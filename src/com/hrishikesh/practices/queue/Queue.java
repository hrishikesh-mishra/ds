package com.hrishikesh.practices.queue;

import com.hrishikesh.practices.stack.Node;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Problem:
 * List Queue implementation
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/list-queue-implementation/
 */
public class Queue<E> {
    private Node<E> front;
    private Node<E> rear;

    public void enqueue(E data) {
        Node<E> temp = new Node<>(data);

        if (Objects.isNull(rear)) {
            rear = temp;
            front = rear;
        } else {
            rear.setNext(temp);
            rear = rear.getNext();
        }
    }

    public E dequeue() {
        if (Objects.isNull(front)) {
            throw new RuntimeException("Queue is empty");
        }

        Node<E> temp = front;
        front = front.getNext();
        temp.setNext(null);

        /** Special case when no data **/
        if (Objects.isNull(front)) {
            rear = null;
        }
        return temp.getData();
    }

    public boolean isEmpty() {
        return Objects.isNull(front);
    }

    public int getSize() {
        int size = 0;
        Node<E> temp = front;
        while (!Objects.isNull(temp)) {
            size++;
            temp = temp.getNext();
        }

        return size;
    }

    @Override
    public String toString() {
        Node<E> temp = front;
        StringJoiner joiner = new StringJoiner("',", "[", "]");

        while (!Objects.isNull(temp)) {
            joiner.add(String.valueOf(temp.getData()));
            temp = temp.getNext();
        }
        return joiner.toString();
    }
}

class QueueTest {
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
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