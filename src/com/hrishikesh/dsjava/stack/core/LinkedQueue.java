package com.hrishikesh.dsjava.stack.core;

/**
 * Created by hrishikesh.mishra on 11/02/16.
 */
public class LinkedQueue<E> implements Queue<E>{

    private SinglyLinkedList<E> list = new SinglyLinkedList<>();

    @Override
    public int size() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        list.addLast(e);
    }

    @Override
    public E first() {
        return list.first();
    }

    @Override
    public E dequeue() {
        return list.removeFist();
    }
}
