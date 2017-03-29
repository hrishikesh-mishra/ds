package com.hrishikesh.ns.queue;

import com.hrishikesh.ns.stack.Stack;

/**
 * <p>
 *     Implement queue using two stack
 *    @link http://hrishikeshmishra.com/implement-queue-using-two-stack/
 * </p>
 * Created by hrishikesh.mishra
 */
public class DoubleQueueStack<E> implements Stack<E> {

    private final Queue<E> queue1;
    private final Queue<E> queue2;

    public DoubleQueueStack() {
        queue1 = new LinkedQueue<>();
        queue2 = new LinkedQueue<>();
    }

    @Override
    public void push(E data) {
        if(queue1.isEmpty())
            queue2.enQueue(data);
        else
            queue1.enQueue(data);
    }

    @Override
    public E pop() {
        if(isEmpty())
            throw new IllegalStateException("Stack is empty");

        int i = 0, queueSize;

        if(!queue1.isEmpty()){
            queueSize = queue1.size();
            while (i++ < (queueSize - 1))
                queue2.enQueue(queue1.deQueue());
            return queue1.deQueue();
        }else {
            queueSize = queue2.size();
            while (i++ < (queueSize - 1))
                queue1.enQueue(queue2.deQueue());
            return queue2.deQueue();
        }
    }

    @Override
    public E top() {
        return null;
    }

    @Override
    public int size() {
        return (queue1.size() + queue2.size());
    }

    @Override
    public boolean isEmpty() {
        return (queue1.isEmpty() && queue2.isEmpty());
    }

    @Override
    public boolean isStackFull() {
        return false;
    }
}

class DoubleQueueStackTest{
    public static void main(String[] args) {
        Stack<Integer> stack = new DoubleQueueStack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Pop :" + stack.pop());
        stack.push(40);
        System.out.println("Pop :" + stack.pop());
        stack.push(50);
        stack.push(60);
        System.out.println("Pop :" + stack.pop());
        System.out.println("Pop :" + stack.pop());
        System.out.println("Pop :" + stack.pop());
    }
}