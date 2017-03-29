package com.hrishikesh.ns.list;

import java.util.Objects;

/**
 * Problem:
 * Reverse a singly linked list.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/reverse-a-singly-linked-list/
 */
public class ReverseSinglyLinkedList {

    /**
     * Reverse list
     *
     * @param head
     * @return
     */
    public ListNode reverse(ListNode head) {
        ListNode currentNode = head, previousNode = null, nextNode;

        while (!Objects.isNull(currentNode)) {
            nextNode = currentNode.getNext();
            currentNode.setNext(previousNode);
            previousNode = currentNode;
            currentNode = nextNode;
        }

        return previousNode;
    }


    /**
     * Reverse list in recursive.
     *
     * @param currentNode
     * @param newHead
     */
    public void reverseRecursive(ListNode currentNode, ListNode[] newHead) {
        ListNode nextNode = currentNode.getNext();

        if (Objects.isNull(nextNode)) {
            newHead[0] = currentNode;
            return;
        }

        reverseRecursive(nextNode, newHead);
        nextNode.setNext(currentNode);
        currentNode.setNext(null);
    }
}


class ReverseSinglyLinkedListTest {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        System.out.print("List: ");

        print(head);

        ReverseSinglyLinkedList reverseSinglyLinkedList = new ReverseSinglyLinkedList();
        head = reverseSinglyLinkedList.reverse(head);

        System.out.print("\nList after reverse: ");
        print(head);

        ListNode[] tempNode = new ListNode[1];
        reverseSinglyLinkedList.reverseRecursive(head, tempNode);
        head = tempNode[0];

        System.out.print("\nList after reverse (recursive):");
        print(head);
    }

    public static void print(ListNode head) {
        System.out.print("[");
        while (!Objects.isNull(head)) {
            System.out.print(head.getData() + ", ");
            head = head.getNext();
        }
        System.out.print("]");
    }
}


