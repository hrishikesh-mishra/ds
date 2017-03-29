package com.hrishikesh.practices.list;

import java.util.Objects;

import static com.hrishikesh.practices.list.LinkedList.*;

/**
 * Problem:
 * Given a singly linked list, remove all the nodes which have a greater value on right side.
 * ;
 * Example:
 * The list 12->15->10->11->5->6->2->3->NULL should be changed to 15->11->6->3->NULL.
 * Note that 12, 10, 5 and 2 have been deleted because there is a greater value on the right side.
 * When we examine 12, we see that after 12 there is one node with value greater than 12 (i.e. 15),
 * so we delete 12.
 * When we examine 15, we find no node after 15 that has value greater than 15 so we keep this node.
 * When we go like this, we get 15->6->3
 * ;
 * ;
 * In Simple term delete when ith element < (i+1) element
 * ;
 * Algorithm:
 * - Base code:  when list is empty or one element return list
 * - Set following pointer
 * - - node = head
 * - - prev = null
 * - Iterate till node.next != null
 * - - if node.data < node.next.data then do following things
 * - - - if prev == null
 * - - - - head = node.next
 * - - - - node.next = null
 * - - - - node = head
 * - - - else
 * - - - -  prev.next = node.next
 * - - - - node.next = null
 * - - - - node= prev.next
 * - - - else
 * - - - - prev = node
 * - - - - node = node.next
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/given-a-singly-linked-list-remove-all-the-nodes-which-have-a-greater-value-on-right-side/
 */
public class ListRemoveGreaterValueFromRight {

    public static Node remove(Node head) {

        /** Base Case: when list is empty or has only one element **/
        if (Objects.isNull(head) || Objects.isNull(head.getNext())) {
            return head;
        }

        Node newHead = head, node = head, prev = null;
        while (!Objects.isNull(node.getNext())) {
            /** When: ith element < (i+1)th element */
            if (node.getData() < node.getNext().getData()) {
                /** When removing first element **/
                if (Objects.isNull(prev)) {
                    newHead = node.getNext();
                    node.setNext(null);
                    node = newHead;
                } else {
                    prev.setNext(node.getNext());
                    node.setNext(null);
                    node = prev.getNext();
                }
            } else {
                prev = node;
                node = node.getNext();
            }
        }
        return newHead;
    }
}


class ListRemoveGreaterValueFromRightTest {

    public static void main(String[] args) {
        Node head1 = new Node(12, new Node(15, new Node(10, new Node(11, new Node(5, new Node(6, new Node(2, new Node(3))))))));
        print(head1);
        head1 = ListRemoveGreaterValueFromRight.remove(head1);
        print(head1);


        Node head2 = new Node(10, new Node(20, new Node(30, new Node(40, new Node(50, new Node(60))))));
        print(head2);
        head2 = ListRemoveGreaterValueFromRight.remove(head2);
        print(head2);
    }
}