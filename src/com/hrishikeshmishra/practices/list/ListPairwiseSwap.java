package com.hrishikeshmishra.practices.list;

import java.util.Objects;

import static com.hrishikeshmishra.practices.list.LinkedList.*;


/**
 * Problem:
 * Pairwise swap elements of a linked list by swapping data
 * ;
 * Given a singly linked list, write a function to swap elements pairwise.
 * ;
 * For example,
 * if the linked list is 1->2->3->4->5 then the function should change it to 2->1->4->3->5, and
 * if the linked list is 1->2->3->4->5->6 then the function should change it to 2->1->4->3->6->5.
 * ;
 * Algorithm:
 * - Set following pointers
 * - - prev = null
 * - - node = head
 * - - newHead = null
 * - - next = null
 * - - nextOfNext = null
 * - Iterate till node.next != null
 * - - next = node.next
 * - - nextOfNext = next.next
 * - - node.next = node
 * - - if (prev != null ) the pre.next = next
 * - - if (newHead == null) then newHead = next
 * - - prev = node
 * - - node = nextOfNext
 * - Return neHead
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/pairwise-swap-elements-of-a-linked-list-by-swapping-data/
 */
public class ListPairwiseSwap {

    public static Node swapPair(Node head) {
        /** Base case for zero or one element **/
        if (Objects.isNull(head) || Objects.isNull(head.getNext())) {
            return head;
        }

        Node current = head;
        Node newHead = null;
        Node next;
        Node nextNext;
        Node prev = null;

        while (!Objects.isNull(current) && !Objects.isNull(current.getNext())) {
            next = current.getNext();
            nextNext = next.getNext();

            current.setNext(nextNext);
            next.setNext(current);

            if (!Objects.isNull(prev)) {
                prev.setNext(next);
            }

            if (Objects.isNull(newHead)) {
                newHead = next;
            }

            prev = current;
            current = nextNext;
        }
        return newHead;
    }

}

class ListPairwiseSwapTest {
    public static void main(String[] args) {

        /** Two elements **/
        Node head1 = new Node(1, new Node(2));
        print(head1);
        head1 = ListPairwiseSwap.swapPair(head1);
        print(head1);
        System.out.println();


        /** Even elements **/
        Node head2 = new Node(1, new Node(2, new Node(3, new Node(4, new Node(5, new Node(6))))));
        print(head2);
        head2 = ListPairwiseSwap.swapPair(head2);
        print(head2);
        System.out.println();

        /** Odd elements **/
        Node head3 = new Node(1, new Node(2, new Node(3, new Node(4, new Node(5, new Node(6, new Node(7)))))));
        print(head3);
        head3 = ListPairwiseSwap.swapPair(head3);
        print(head3);
    }
}
