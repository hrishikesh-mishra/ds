package com.hrishikesh.practices.list;

import java.util.Objects;

import static com.hrishikesh.practices.list.LinkedList.*;

/**
 * Problem:
 * Merge two sorted linked lists such that merged list is in reverse order
 * ;
 * Input:  a: 5->10->15->40
 * b: 2->3->20
 * Output: res: 40->20->15->10->5->3->2
 * ;
 * Same as {@link SortedListIntersection}
 * Basic Logic:
 * - If any of list is empty then return non-empty list
 * - Iterate till both lists nodes are not empty.
 * - If node1.data > node2.data then move of step a head in list2
 * - If node1.data < node2.data then move of step a head in list1
 * - If  node1.data  ==  node2.data, then add data to result list and increase both list pointers
 * - When Adding to result list add it to front of list
 *
 * @author hrishikesh.mishra
 * @link hrishikeshmishra.com/merge-two-sorted-linked-lists-such-that-merged-list-is-in-reverse-order/
 */
public class ReverseMerge {

    public Node reserveMerge(Node node1, Node node2) {
        Node result = null;

        while (!Objects.isNull(node1) || !Objects.isNull(node2)) {

            if (!Objects.isNull(node1) && !Objects.isNull(node2)) {
                if (node1.getData() < node2.getData()) {
                    result = getAddToResult(result, node1.getData());
                    node1 = node1.getNext();
                } else {
                    result = getAddToResult(result, node2.getData());
                    node2 = node2.getNext();
                }
            } else if (!Objects.isNull(node1)) {
                result = getAddToResult(result, node1.getData());
                node1 = node1.getNext();
            } else {
                result = getAddToResult(result, node2.getData());
                node2 = node2.getNext();
            }
        }

        return result;
    }

    private Node getAddToResult(Node result, int data) {
        return new Node(data, result);
    }
}


class ReverseMergeTest {

    public static void main(String[] args) {
        Node node1 = new Node(5, new Node(10, new Node(15, new Node(40))));
        Node node2 = new Node(2, new Node(3, new Node(20)));

        ReverseMerge reverseMerge = new ReverseMerge();
        Node node3 = reverseMerge.reserveMerge(node1, node2);

        LinkedList linkedList = new LinkedList();
        System.out.print("List1: ");
        linkedList.print(node1);

        System.out.print("List2: ");
        linkedList.print(node2);

        System.out.print("Reversed Merged List: ");
        linkedList.print(node3);

    }
}