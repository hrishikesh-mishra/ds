package com.hrishikeshmishra.practices.list;

import java.util.Objects;

import static com.hrishikeshmishra.practices.list.LinkedList.*;

/**
 * Problem:
 * Segregate even and odd nodes in a Linked List
 * ;
 * Given a Linked List of integers, write a function to modify the linked list such that all even numbers
 * appear before all the odd numbers in the modified linked list. Also, keep the order of even and odd numbers same.
 * ;
 * Algorithm :
 * - Create two list heads, one for even another for odd.
 * - Iterate given list from start to end
 * - Populate these list based on their types.
 * - At end, append odd list at end of even list
 * - return new head .
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/segregate-even-and-odd-nodes-in-a-linked-list/
 */
public class ListEvenOddSegregator {

    public static Node segregate(Node node) {

        if (Objects.isNull(node) && Objects.isNull(node.getNext())) {
            return node;
        }

        Node evenList = null,
                evenListHead = null,
                oddList = null,
                oddListHead = null;

        /** Create two list to hold even and odd data **/
        while (!Objects.isNull(node)) {
            if (isOdd(node)) {
                if (Objects.isNull(oddList)) {
                    oddList = new Node(node.getData());
                    oddListHead = oddList;
                } else {
                    oddList.setNext(new Node(node.getData()));
                    oddList = oddList.getNext();
                }
            } else {
                if (Objects.isNull(evenList)) {
                    evenList = new Node(node.getData());
                    evenListHead = evenList;
                } else {
                    evenList.setNext(new Node(node.getData()));
                    evenList = evenList.getNext();
                }
            }
            node = node.getNext();
        }

        /** Append odd list at the end of even list **/
        if (Objects.isNull(evenListHead)) {
            return oddListHead;
        } else {
            evenList.setNext(oddListHead);
            return evenListHead;
        }
    }

    private static boolean isOdd(Node node) {
        return node.getData() % 2 != 0;
    }
}

class ListEvenOddSegregatorTest {

    public static void main(String[] args) {
        Node head = new Node(11, new Node(15, new Node(20, new Node(5, new Node(10)))));
        print(head);
        head = ListEvenOddSegregator.segregate(head);
        print(head);
    }
}

