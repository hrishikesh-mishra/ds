package com.hrishikesh.practices.list;

import java.util.Objects;

import static com.hrishikesh.practices.list.LinkedList.*;

/**
 * Problem:
 * Delete without head pointer
 * ;
 * You are given a pointer/reference to a node to be deleted in a linked list.
 * The task is to delete the node.  Pointer/reference to head node is not given.
 * ;
 * You may assume that the node to be deleted is not the last node.
 * ;
 * Algorithm:
 * - Iterate list from given node till node.next != null
 * - - During each iteration node.data = node.next.data
 * - - Set previousNode = node
 * - - Set node = node.get
 * - Set previousNode.next = null
 *
 * @author hrishikesh.mishra
 * @link hrishikeshmishra.com/delete-without-head-pointer/
 */
public class ListDeleteNodeWithoutHead {

    public static void removeWithoutHead(Node node) {

        Node prev = null;
        while (!Objects.isNull(node.getNext())) {
            node.setData(node.getNext().getData());
            prev = node;
            node = node.getNext();
        }

        prev.setNext(null);
    }
}


class ListDeleteNodeWithoutHeadTest {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);


        print(node1);


        ListDeleteNodeWithoutHead.removeWithoutHead(node3);

        print(node1);

        ListDeleteNodeWithoutHead.removeWithoutHead(node3);

        print(node1);


    }
}

