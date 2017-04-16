package com.hrishikeshmishra.practices.list;

import java.util.Objects;

import static com.hrishikeshmishra.practices.list.KthNodeFromLast.printKthNodeFromLast;
import static com.hrishikeshmishra.practices.list.LinkedList.*;

/**
 * Problem:
 * Print Kth node from last of linked list.
 * ;
 * ;
 * Algorithm:
 * - If node is null then
 * - - return 0
 * - Recursively call same method with node.next
 * - After call increment node counter
 * - If node counter == k then
 * - - Print node
 * - return node counter
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/kth-node-last-linked-list/
 */
public class KthNodeFromLast {

    public static int printKthNodeFromLast(LinkedList.Node node, int k) {
        if (Objects.isNull(node)) {
            return 0;
        }

        int nodeCounter = printKthNodeFromLast(node.getNext(), k) + 1;

        if (nodeCounter == k) {
            System.out.println("The " + k + "th Node is :" + node.getData());
        }

        return nodeCounter;
    }
}


class KthNodeFromLastTest {

    public static void main(String[] args) {
        Node head = new Node(1, new Node(2, new Node(3, new Node(4, new Node(5)))));
        print(head);
        printKthNodeFromLast(head, 1);
        printKthNodeFromLast(head, 2);
        printKthNodeFromLast(head, 3);
    }
}