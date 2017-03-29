package com.hrishikesh.practices.list;

import java.util.Objects;

import static com.hrishikesh.practices.list.LinkedList.*;

/**
 * Problem:
 * Implement Stack using Linked List
 * ;
 * Algorithm:
 * - In push operation add element at head of list (head = new Node(data, head))
 * - In pop operation, remove element from head of list
 * - -  Node temp = head;
 * - - head = head.getNext();
 * - - temp.setNext(null);
 * - - return temp.getData();
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/implement-stack-using-linked-list/
 */
public class ListStack {

    private Node head;

    public void push(int data) {
        head = new Node(data, head);
    }

    public int pop() {
        if (Objects.isNull(head)) {
            throw new NoSuchFieldError("Stack is underflow");
        }

        Node temp = head;
        head = head.getNext();
        temp.setNext(null);
        return temp.getData();
    }

    public void print() {
        LinkedList.print(head);
    }
}

class ListStackTest {

    public static void main(String[] args) {
        ListStack stack = new ListStack();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.print();

        System.out.println("Pop :" + stack.pop());
        System.out.println("Pop :" + stack.pop());

        stack.push(100);
        stack.push(200);
        stack.push(300);

        System.out.println("Pop :" + stack.pop());

        stack.print();
    }
}
