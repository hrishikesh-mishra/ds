package com.hrishikesh.ns.queue;

import com.hrishikesh.ns.stack.LinkedStack;
import com.hrishikesh.ns.stack.Stack;


/**
 *  D - Problem
 * Implement queue using double stack.
 *
 * We use two stack.
 *  1. we'll push all element to another stack1 for enqueue
 *  2. For dequeue, pop from stack2 if stack2 is not empty, if its empty
 *      then, we push all element from stack1 to stack2.
 *
 *
 * @link http://hrishikeshmishra.com/implement-queue-using-double-stack/
 * </p>
 *
 * Created by hrishikesh.mishra
 */
public class DoubleStackQueue<E> implements Queue<E> {

    private final Stack<E> stack1;
    private final Stack<E> stack2;

    public DoubleStackQueue() {
        stack1 = new LinkedStack<>();
        stack2 = new LinkedStack<>();
    }

    @Override
    public void enQueue(E element) {
        stack1.push(element);
    }

    @Override
    public E deQueue() {
        if(isEmpty())
            throw new IllegalStateException("Queue is empty.");

        moveElements();
        return stack2.pop();
    }

    @Override
    public E front() {
        if(isEmpty())
            throw new IllegalStateException("Queue is empty.");

        moveElements();
        return stack2.top();
    }

    private void moveElements(boolean isForceMove) {
        if(stack2.isEmpty() || isForceMove)
            while (!stack1.isEmpty())
                stack2.push(stack1.pop());
    }

    private void moveElements() {
        moveElements(false);
    }

    @Override
    public int size() {
        return stack1.size() + stack2.size();
    }

    @Override
    public boolean isEmpty() {
        return (stack1.isEmpty() && stack2.isEmpty());
    }
}

class DoubleStackQueueTest{

    public static void main(String[] args) {
        Queue<Integer> queue = new DoubleStackQueue<>();
        queue.enQueue(1);
        queue.enQueue(2);
        System.out.println("Dequeue: " + queue.deQueue());
        queue.enQueue(3);
        queue.enQueue(4);
        System.out.println("Dequeue: " + queue.deQueue());
        System.out.println("Dequeue: " + queue.deQueue());
        System.out.println("Dequeue: " + queue.deQueue());
    }
}
