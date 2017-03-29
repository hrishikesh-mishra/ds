package com.hrishikesh.ns.stack;

import java.util.StringJoiner;

/**
 * Problem:
 * Fixed size array based stack
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/fixed-size-array-based-stack/
 */
public class FixedArrayStack<E> implements Stack<E> {

    public static final int DEFAULT_CAPACITY = 16;
    protected int capacity;
    private E[] repository;
    private int top = -1;

    public FixedArrayStack(int capacity) {
        this.capacity = capacity;
        this.repository = (E[]) new Object[capacity];
    }

    public FixedArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void push(E data) {
        if (isStackFull())
            throw new RuntimeException("Stack is full");
        repository[++top] = data;
    }

    @Override
    public E pop() {
        if (isEmpty())
            throw new RuntimeException("Stack is underflow");

        return repository[top--];
    }

    @Override
    public E top() {
        if (isEmpty())
            throw new RuntimeException("Stack is underflow");
        return repository[top];
    }

    @Override
    public int size() {
        return (top + 1);
    }

    @Override
    public boolean isEmpty() {
        return (top == -1);
    }

    @Override
    public boolean isStackFull() {
        return (top == capacity - 1);
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (int i = top; i >= 0; i--)
            joiner.add("" + repository[i]);
        return joiner.toString();
    }
}

class FixedArrayStackTest {

    public static void main(String[] args) {

        Stack stack = new FixedArrayStack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Stack : " + stack);
        System.out.println("Stack pop: " + stack.pop());
        System.out.println("Stack top: " + stack.top());
        System.out.println("Stack : " + stack);
        System.out.println("Stack Size: " + stack.size());
        System.out.println("Is empty: " + stack.isEmpty());
        System.out.println("Is full: " + stack.isStackFull());
    }
}
