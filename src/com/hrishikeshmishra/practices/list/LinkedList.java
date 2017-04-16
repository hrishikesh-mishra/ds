package com.hrishikeshmishra.practices.list;

import java.util.Objects;

/**
 * @author hrishikesh.mishra
 */
public class LinkedList {

    public static class Node {
        private int data;
        private Node next;

        public Node(int d) {
            data = d;
            next = null;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node(" +
                    data +
                    ')';
        }
    }

    public static void print(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println("");
    }

    public static int len(Node node) {
        int counter = 0;
        while (!Objects.isNull(node)) {
            counter++;
            node = node.getNext();
        }
        return counter;
    }
}
