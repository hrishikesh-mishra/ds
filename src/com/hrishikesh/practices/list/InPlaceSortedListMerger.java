package com.hrishikesh.practices.list;

import java.util.Objects;

import static com.hrishikesh.practices.list.LinkedList.*;

/**
 * Problem:
 * Merge two sorted linked lists
 * It is strongly recommended to do merging in-place using O(1) extra space.
 * ;
 * Algorithm :
 * - Base case : If any one of list is empty return node empty list head
 * - Here we will add all node from list2 to list 1
 * - Iterate till node2 != null
 * - - Get position for node2 in list1
 * - - Create a temporary node with node2 and add this node in list1 at above said postion
 * - - Move node2 pointer to next element in list2
 * - Return list1 head
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/merge-two-sorted-linked-lists/
 */
public class InPlaceSortedListMerger {

    public static Node merge(Node node1, Node node2) {

        if (Objects.isNull(node1) && Objects.isNull(node2)) {
            return null;
        } else if (Objects.isNull(node1)) {
            return node2;
        } else if (Objects.isNull(node2)) {
            return node1;
        }

        /** Making sure that node1 start with smaller value **/
        if (node2.getData() < node1.getData()) {
            Node temp = node2;
            node2 = node1;
            node1 = temp;
        }

        Node head = node1;
        while (!Objects.isNull(node2)) {
            node1 = getNextPlaceToAdd(node1, node2.getData());
            Node temp = new Node(node2.getData(), node1.getNext());
            node1.setNext(temp);
            node2 = node2.getNext();
        }

        return head;
    }

    private static Node getNextPlaceToAdd(Node node, int data) {

        while (true) {

            /** When first list reaches at end. **/
            if (Objects.isNull(node.getNext())) {
                return node;
            }

            if (node.getNext().getData() > data) {
                return node;
            }

            node = node.getNext();
        }
    }
}

class InPlaceSortedListMergerTest {

    public static void main(String[] args) {
        Node node1 = new Node(3, new Node(8, new Node(12, new Node(18))));
        LinkedList.print(node1);

        Node node2 = new Node(6, new Node(9, new Node(10, new Node(30, new Node(39, new Node(40))))));
        LinkedList.print(node2);

        Node merge = InPlaceSortedListMerger.merge(node2, node1);
        LinkedList.print(merge);
    }

}
