package com.hrishikesh.ns.list;

import java.util.Objects;

/**
 * Problem:
 * Split a circular linked List into two equal parts, if the number of nodes in list are odd then make first list
 * one node extra than second list.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/split-a-circular-linked-list-into-two-equal-parts/
 */
public class CircularListSplitter {

    public void split(ListNode head, ListNode head1, ListNode head2) {
        /** In case, empty list **/
        if (Objects.isNull(head)) {
            return;
        }

        /** Finding middle of list. **/
        ListNode fastPtr = head, slowPtr = head;
        while (fastPtr.getNext() != head
                && fastPtr.getNext().getNext() != head) {
            slowPtr = slowPtr.getNext();
            fastPtr = fastPtr.getNext().getNext();
        }

        /**
         * In case of even nodes,
         * fastPtr has to move till last node (just a node before head node)
         */
        if (fastPtr.getNext().getNext() == head)
            fastPtr = fastPtr.getNext();

        /** Building 1st sub-circular list **/
        head1.setNext(head);
        fastPtr.setNext(slowPtr.getNext());


        /** Building 2nd sub-circular list **/

        /** Checking for only one node in list **/
        if (head.getNext() != head)
            head2.setNext(slowPtr.getNext());

        slowPtr.setNext(head);
        return;
    }

}

class CircularListSplitterTest {

    public static void main(String[] args) {
        /** When list has even number of nodes **/
        testEvenLengthNode();

        /** When list has even number of nodes **/
        testOddLengthNode();
    }

    private static void testEvenLengthNode() {
        ListNode head = new ListNode(1);
        head.setNext(new ListNode(2,
                new ListNode(3,
                        new ListNode(4, head))));

        System.out.print("\nMain List: ");
        print(head);

        ListNode head1 = new ListNode(0),
                head2 = new ListNode(0);

        CircularListSplitter splitter = new CircularListSplitter();
        splitter.split(head, head1, head2);

        head1 = head1.getNext();
        head2 = head2.getNext();

        System.out.print("\n1st Sub List : ");
        print(head1);

        System.out.print("\n2nd Sub List : ");
        print(head2);
    }

    private static void testOddLengthNode() {
        ListNode head = new ListNode(1);
        head.setNext(new ListNode(2,
                new ListNode(3,
                        new ListNode(4,
                                new ListNode(5, head)))));

        System.out.print("\n\nMain List: ");
        print(head);

        ListNode head1 = new ListNode(0),
                head2 = new ListNode(0);

        CircularListSplitter splitter = new CircularListSplitter();
        splitter.split(head, head1, head2);

        head1 = head1.getNext();
        head2 = head2.getNext();

        System.out.print("\n1st Sub List : ");
        print(head1);

        System.out.print("\n2nd Sub List : ");
        print(head2);
    }


    private static void print(ListNode navigatorNode, ListNode head) {
        if (Objects.isNull(navigatorNode)) return;
        System.out.print(navigatorNode.getData() + ", ");
        if (navigatorNode.getNext() != head)
            print(navigatorNode.getNext(), head);
    }

    private static void print(ListNode head) {
        print(head, head);
    }
}


