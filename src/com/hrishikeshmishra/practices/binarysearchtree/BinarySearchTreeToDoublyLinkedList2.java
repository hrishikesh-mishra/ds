package com.hrishikeshmishra.practices.binarysearchtree;

import com.hrishikeshmishra.practices.binarytree.BTPrinter;
import com.hrishikeshmishra.practices.binarytree.BinaryTreeNode;

import java.util.Objects;

/**
 * Problem:
 * Binary Search Tree to Doubly Linked List
 * ;
 * Solution:
 * - Traverse in reverse InOrder (i.e. Right-Node-Left)
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/binary-search-tree-to-doubly-linked-list/
 */
public class BinarySearchTreeToDoublyLinkedList2 {

    private static class Container {
        BinaryTreeNode<Integer> dllHead;
        BinaryTreeNode<Integer> dllTail;
    }

    public static BinaryTreeNode<Integer> createDLL(BinaryTreeNode<Integer> root) {
        Container container = new Container();
        createDLL(root, container);
        return container.dllHead;
    }

    private static void createDLL(BinaryTreeNode<Integer> root, Container container) {

        /** Base case when root is null **/
        if (Objects.isNull(root)) {
            return;
        }

        /** Go extreme right **/
        if (!Objects.isNull(root.getRight())) {
            createDLL(root.getRight(), container);
        }

        /** Update next node's previous pointer  **/
        if (!Objects.isNull(container.dllHead)) {
            container.dllHead.setLeft(root);
        } else {
            container.dllTail = root;
        }

        /** Current node's next pointer **/
        root.setRight(container.dllHead);

        /** Update next pointer **/
        container.dllHead = root;

        /** Process left sub-tree **/
        if (!Objects.isNull(root.getLeft())) {
            createDLL(root.getLeft(), container);
        }
    }

}


class BinarySearchTreeToDoublyLinkedList2Test {
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

        BinaryTreeNode<Integer> dllHead = BinarySearchTreeToDoublyLinkedList2.createDLL(root);
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