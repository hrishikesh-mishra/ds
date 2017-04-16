package com.hrishikeshmishra.practices.stack;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Problem:
 * Stack implementation
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/stack-implementation/
 */
public class Stack<E> {

    private Node<E> head;

    public void push(E element) {
        head = new Node<>(element, head);
    }

    public E pop() {
        if (Objects.isNull(head)) {
            throw new NullPointerException("Stack is empty");
        } else {
            Node<E> node = head;
            head = head.getNext();
            node.setNext(null);
            return node.getData();
        }
    }

    public E peek() {
        return Objects.isNull(head) ? null : head.getData();
    }

    public boolean isEmpty() {
        return Objects.isNull(head);
    }

    public int size() {
        int size = 0;
        for (Node<E> temp = head; !Objects.isNull(temp); temp = temp.getNext(), size++) ;
        return size;
    }

    public void clear() {
        head = null;
    }

    @Override
    public String toString() {
        Node<E> temp = head;
        StringJoiner sj = new StringJoiner(",", "[", "]");

        while (!Objects.isNull(temp)) {
            sj.add(String.valueOf(temp.getData()));
            temp = temp.getNext();
        }

        return sj.toString();
    }

}


class StackTest {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Stack : " + stack);
        System.out.println("Pop : " + stack.pop());
        stack.push(40);
        stack.push(50);
        System.out.println("Stack : " + stack);
        System.out.println("Pop : " + stack.pop());
        System.out.println("Pop : " + stack.pop());
        System.out.println("Pop : " + stack.pop());
        System.out.println("Stack : " + stack);
    }
}