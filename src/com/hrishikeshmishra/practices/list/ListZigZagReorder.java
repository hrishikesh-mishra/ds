package com.hrishikeshmishra.practices.list;

import java.util.Objects;

import static com.hrishikeshmishra.practices.list.LinkedList.Node;
import static com.hrishikeshmishra.practices.list.LinkedList.print;

/**
 * Problem:
 * Linked List in Zig-Zag fashion
 * Given a Linked list, rearrange it such that converted list should be of the
 * from a < b > c < d > e < f .. where a, b, c are consecutive data node of linked list and
 * such that the order of linked list is sustained.
 * ;
 * For example:
 * In 11 15 20 5 10 we consider only 11 20 5 15 10
 * because it satisfies the above condition and the order of linked list.
 * 5 20 11 15 10 is not considered as it does not follow the order of linked list.
 * ;
 * ;
 * Algorithm :-
 * - If list is empty or list has only one child then return ;
 * - Set mode = zig
 * - Iterate till node.next != null
 * - - if (mode == zig and node.data > node.next.data) then swap node.data with node.next.data
 * - - if (mode == zag and node.data < node.next.data) then swap node.data with node.next.data
 * - - set mode = (mode== zig) ? zag : zig
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/linked-list-in-zig-zag-fashion/
 */
public class ListZigZagReorder {

    public enum ZigZag {
        /**
         * Zig means <
         **/
        Zig,

        /**
         * Zag means >
         **/
        Zag;
    }

    public static void sortZigZag(Node node) {
        if (Objects.isNull(node) || Objects.isNull(node.getNext())) {
            return;
        }

        ZigZag current = ZigZag.Zig;
        while (!Objects.isNull(node.getNext())) {
            if (isZig(current)) {
                /** If not fulfilling Zig condition then swap value **/
                if (node.getData() > node.getNext().getData()) {
                    swap(node);
                }
            } else {
                /** If not fulfilling Zag condition then swap value **/
                if (node.getData() < node.getNext().getData()) {
                    swap(node);
                }
            }

            current = swap(current);
            node = node.getNext();
        }
    }

    private static void swap(Node node) {
        int temp = node.getData();
        node.setData(node.getNext().getData());
        node.getNext().setData(temp);
    }

    private static ZigZag swap(ZigZag current) {
        return (isZig(current)) ? ZigZag.Zag : ZigZag.Zig;
    }

    private static boolean isZig(ZigZag current) {
        return current == ZigZag.Zig;
    }
}


class ListZigZagReorderTest {
    public static void main(String[] args) {
        Node head = new Node(11, new Node(15, new Node(20, new Node(5, new Node(10)))));
        print(head);
        ListZigZagReorder.sortZigZag(head);
        print(head);
    }
}
