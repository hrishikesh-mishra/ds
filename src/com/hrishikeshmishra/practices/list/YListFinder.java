package com.hrishikeshmishra.practices.list;

import static com.hrishikeshmishra.practices.list.LinkedList.*;

/**
 * Problem:
 * Intersection Point in Y Shaped Linked Lists
 * ;
 * Algorithm:
 * - Find length of both lists
 * - Find larger list (suppose L1) and smaller list (L2), between these lists
 * - Get extra_node = L1 - L2
 * - Now start moving a head in large list by extra_node steps.
 * - After moving extra_node steps in larger list, now start moving both lists
 * - Continue moving to next node till both node are not same.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/intersection-point-in-y-shaped-linked-lists/
 */
public class YListFinder {

    public static Node find(Node list1, Node list2) {
        int list1Len = len(list1),
            list2Len = len(list2);


        Node bigList, smallList;

        if (list1Len > list2Len) {
            bigList = list1;
            smallList = list2;
        } else {
            bigList = list2;
            smallList = list1;
        }


        int extraSize = Math.abs(list1Len - list2Len);

        /** Move out extra node in bigger list **/
        bigList = move(bigList, extraSize);

        /** Move till both list point the same node **/
        while (bigList != smallList) {
            bigList = bigList.getNext();
            smallList = smallList.getNext();
        }

        return smallList;
    }

    private static Node move(Node node, int steps) {
        for (int i = 0; i < steps; ++i) {
            node = node.getNext();
        }
        return node;
    }

}

class YListFinderTest {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node7);
        node7.setNext(node8);
        node8.setNext(node9);


        Node node101 = new Node(101);
        Node node102 = new Node(102);
        node101.setNext(node102);
        node102.setNext(node4);


        print(node1);
        print(node101);

        System.out.println("Y node : " + YListFinder.find(node1, node101));

    }
}
