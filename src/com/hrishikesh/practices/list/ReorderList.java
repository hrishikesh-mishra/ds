package com.hrishikesh.practices.list;

import java.util.Objects;

import static com.hrishikesh.practices.list.LinkedList.*;

/**
 * Problem:
 * Reorder List
 * Given a singly linked list: A0→A1→…→An-1→An,
 * reorder it to: A0→An→A1→An-1→A2→An-2→…
 * Given 1->2->3->4->5 its reorder is 1->5->2->4->3.
 * It is recommended do this in-place without altering the nodes' values.
 * ;
 * ;
 * Algorithm:
 * - Divide linked list into two equal parts by using by Runner algorithm {@link SplitCLL }
 * - Reverse the second linked list {@link ReverseList }
 * - Merge these linked list {@link SortedListMerger}
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/reorder-list/
 */
public class ReorderList {

    public static void reorder(Node node) {

        /** When list is empty or only one node. **/
        if (Objects.isNull(node) || Objects.isNull(node.getNext())) {
            return;
        }

        /** Get middle of list **/
        Node middleNode = getMiddleOfList(node);

        /** Break list in two parts **/
        Node firstPartHead = node;
        Node secondPartHead = middleNode.getNext();
        middleNode.setNext(null);

        /** Reverse second part **/
        secondPartHead = reverse(secondPartHead);

        /** Merge second part in first part **/
        merge(firstPartHead, secondPartHead);

    }

    public static void merge(Node node1, Node node2) {
        Node temp;
        while (!Objects.isNull(node2)) {
            temp = node2;
            node2 = node2.getNext();

            temp.setNext(node1.getNext());
            node1.setNext(temp);
            node1 = node1.getNext().getNext();
        }
    }

    public static Node reverse(Node node) {
        if (Objects.isNull(node) || Objects.isNull(node.getNext())) {
            return node;
        }

        Node prev = null, current = node, next;
        while (!Objects.isNull(current)) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        return prev;
    }

    public static Node getMiddleOfList(Node head) {
        Node slowPtr = head, fastPtr = head;

        while (!Objects.isNull(fastPtr) &&
                !Objects.isNull(fastPtr.getNext())) {

            slowPtr = slowPtr.getNext();
            fastPtr = fastPtr.getNext().getNext();
        }
        return slowPtr;
    }

}

class ReorderListTest {

    public static void main(String[] args) {
        Node node = new Node(1, new Node(2, new Node(3, new Node(4, new Node(5)))));
        print(node);
        ReorderList.reorder(node);
        print(node);

        Node node2 = new Node(1, new Node(2, new Node(3, new Node(4, new Node(5, new Node(6))))));
        print(node2);
        ReorderList.reorder(node2);
        print(node2);
    }
}
