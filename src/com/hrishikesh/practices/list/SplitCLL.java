package com.hrishikesh.practices.list;

import java.util.Objects;

import static com.hrishikesh.practices.list.LinkedList.*;

/**
 * Problem:
 * Split a Circular Linked List
 * Given a Circular Linked List split it into two halves circular lists.
 * If there are odd number of nodes in the given circular linked list then out of the resulting
 * two halved lists, first list should have one node more than the second list.
 * The resultant lists should also be circular lists and not linear lists.
 * ;
 * ;
 * Algorithm:
 * - Find middle of circular linked list by Runner algorithm
 * - Set list2 head by head2 = middle.next
 * - Set null for list1 tail node i.e. middle.next = null
 * - Now it time set null for tail for list2 for this, iterate will temp=head2, till temp.next = head
 * - Set null for list2 tail node i.e. temp.next = null
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/split-a-circular-linked-list/
 */
public class SplitCLL {

    public static Node[] split(Node head) {

        /** Base case for no element **/
        if (Objects.isNull(head)) {
            return null;
        }

        /** Base case for only one element **/
        if (Objects.isNull(head.getNext())) {
            return new Node[]{head, null};
        }

        /** Base case for only two elements **/
        if (Objects.isNull(head.getNext().getNext())) {
            return new Node[]{head, head.getNext()};
        }


        Node middleNode = getMiddleNode(head);
        Node afterMiddleNode = middleNode.getNext();
        middleNode.setNext(null);

        Node temp = afterMiddleNode;
        while (temp.getNext() != head) {
            temp = temp.getNext();
        }
        temp.setNext(null);

        return new Node[]{head, afterMiddleNode};
    }

    private static Node getMiddleNode(Node head) {
        Node slowPtr = head.getNext(),
                fastPrt = head.getNext().getNext();

        while (fastPrt != head && fastPrt.getNext() != head) {
            slowPtr = slowPtr.getNext();
            fastPrt = fastPrt.getNext().getNext();
        }

        return slowPtr;
    }

}

class SplitCLLTest {

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
        node5.setNext(node1);


        Node[] nodes = SplitCLL.split(node1);
        print(nodes[0]);
        print(nodes[1]);
    }
}
