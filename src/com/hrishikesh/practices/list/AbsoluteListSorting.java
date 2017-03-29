package com.hrishikesh.practices.list;

import java.util.Objects;

import static com.hrishikesh.practices.list.LinkedList.*;

/**
 * Absolute List Sorting
 * ;
 * Given a linked list L of N nodes, sorted in ascending order based on the absolute values of its data.
 * Sort the linked list according to the actual values.
 * ;
 * Ex: Input : 1 -> -2 -> -3 -> 4 -> -5
 * Output: -5 -> -3 -> -2 -> 1 -> 4
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/absolute-list-sorting/
 */
public class AbsoluteListSorting {

    public static Node sort(Node head) {

        if (Objects.isNull(head)) {
            return head;
        }

        Node next = head.getNext(), prev = head;

        while (true) {

            if (Objects.isNull(next)) {
                break;
            }

            /** when smaller element found, move that element to the  begin of list **/
            if (head.getData() > next.getData()) {
                Node temp = new Node(next.getData(), head);
                prev.setNext(next.getNext());
                next.setNext(null);

                head = temp;
                next = temp;
                prev = temp;

            } else {
                prev = next;
            }

            next = next.getNext();
        }
        return head;
    }

}

class AbsoluteListSortingTest {

    public static void main(String[] args) {
        Node head = new Node(1, new Node(-2, new Node(-3, new Node(4, new Node(-5)))));
        print(head);
        head = AbsoluteListSorting.sort(head);
        print(head);
    }
}

