package com.hrishikeshmishra.practices.list;

import java.util.Objects;

import static com.hrishikeshmishra.practices.list.LinkedList.*;

/**
 * Problem:
 * Sorted insert for circular linked list
 * Given a sorted circular linked list, insert a newnode so that it remains a sorted circular linked list.
 * ;
 * Algorithm:
 * - Iterate through the list and
 * - Find position to insert
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/sorted-insert-for-circular-linked-list/
 */
public class InsertInSortedCLL {

    private Node head;

    public void insertSorted(int data) {

        /** 1. when head is empty **/
        if (Objects.isNull(head)) {
            head = new Node(data);
            head.setNext(head);
            return;
        }

        /**
         * Special case : when new element is less than head element
         * so insert before head and change head pointer
         */
        if (head.getData() > data) {
            Node lastNode = findLastNode();
            head = new Node(data, head);
            lastNode.setNext(head);
            return;
        }

        Node position = findPosition(data);
        Node next = position.getNext();
        position.setNext(new Node(data, next));
    }


    public Node findPosition(int data) {
        Node node = head.getNext(), prev = head;
        while (node != head) {
            if (node.getData() > data) {
                break;
            }
            prev = node;
            node = node.getNext();
        }
        return prev;
    }

    public void print() {
        Node node = head;

        if (!Objects.isNull(node)) {
            System.out.print(node.getData() + " ");
            node = node.getNext();
        }

        while (node != head) {
            System.out.print(node.getData() + " ");
            node = node.getNext();
        }

        System.out.println();
    }


    public Node findLastNode() {
        Node node = head.getNext();
        while (node.getNext() != head) {
            node = node.getNext();
        }

        return node;
    }

}

class InsertInSortedCLLTest {

    public static void main(String[] args) {
        InsertInSortedCLL cll = new InsertInSortedCLL();
        cll.insertSorted(18);
        cll.insertSorted(20);
        cll.insertSorted(10);
        cll.print();
        cll.insertSorted(2);
        cll.insertSorted(12);
        cll.insertSorted(32);
        cll.print();


    }
}
