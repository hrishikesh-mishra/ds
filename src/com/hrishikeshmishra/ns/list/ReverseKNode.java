package com.hrishikeshmishra.ns.list;

import java.util.Objects;

/**
 * Problem:
 * For a given K value (K>0) reverse blocks of K nodes in a list.
 * ;
 * Input: 1 2 3 4 5 6 7 8 9 10
 * ;
 * Output:
 * For K = 2:
 * 2 1 4 3 6 5 8 7 10 9
 * ;
 * For K = 3:
 * 3 2 1 6 5 4 9 8 7 10
 * ;
 * For K = 4:
 * 4 3 2 1 8 7 6 5 9 10
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/for-a-given-k-value-k0-reverse-blocks-of-k-nodes-in-a-list/
 */
public class ReverseKNode {

    public ListNode reverseK(ListNode head, int k) {

        ListNode previousCurrent = head,
                previousTail = null,
                current = head;

        while (!Objects.isNull(current)) {
            int counter = 0;
            ListNode tail = null;

            while (counter < k && !Objects.isNull(current)) {
                ListNode next = current.getNext();
                current.setNext(tail);
                tail = current;
                current = next;
                counter++;
            }

            if (Objects.isNull(previousTail))
                head = tail;
            else
                previousTail.setNext(tail);

            previousTail = previousCurrent;
            previousCurrent = current;

        }

        return head;
    }
}

class ReverseKNodeTest {
    public static void main(String[] args) {
        ListNode head = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4,
                                        new ListNode(5,
                                                new ListNode(6,
                                                        new ListNode(7,
                                                                new ListNode(8))))))));
        System.out.print("List: ");
        print(head);

        ReverseKNode reverse = new ReverseKNode();
        head = reverse.reverseK(head, 3);
        System.out.print("\n After reverse List: ");
        print(head);

    }


    private static void print(ListNode navigatorNode) {
        if (Objects.isNull(navigatorNode)) return;
        System.out.print(navigatorNode.getData() + ", ");
        print(navigatorNode.getNext());
    }


}
