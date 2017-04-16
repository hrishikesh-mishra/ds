package com.hrishikeshmishra.practices.list;

import java.util.Objects;

import static com.hrishikeshmishra.practices.list.LinkedList.*;

/**
 * Problem:
 * Delete Middle of Linked List
 * ;
 * If there are even nodes, then there would be two middle nodes, we need to delete the second middle element.
 * ;
 * Algorithm:
 * - Use runner algorithm to find middle
 *
 * @author hrishikesh.mishra
 * @link https://en.wikipedia.org/wiki/Cycle_detection
 * @link http://hrishikeshmishra.com/delete-middle-of-linked-list/
 */
public class ListMiddleRemover {

    public static Node remove(Node node) {

        if (Objects.isNull(node)) {
            return null;
        } else if (Objects.isNull(node.getNext())) {
            return new Node(Integer.MIN_VALUE);
        }

        Node slowPtr = null,
                fastPtr = node;

        while (!Objects.isNull(fastPtr.getNext()) &&
                !Objects.isNull(fastPtr.getNext().getNext())) {

            if (Objects.isNull(slowPtr)) {
                slowPtr = fastPtr;
            } else {
                slowPtr = slowPtr.getNext();
            }
            fastPtr = fastPtr.getNext().getNext();
        }

        if (!Objects.isNull(fastPtr) &&
                !Objects.isNull(fastPtr.getNext())) {
            slowPtr = slowPtr.getNext();
        }

        slowPtr.setNext(slowPtr.getNext().getNext());
        return node;
    }
}


class ListMiddleRemoverTest {

    public static void main(String[] args) {
        Node node = new Node(1, new Node(2, new Node(3, new Node(4))));
        LinkedList.print(node);
        node = ListMiddleRemover.remove(node);
        LinkedList.print(node);

        Node node1 = new Node(1, new Node(2, new Node(3, new Node(4, new Node(5)))));
        LinkedList.print(node1);
        node1 = ListMiddleRemover.remove(node1);
        LinkedList.print(node1);
    }
}