package com.hrishikesh.practices.list;

import java.util.Objects;

import static com.hrishikesh.practices.list.LinkedList.*;

/**
 * Problem:
 * Merge two sorted linked lists
 * Given two linked lists sorted in ascending order.
 * Merge them in-place and return head of the merged list.
 * For example, if first list is 4->8->12 and second list is 3->6, then the result l
 * ist should be 3->4->6->8->12.
 * It is strongly recommended to do merging in-place using O(1) extra space.
 * ;
 * ;
 * Non-Recursive Algorithm:
 * - If any one of list is empty then return other non-empty
 * - The logic here is, to add all nodes from list 2 to list 1 at their proper place
 * - Repeat bellow tasks - till node2 is not null
 * - There could be two possible case when either node1.data > node2.data or node1.data <= node2.data
 * - If node1.data < node2.data, then move list 1 till we find node1.next == null or node1.next.data < node2.data
 * - Then add node2 at that position and update next pointer properly
 * - If node1.data >= node2.data, then add node2 in front of list 1 and change pointer properly
 * ;
 * ;
 * Recursive Algorithm:
 * - Base case - If any one of list is empty then return other non-empty
 * - If node1.data <= node2.data then, set node1.next pointer with recursive call with node1.next and node2
 * - If node1.data > node2.data then, set node2.next pointer with recursive call with node1 and node.next
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/merge-two-sorted-linked-lists-2/
 */
public class SortedListMerger {

    public static Node merge(Node node1, Node node2) {
        Node newHead = node1, temp;

        /** Base case: Any of null then return not other node **/
        if (Objects.isNull(node1) || Objects.isNull(node2)) {
            return Objects.isNull(node1) ? node2 : node1;
        }


        /** This will add list2 nodes in list1 **/
        /** But this process will also dilute list2 which will uses after **/
        while (!Objects.isNull(node2)) {

            if (node1.getData() < node2.getData()) {
                node1 = moveToNext(node1, node2);
                /** Inserting node2 in between node1 **/
                temp = node1.getNext();
                node1.setNext(node2);
                node2 = node2.getNext();
                node1 = node1.getNext();
                node1.setNext(temp);
            } else {
                /** Inserting node2 in front of node1  **/
                temp = node2.getNext();
                node2.setNext(node1);
                node1 = node2;
                newHead = node1;
                node2 = temp;
            }
        }
        return newHead;
    }

    private static Node moveToNext(Node node1, Node node2) {
        /** Move a head till node1 next is null or data is greater than node2 **/
        while (!Objects.isNull(node1.getNext()) &&
                node1.getNext().getData() < node2.getData()) {
            node1 = node1.getNext();
        }
        return node1;
    }


    public static Node recursiveMerge(Node node1, Node node2) {

        Node temp;

        /** Base case when node1 is null **/
        if (Objects.isNull(node1)) {
            return node2;
        }

        /** Base case when node2 is null **/
        if (Objects.isNull(node2)) {
            return node1;
        }

        if (node1.getData() <= node2.getData()) {
            temp = node1;
            temp.setNext(recursiveMerge(node1.getNext(), node2));
        } else {
            temp = node2;
            temp.setNext(recursiveMerge(node1, node2.getNext()));
        }

        return temp;
    }

}


class SortedListMergerTest {

    public static void main(String[] args) {
        Node node1 = new Node(2, new Node(5, new Node(16, new Node(17))));
        Node node2 = new Node(3, new Node(7, new Node(26)));
        LinkedList.print(node1);
        LinkedList.print(node2);
        Node merge = SortedListMerger.merge(node1, node2);
        LinkedList.print(merge);

        Node node3 = new Node(2, new Node(5, new Node(16, new Node(17))));
        Node node4 = new Node(3, new Node(7, new Node(26)));

        LinkedList.print(node3);
        LinkedList.print(node4);
        Node merge2 = SortedListMerger.recursiveMerge(node3, node4);
        LinkedList.print(merge2);
    }
}


