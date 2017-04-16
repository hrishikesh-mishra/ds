package com.hrishikeshmishra.practices.list;

import java.util.Objects;

import static com.hrishikeshmishra.practices.list.LinkedList.*;

/**
 * Problem:
 * Given a linked list, reverse alternate nodes and append at the end
 * ;
 * Given a linked list,performs the following task
 * - Remove alternative nodes from second node
 * - Reverse the removed list.
 * - Append the removed list at the end.
 * ;
 * Example:
 * Input List: 1->2->3->4->5->6
 * After step 1 Linked list are 1>3->5 and 2->4->6
 * After step 2 Linked list are 1->3->5 abd 6->4->2
 * After step 3 Linked List is 1->3->5->6->4->2
 * Output List: 1->3->5->6->4->2
 * ;
 * Algorithm
 * - Remove alternate node
 * - Reverse the second list {@link ReorderList}
 * - For appending move to list1 to end and at end of list1 add the head of list 2
 *
 * @author hrishikesh.mishra
 * http://hrishikeshmishra.com/given-a-linked-list-reverse-alternate-nodes-and-append-at-the-end/
 */

public class ListRemoveAlternativeAndAppend {


    public static void removeSortAppend(Node node) {
        /** Base case validation **/
        if (Objects.isNull(node) || Objects.isNull(node)) {
            return;
        }

        /** Remove alternative nodes **/
        Node removedNodeHead = removeAlternateNode(node);

        /** Reverse removed nodes **/
        Node reversedNodeHead = reverseList(removedNodeHead);

        /** Move to last node **/
        Node lastNode = moveToLastNode(node);

        /** Append reversed node to last node **/
        lastNode.setNext(reversedNodeHead);
    }

    private static Node moveToLastNode(Node node) {
        while (!Objects.isNull(node.getNext())) {
            node = node.getNext();
        }
        return node;
    }

    private static Node reverseList(Node node) {
        Node next, prev = null;
        while (!Objects.isNull(node)) {
            next = node.getNext();
            node.setNext(prev);
            prev = node;
            node = next;
        }
        return prev;
    }

    public static Node removeAlternateNode(Node node) {
        Node removedList = null, removedListHead = null;

        while (!Objects.isNull(node) && !Objects.isNull(node.getNext())) {

            Node next = node.getNext();
            node.setNext(next.getNext());
            node = node.getNext();

            /** Add to removed list **/
            next.setNext(null);
            if (Objects.isNull(removedList)) {
                removedList = next;
                removedListHead = removedList;
            } else {
                removedList.setNext(next);
                removedList = removedList.getNext();

            }
        }
        return removedListHead;
    }


}

class ListRemoveAlternativeAndAppendTest {

    public static void main(String[] args) {
        Node head = new Node(1, new Node(2, new Node(3, new Node(4, new Node(5, new Node(6))))));
        print(head);
        ListRemoveAlternativeAndAppend.removeSortAppend(head);
        print(head);
    }
}



