package com.hrishikeshmishra.practices.list;

import java.util.Objects;

import static com.hrishikeshmishra.practices.list.LinkedList.Node;

/**
 * Problem :
 * Remove loop from list
 * ;
 * Algorithm:
 * - Use Runner algorithm to detect loop
 * - - In Runner algorithm we have two pointer slowPtr and fastPtr
 * - Now move slow pointer to head of list and
 * - Iterate one by one node from head by slowPtr and from circle by fastPtr
 * - Both will meet starting starting point circle.
 *
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/remove-loop-from-list/
 */
public class ListLoopRemover {

    public static void remove(Node head) throws Exception {
        Node slowPtr = head,
                fastPtr = head;

        boolean isLoopExist = false;

        while (!Objects.isNull(fastPtr) &&
                !Objects.isNull(fastPtr.getNext())) {

            slowPtr = slowPtr.getNext();
            fastPtr = fastPtr.getNext().getNext();
            if (slowPtr == fastPtr) {
                isLoopExist = true;
                break;
            }
        }

        if (!isLoopExist) {
            throw new Exception("No loop found!");
        }
        slowPtr = head;
        Node prev = null;
        while (slowPtr != fastPtr) {
            prev = fastPtr;
            slowPtr = slowPtr.getNext();
            fastPtr = fastPtr.getNext();
        }

        prev.setNext(null);
    }

}


class ListLoopRemoverTest {

    public static void main(String[] args) throws Exception {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node7);
        node7.setNext(node8);
        node8.setNext(node9);
        node9.setNext(node10);
        node10.setNext(node11);
        node11.setNext(node7);

        ListLoopRemover.remove(node1);

        LinkedList.print(node1);

    }
}




