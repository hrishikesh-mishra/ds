package com.hrishikeshmishra.ns.list;

import java.util.Objects;

/**
 * Problem:
 * How to find the starting point of a loop in a linked list.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/how-to-find-the-starting-point-of-a-loop-in-a-linked-list/
 */
public class LoopDetector {

    public ListNode getLoopStartingPoint(ListNode head) {
        boolean isLoopExists = false;
        ListNode slowPtr = head, fastPtr = head;
        while (!Objects.isNull(fastPtr) && !Objects.isNull(fastPtr.getNext())) {
            slowPtr = slowPtr.getNext();
            fastPtr = fastPtr.getNext().getNext();
            if (slowPtr == fastPtr) {
                isLoopExists = true;
                break;
            }
        }

        if (!isLoopExists) return null;
        slowPtr = head;
        while (slowPtr != fastPtr) {
            slowPtr = slowPtr.getNext();
            fastPtr = fastPtr.getNext();
        }
        return slowPtr;
    }
}

class LoopDetectorTest {
    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);
        ListNode node8 = new ListNode(8);
        ListNode node9 = new ListNode(9);

        head.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node7);
        node7.setNext(node8);
        node8.setNext(node9);
        node9.setNext(node4);

        print(head, node9);

        System.out.println("Loop Starting point : " + new LoopDetector().getLoopStartingPoint(head));
    }

    private static void print(ListNode head, ListNode tail) {
        ListNode navigatorNode = head;
        System.out.print("[");
        while (navigatorNode != tail) {
            System.out.print(navigatorNode.getData() + "=>" + navigatorNode.getNext().getData() + ", ");
            navigatorNode = navigatorNode.getNext();
        }
        System.out.print(navigatorNode.getData() + "=>" + navigatorNode.getNext().getData() + ", ");
        System.out.println("]");
    }
}