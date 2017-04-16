package com.hrishikeshmishra.practices.list;

import java.util.Objects;

import static com.hrishikeshmishra.practices.list.LinkedList.Node;
import static com.hrishikeshmishra.practices.list.LinkedList.print;

/**
 * Problem:
 * Write a function to reverse a linked list
 * ;
 * Solutions:
 * - One solution could be take all element reverse using array until and replace with node value
 * - Second could be, move one step and set node next point to previous node
 * ;
 * Algorithm:
 * - Set previous = null
 * - Iterate till we reached end of list
 * - - temp = node.next
 * - - node.next = previous
 * - - previous = node
 * - - node = temp
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/write-a-function-to-reverse-a-linked-list/
 */
public class ReverseList {

    public Node head;

    public static Node reverseNonRecursive(Node node) {
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


//    public Node reverseRecursive(Node node) {
//
//        if (Objects.isNull(node) || Objects.isNull(node.getNext())) {
//            head = node;
//            return node;
//        }
//
//        Node current = node;
//        Node tempList = reverseRecursive(node.getNext());
//        tempList.setNext(node);
//
//        return current;
//    }

}

class ReverseListTest {

    public static void main(String[] args) {
        Node head = new Node(1, new Node(2, new Node(3, new Node(4))));
        print(head);
        head = ReverseList.reverseNonRecursive(head);
        print(head);

        Node head2 = new Node(1, new Node(2));
        print(head2);
        head2 = ReverseList.reverseNonRecursive(head2);
        print(head2);


    }
}
