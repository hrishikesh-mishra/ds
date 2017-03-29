package com.hrishikesh.practices.list;

import java.util.Objects;

import static com.hrishikesh.practices.list.QuickSortUsingDLL.*;

/**
 * Problem:
 * QuickSort on Doubly Linked List
 * The quick sort uses divide and conquer to gain the same advantages as the merge sort,
 * while not using additional storage. As a trade-off, however, it is possible that the
 * list may not be divided in half.
 * ;
 * Algorithm:
 * - Quick Sort Method
 * - - Get a pivot element from list or array by partitioning algorithm
 * - - Recursive call quick sort method for first part partition which is less than pivot and
 * - - Recursive call quick sort method for half part partition which is greater than or equal to pivot
 * - Partition Method
 * - - For partition divide list or array in three buckets, that is
 * - - - element less than pivot  (handled by i)
 * - - - element equal to pivot  (handled by pivot)
 * - - - element greater than pivot  (handled by j)
 * - - Set values
 * - - - Pivot = high
 * - - - i = null
 * - - - j = low
 * - - iterate below code till j != high
 * - - - if list.i < list.pivot then
 * - - - - i = (i == null) ?  low : i.next
 * - - - - now swap ith data with jth data
 * - - - j = j.next
 * - - if list.i < list.pivot then
 * - - -  i = (i == null) ?  low : i.next
 * - - -  now swap ith data with jth data
 * - - return i
 *
 * @author hrishikesh.mishra
 * @link https://www.youtube.com/watch?v=MZaf_9IZCrc
 * @link https://en.wikipedia.org/wiki/Quicksort
 * @link http://hrishikeshmishra.com/quicksort-on-doubly-linked-list/
 */
public class QuickSortUsingDLL {

    public static class Node {
        private int data;
        private Node prev;
        private Node next;

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node(" + data + ')';
        }
    }

    public static void sort(Node head) {
        Node first = head;
        Node last = getLast(head);
        quickSort(first, last);
    }

    private static void quickSort(Node low, Node high) {
        /**
         * Partioning till
         *  - high is out of boundary and
         *  - low not equal high means no element and
         *  - has more than one element
         */
        if (!Objects.isNull(high) &&
                low != high && low != high.getNext()) {

            Node pivot = partition(low, high);
            quickSort(low, pivot.getPrev());
            quickSort(pivot.getNext(), high);
        }
    }

    /**
     * <p>
     * It works on three variable, i , j and pivot
     * |------------| pivot |----------------|
     * i                   j
     * It has three bucket,
     * - element less than pivot  (handled by i)
     * - element equal to pivot  (handled by pivot)
     * - element greater than pivot  (handled by j)
     * </p>
     *
     * @param low
     * @param high
     * @return
     */
    private static Node partition(Node low, Node high) {
        Node i = null,
                j = low,
                pivot = high;
        int temp;

        for (; j != high; j = j.getNext()) {
            if (pivot.getData() > j.getData()) {
                i = (Objects.isNull(i)) ? low : i.getNext();
                temp = i.getData();
                i.setData(j.getData());
                j.setData(temp);
            }
        }

        i = (Objects.isNull(i)) ? low : i.getNext();
        temp = i.getData();
        i.setData(pivot.getData());
        pivot.setData(temp);
        return i;


    }

    public static Node getLast(Node node) {
        while (!Objects.isNull(node.getNext())) {
            node = node.getNext();
        }
        return node;
    }

    public static void print(Node node, boolean isForward) {
        while (!Objects.isNull(node)) {
            System.out.print(node.getData() + " ");
            node = (isForward) ? node.getNext() : node.getPrev();
        }
        System.out.println("");
    }

}


class QuickSortUsingDLLTest {


    public static void main(String[] args) {
        Node node1 = new Node(10);
        Node node2 = new Node(2);
        Node node3 = new Node(6);
        Node node4 = new Node(49);
        Node node5 = new Node(5);
        Node node6 = new Node(12);
        Node node7 = new Node(7);
        Node node8 = new Node(19);

        /** Setting next pointer **/
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node7);
        node7.setNext(node8);

        /** Setting prev pointer **/
        node2.setPrev(node1);
        node3.setPrev(node2);
        node4.setPrev(node3);
        node5.setPrev(node4);
        node6.setPrev(node5);
        node7.setPrev(node6);
        node8.setPrev(node7);

        print(node1, true);
        QuickSortUsingDLL.sort(node1);
        print(node1, true);

    }
}

