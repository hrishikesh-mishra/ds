package com.hrishikesh.ns.list;

import java.util.Objects;

/**
 * Problem:
 * Find nth node from last in singly linked list in one scan.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/find-nth-node-from-last-in-singly-linked-list-in-one-scan/
 */
public class ListNthFinder {

    /**
     * <p>
     * Find nth node from last in one scan
     * </p>
     *
     * @param list
     * @param n
     * @return
     */
    public ListNode findNthNodeFromEnd(LinkedList list, int n) {
        ListNode nthNodeFromLast = list.getHead(), temp = list.getHead();

        for (int i = 1; i < n && !Objects.isNull(temp); i++)
            temp = temp.getNext();

        if (Objects.isNull(temp))
            throw new IllegalArgumentException("List is smaller than requested length.");

        while (!Objects.isNull(temp.getNext())) {
            nthNodeFromLast = nthNodeFromLast.getNext();
            temp = temp.getNext();
        }

        return nthNodeFromLast;
    }
}

class ListNthFinderTest {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.insertAtEnd(new ListNode(1));
        linkedList.insertAtEnd(new ListNode(2));
        linkedList.insertAtEnd(new ListNode(3));
        linkedList.insertAtEnd(new ListNode(4));
        linkedList.insertAtEnd(new ListNode(5));
        linkedList.insertAtEnd(new ListNode(6));
        linkedList.insertAtEnd(new ListNode(7));
        linkedList.insertAtEnd(new ListNode(8));
        linkedList.insertAtEnd(new ListNode(9));

        System.out.println(linkedList);

        ListNthFinder finder = new ListNthFinder();
        System.out.println("1st from last: " + finder.findNthNodeFromEnd(linkedList, 1));
        System.out.println("2nd from last: " + finder.findNthNodeFromEnd(linkedList, 2));
        System.out.println("9th from last: " + finder.findNthNodeFromEnd(linkedList, 9));
    }
}


