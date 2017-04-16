package com.hrishikeshmishra.ns.stack;

import java.util.StringJoiner;

/**
 * Problem:
 * Dynamic array stack, which will auto shrink & grow.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/dynamic-array-stack/
 */
public class DynamicArrayStack<E> implements Stack<E> {

    public static final int DEFAULT_CAPACITY = 4;
    protected int capacity;
    private E[] repository;
    private int top = -1;

    public DynamicArrayStack(int capacity) {
        this.capacity = capacity;
        this.repository = (E[]) new Object[capacity];
    }

    public DynamicArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void push(E data) {
        if (isStackFull()) increaseStackSize();
        repository[++top] = data;
    }

    @Override
    public E pop() {
        if (isEmpty())
            throw new RuntimeException("Stack is underflow");

        E data = repository[top--];
        shrinkStack();
        return data;
    }

    /**
     * Increase size of stack by double
     */
    private void increaseStackSize() {
        capacity <<= 1;
        E[] temp = (E[]) new Object[capacity];
        System.arraycopy(repository, 0, temp, 0, top + 1);
        repository = temp;
    }

    /**
     * Shrink Stack
     */
    private void shrinkStack() {
        if (!isShrinkRequired()) return;
        capacity >>= 2;
        E[] temp = (E[]) new Object[capacity];
        System.arraycopy(repository, 0, temp, 0, top + 1);
        repository = temp;
    }

    private boolean isShrinkRequired() {
        int minSize = capacity >> 2;
        return (size() < minSize && size() < DEFAULT_CAPACITY);
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

    public int getCapacity() {
        return capacity;
    }
}


class DynamicArrayStackTest {

    public static void main(String[] args) {
        Stack stack = new DynamicArrayStack();
        System.out.println("Capacity:  " + ((DynamicArrayStack) stack).getCapacity());
        stack.push(1);
        stack.push(2);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        System.out.println("Stack : " + stack);
        System.out.println("Capacity:  " + ((DynamicArrayStack) stack).getCapacity());
        System.out.println("Stack pop: " + stack.pop());
        System.out.println("Stack pop: " + stack.pop());
        System.out.println("Stack pop: " + stack.pop());
        System.out.println("Stack pop: " + stack.pop());
        System.out.println("Stack : " + stack);
        System.out.println("Capacity:  " + ((DynamicArrayStack) stack).getCapacity());
    }
}