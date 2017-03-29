package com.hrishikesh.practices.list;

import java.util.Objects;

import static com.hrishikesh.practices.list.LinkedList.*;

/**
 * Problem:
 * Reverse a Linked List in groups of given size.
 * ;
 * ;
 * Example:
 * If a linked list is given as 1->2->3->4->5->6->7->8->NULL and k = 3 then,
 * Output will be 3->2->1->6->5->4->8->7->NULL.
 * ;
 * ;
 * Algorithm :
 * - It a variant of {@link ReorderList}, in that k was 2
 * - Set some variable before starting iteration process
 * - - newHead = null, current = node, next, prev, prevPrev = node and bachStartNode;
 * - Iterate till node ! = null
 * - - Set prev = null, counter = 0 and bachStartNode = node
 * - - Iterate till node != null  and counter < k
 * - - - next = node.next
 * - - - node.next = prev
 * - - - prev = node
 * - - - node = next
 * - - if prevPrev != null
 * - - - prevPrev.next = prev
 * - - - PrevPrev = bachStartNode
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/reverse-a-linked-list-in-groups-of-given-size/
 */
public class KReverseList {

    public static Node reverse(Node node, int k) {

        if (Objects.isNull(node) || Objects.isNull(node.getNext())) {
            return node;
        }

        Node newHead = null, current = node, next, prev, prevPrev = node, bachStartNode;

        while (!Objects.isNull(current)) {

            /** Reversing batch of K elements **/
            prev = null;
            int counter = 0;
            bachStartNode = current;
            while (!Objects.isNull(current) && counter++ < k) {
                next = current.getNext();
                current.setNext(prev);
                prev = current;
                current = next;
            }

            /** Setting up new head only one time **/
            if (Objects.isNull(newHead)) {
                newHead = prev;
            } else {
                /** Setting up previous links **/
                prevPrev.setNext(prev);
                prevPrev = bachStartNode;
            }

        }

        return newHead;
    }

}

class KReverseListTest {

    public static void main(String[] args) {
        Node head1 = new Node(1, new Node(2, new Node(3, new Node(4, new Node(5, new Node(6, new Node(7, new Node(8))))))));
        Node head2 = new Node(1, new Node(2, new Node(3, new Node(4, new Node(5, new Node(6, new Node(7, new Node(8))))))));
        Node head3 = new Node(1, new Node(2, new Node(3, new Node(4, new Node(5, new Node(6, new Node(7, new Node(8))))))));

        print(head1);
        head1 = KReverseList.reverse(head1, 2);
        print(head1);
        System.out.println();

        print(head2);
        head2 = KReverseList.reverse(head2, 3);
        print(head2);
        System.out.println();

        print(head3);
        head3 = KReverseList.reverse(head3, 4);
        print(head3);
    }
}