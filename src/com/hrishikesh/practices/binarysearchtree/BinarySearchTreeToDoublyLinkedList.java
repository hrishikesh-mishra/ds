package com.hrishikesh.practices.binarysearchtree;

import com.hrishikesh.practices.binarytree.BTPrinter;
import com.hrishikesh.practices.binarytree.BinaryTreeNode;

import java.util.Objects;

/**
 * Problem:
 * Convert binary search tree to doubly linked list
 * ;
 * Solution:
 * - Traverse InOrder
 * - Keep TailPointer
 * - And update following things for each root
 * - - root.left = tail
 * - - tail.right = root
 * - - tail = root
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/convert-binary-search-tree-to-doubly-linked-list/
 */
public class BinarySearchTreeToDoublyLinkedList {

    /**
     * Container to hold DLL head and tail
     * because java doesn't support call by reference
     */
    public static class Container {
        private BinaryTreeNode<Integer> dllHead;
        private BinaryTreeNode<Integer> dllTail;
    }


    public static BinaryTreeNode<Integer> createDLL(BinaryTreeNode<Integer> root) {
        Container container = new Container();
        createDLL(root, container);
        return container.dllHead;
    }


    /**
     * Process in InOrder traversal and storing various node in tail pointer
     *
     * @param root
     * @param container
     */
    private static void createDLL(BinaryTreeNode<Integer> root, Container container) {

        /** Base case when object is null **/
        if (Objects.isNull(root)) {
            return;
        }

        /** First go extreme left till it finds null in left sub tree  **/
        if (!Objects.isNull(root.getLeft())) {
            createDLL(root.getLeft(), container);
        }

        /** Update root's previous pointer to tail node **/
        root.setLeft(container.dllTail);

        /** Updating previous node next pointer to current root **/
        if (!Objects.isNull(container.dllTail)) {
            container.dllTail.setRight(root);
        } else {
            /** this will execute only one time to set DLL head when tail pointer was null **/
            container.dllHead = root;
        }

        /** Update tail pointer to current root **/
        container.dllTail = root;

        /** Now process right sub tree **/
        if (!Objects.isNull(root.getRight())) {
            createDLL(root.getRight(), container);

        }

    }
}

class BinarySearchTreeToDoublyLinkedListTest {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(6,
                new BinaryTreeNode<>(-13,
                        null,
                        new BinaryTreeNode<>(-8)),
                new BinaryTreeNode<>(14,
                        new BinaryTreeNode<>(13,
                                new BinaryTreeNode<>(7),
                                null),
                        new BinaryTreeNode<>(15)));

        BTPrinter.print(root);

        BinaryTreeNode<Integer> dllHead = BinarySearchTreeToDoublyLinkedList.createDLL(root);
        printDLL(dllHead);
    }

    private static void printDLL(BinaryTreeNode<Integer> dllHead) {

        System.out.print("\nForward: ");

        BinaryTreeNode<Integer> tail = null;
        while (!Objects.isNull(dllHead)) {
            System.out.print(dllHead.getData() + " ");
            dllHead = dllHead.getRight();
            tail = (!Objects.isNull(dllHead)) ? dllHead : tail;
        }

        System.out.print("\nBackward: ");
        while (!Objects.isNull(tail)) {
            System.out.print(tail.getData() + " ");
            tail = tail.getLeft();
        }


    }
}