package com.hrishikesh.practices.list;

import java.util.Objects;

import static com.hrishikesh.practices.list.LinkedList.*;

/**
 * Problem:
 * Intersection of two sorted Linked lists
 * ;
 * Given two lists sorted in increasing order, create a new list representing the intersection of the two lists.
 * The new list should be made with its own memory — the original lists should not be changed.
 * ;
 * For example, let the first linked list be 1->2->3->4->6 and
 * second linked list be 2->4->6->8, then your function should create a third list as 2->4->6.
 * ;
 * ;
 * Basic Logic:
 * - If any of list is empty then return non-empty list
 * - Iterate till both lists nodes are not empty.
 * - If node1.data > node2.data then move of step a head in list2
 * - If node1.data < node2.data then move of step a head in list1
 * - If  node1.data  ==  node2.data, then add data to result list and increase both list pointers
 * ;
 * Note:
 * - Below implementation is little optimised
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/intersection-of-two-sorted-linked-lists/
 */
public class SortedListIntersection {

    public static Node intersection(Node list1, Node list2) {

        if (Objects.isNull(list1) || Objects.isNull(list2)) {
            throw new NullPointerException("One of list is empty.");
        }

        Node resultHead = null, temp = null,
                smallestListHead = list1,
                greatestListHead = list2;


        while (!Objects.isNull(smallestListHead) && !Objects.isNull(greatestListHead)) {

            /** set smallest or largest list **/
            if (smallestListHead.getData() > greatestListHead.getData()) {
                Node temp1 = smallestListHead;
                smallestListHead = greatestListHead;
                greatestListHead = temp1;
            }

            smallestListHead = moveTillNotEqualOrGreater(smallestListHead, greatestListHead.getData());

            /** Special case when smallhead list reached at end **/
            if (Objects.isNull(smallestListHead)) {
                break;

            } else if (smallestListHead.getData() == greatestListHead.getData()) {
                /** When both have same value **/
                temp = addInResult(temp, smallestListHead.getData());

                /** Move till both equal **/
                while (!Objects.isNull(smallestListHead) &&
                        !Objects.isNull(greatestListHead) &&
                        smallestListHead.getData() == greatestListHead.getData()) {
                    smallestListHead = smallestListHead.getNext();
                    greatestListHead = greatestListHead.getNext();
                }
            }

            if (Objects.isNull(resultHead)) {
                resultHead = temp;
            }

        }
        return resultHead;
    }

    private static Node addInResult(Node node, int data) {
        if (Objects.isNull(node)) {
            node = new Node(data);
        } else {
            node.setNext(new Node(data));
            node = node.getNext();
        }
        return node;
    }


    private static Node moveTillNotEqualOrGreater(Node smallestListHead, int value) {
        while (!Objects.isNull(smallestListHead) && smallestListHead.getData() < value) {
            smallestListHead = smallestListHead.getNext();
        }

        return smallestListHead;
    }

}


class SortedListIntersectionTest {

    public static void main(String[] args) {
        Node head1 = new Node(1, new Node(3, new Node(8, new Node(20, new Node(36)))));
        Node head2 = new Node(2, new Node(3, new Node(7, new Node(8, new Node(12, new Node(16, new Node(20, new Node(23))))))));
        print(head1);
        print(head2);

        Node head3 = SortedListIntersection.intersection(head1, head2);
        print(head3);

    }
}