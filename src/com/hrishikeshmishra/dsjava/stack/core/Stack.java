package com.hrishikeshmishra.dsjava.stack.core;

/**
 *
 * Stack ADT
 *
 * Created by hrishikesh.mishra on 17/01/16.
 */

public interface Stack<E> {

    int size();

    boolean isEmpty();

    void push(E e);

    E top();

    E pop();
}