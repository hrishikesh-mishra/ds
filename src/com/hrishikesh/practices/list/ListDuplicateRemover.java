package com.hrishikesh.practices.list;

import java.util.Objects;

import static com.hrishikesh.practices.list.LinkedList.*;

/**
 * Problem:
 * Remove duplicate element from sorted Linked List
 * Write a program which takes a list sorted in non-decreasing order and deletes any duplicate nodes from the list.
 * The list should only be traversed once.
 * ;
 * Algorithm:
 * - Iterate list from start to end
 * - Update each node next with node that is not equal to current node data
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/remove-duplicate-element-from-sorted-linked-list/
 */
public class ListDuplicateRemover {

    public static void remove(Node head) {
        Node temp = head;

        while (!Objects.isNull(temp)) {
            if (Objects.isNull(temp.getNext())) {
                break;
            }
            temp.setNext(getNextNode(temp).getNext());
            temp = temp.getNext();
        }
    }

    public static Node getNextNode(Node node) {

        while (!Objects.isNull(node.getNext()) &&
                node.getData() == node.getNext().getData()) {
            node = node.getNext();
        }
        return node;
    }
}


class ListDuplicateRemoverTest {
    public static void main(String[] args) {
        Node head = new Node(1, new Node(2, new Node(2, new Node(2, new Node(3, new Node(3, new Node(4, new Node(5, new Node(6)))))))));
        LinkedList.print(head);
        ListDuplicateRemover.remove(head);
        LinkedList.print(head);
    }
}