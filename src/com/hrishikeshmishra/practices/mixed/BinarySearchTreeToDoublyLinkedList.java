package com.hrishikeshmishra.practices.mixed;

import java.util.Objects;

import static com.hrishikeshmishra.practices.mixed.BinarySearchTreeToDoublyLinkedList.*;

/**
 * Problem:
 * Binary Search Tree To Doubly Linked List
 * Convert Binary search tree to doubly linked list.
 * Note: BiNode - is a data structure that has two pointers to two nodes. It used to represent both data structure,
 * a binary tree or doubly linked list.
 * ;
 * ;
 * ;
 * Algorithm:
 * - Recursively go to left end
 * - Recursively go to right end
 * - If left child exists then
 * - - Traverse to tail
 * - - Merge after root node after tail
 * - If Right child exists then
 * - - Merge right child before root
 * - If left child exists then
 * - - return left child
 * - Else
 * - - return root
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/binarysearchtreetodoublylinkedlist/
 */
public class BinarySearchTreeToDoublyLinkedList {

    public static class BiNode {
        private int value;
        private BiNode node1;
        private BiNode node2;

        public BiNode(int value) {
            this.value = value;
        }

        public BiNode(int value, BiNode node1, BiNode node2) {
            this.value = value;
            this.node1 = node1;
            this.node2 = node2;
        }


        public int getValue() {
            return value;
        }

        public BiNode getNode1() {
            return node1;
        }

        public BiNode getNode2() {
            return node2;
        }

        @Override
        public String toString() {
            return "BiNode(" + value + ')';
        }
    }

    public static BiNode convert(BiNode root) {
        if (Objects.isNull(root)) {
            return null;
        }

        BiNode left = convert(root.getNode1());
        BiNode right = convert(root.getNode2());

        if (!Objects.isNull(left)) {
            merge(getTail(left), root);
        }

        if (!Objects.isNull(right)) {
            merge(root, right);
        }

        return !Objects.isNull(left) ? left : root;
    }

    private static void merge(BiNode a, BiNode b) {
        a.node2 = b;
        b.node1 = a;
    }

    private static BiNode getTail(BiNode node) {
        while (!Objects.isNull(node.getNode2())) {
            node = node.getNode2();
        }
        return node;
    }
}


class BinarySearchTreeToDoublyLinkedListTest {
    public static void main(String[] args) {
        BiNode root = new BiNode(40,
                new BiNode(20,
                        new BiNode(14,
                                new BiNode(8),
                                null),
                        new BiNode(25)),
                new BiNode(50,
                        null,
                        new BiNode(100)));

        System.out.print("Tree InOrder: ");
        traverseInOrder(root);

        BiNode dllHead = convert(root);

        printDLLFromHeadSide(dllHead);
        printDLLFromTailSide(dllHead);

    }

    private static void traverseInOrder(BiNode root) {
        if (Objects.isNull(root)) {
            return;
        }

        traverseInOrder(root.getNode1());
        System.out.print(root.getValue() + ", ");
        traverseInOrder(root.getNode2());
    }

    private static void printDLLFromHeadSide(BiNode node) {
        System.out.print("\nDLL from head side: ");
        while (!Objects.isNull(node)) {
            System.out.print(node.getValue() + ", ");
            node = node.getNode2();
        }
    }

    private static void printDLLFromTailSide(BiNode node) {

        while (!Objects.isNull(node.getNode2())) {
            node = node.getNode2();
        }

        System.out.print("\nDLL from Tail side: ");
        while (!Objects.isNull(node)) {
            System.out.print(node.getValue() + ", ");
            node = node.getNode1();
        }
    }

}