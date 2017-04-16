package com.hrishikeshmishra.ns.stack;


import java.util.Objects;
import java.util.StringJoiner;

/**
 * Problem:
 * Linked Stack implementation
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/linked-stack-implementation/
 */
public class LinkedStack<E> implements Stack<E> {

    private int length;
    private ListNode<E> top;


    @Override
    public void push(E e) {
        ListNode<E> newNode = new ListNode(e);
        newNode.setNext(top);
        top = newNode;
        length++;
    }

    @Override
    public E pop() {
        if (Objects.isNull(top))
            throw new RuntimeException("Stack is underflow");
        E data = top.getData();
        top = top.getNext();
        length--;
        return data;
    }

    @Override
    public E top() {
        if (Objects.isNull(top))
            throw new RuntimeException("Stack is underflow");
        return (E) top.getData();
    }

    @Override
    public int size() {
        return (length + 1);
    }

    @Override
    public boolean isEmpty() {
        return (length == 0);
    }

    @Override
    public boolean isStackFull() {
        return false;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        ListNode<E> navigatorNode = top;
        while (!Objects.isNull(navigatorNode)) {
            joiner.add(String.valueOf(navigatorNode.getData()));
            navigatorNode = navigatorNode.getNext();
        }
        return joiner.toString();
    }
}

class LinkedStackTest {

    public static void main(String[] args) {
        Stack stack = new LinkedStack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Stack : " + stack);
        System.out.println("Stack pop: " + stack.pop());
        System.out.println("Stack top: " + stack.top());
        System.out.println("Stack : " + stack);
        System.out.println("Stack Size: " + stack.size());
        System.out.println("Is empty: " + stack.isEmpty());
    }
}
