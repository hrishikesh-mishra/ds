package com.hrishikesh.practices.binarysearchtree;

import com.hrishikesh.practices.binarytree.BTPrinter;
import com.hrishikesh.practices.binarytree.BinaryTreeNode;

import java.util.Objects;

/**
 * Problem:
 * In-place conversion of Sorted DLL to Balanced BST
 * Given a Doubly Linked List which has data members sorted in ascending order.
 * Construct a Balanced Binary Search Tree which has same data members as the given Doubly Linked List.
 * The tree must be constructed in-place (No new node should be allocated for tree conversion)
 * ;
 * Solution:
 * - Get middle node
 * - Process first half part recursively for left sub-tree
 * - Process right half part recursively for right sub-tree
 * - Technically its creating node from root to leaves
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/in-place-conversion-of-sorted-dll-to-balanced-bst/
 */
public class SortedDLLToBalancedBST {

    public static BinaryTreeNode<Integer> createBSTRecurrsive(BinaryTreeNode<Integer> node) {

        /** Base case when node is null **/
        if (Objects.isNull(node)) {
            return null;
        }


        /** Get middle node **/
        BinaryTreeNode<Integer> middleNode = getMiddleNode(node);

        /** In process left part **/
        if (!Objects.isNull(middleNode.getLeft())) {
            /** Remove the previous pointer to middle node **/
            middleNode.getLeft().setRight(null);

            /** Create left sub-tree recursively **/
            middleNode.setLeft(createBSTRecurrsive(node));
        } else {
            middleNode.setLeft(null);
        }

        /** Process right part **/
        if (!Objects.isNull(middleNode.getRight())) {
            /** Remove the next pointer back to middle node **/
            middleNode.getRight().setLeft(null);

            /** Create right sub-tree recursively **/
            middleNode.setRight(createBSTRecurrsive(middleNode.getRight()));
        } else {
            middleNode.setRight(null);
        }

        /** Return create root **/
        return middleNode;
    }

    /**
     * Get middle node
     * (In case of even, left part will one more than right part half part)
     *
     * @param node
     * @return
     */
    private static BinaryTreeNode<Integer> getMiddleNode(BinaryTreeNode<Integer> node) {
        BinaryTreeNode<Integer> slowPrt = node;
        BinaryTreeNode<Integer> fastPtr = node;

        while (!Objects.isNull(fastPtr.getRight()) &&
                !Objects.isNull(fastPtr.getRight().getRight())) {
            slowPrt = slowPrt.getRight();
            fastPtr = fastPtr.getRight().getRight();
        }

        /** Making one step right, in case length is even **/
        if (!Objects.isNull(fastPtr.getRight())) {
            slowPrt = slowPrt.getRight();
        }

        return slowPrt;

    }

}


class SortedDLLToBalancedBSTTest {
    public static void main(String[] args) {

        /** Node creation **/
        BinaryTreeNode<Integer> node1 = new BinaryTreeNode<>(1),
                node2 = new BinaryTreeNode<>(2),
                node3 = new BinaryTreeNode<>(3),
                node4 = new BinaryTreeNode<>(4),
                node5 = new BinaryTreeNode<>(5),
                node6 = new BinaryTreeNode<>(6),
                node7 = new BinaryTreeNode<>(7);

        /** Next pointer (here we are using right pointer for next **/
        node1.setRight(node2);
        node2.setRight(node3);
        node3.setRight(node4);
        node4.setRight(node5);
        node5.setRight(node6);
        node6.setRight(node7);

        /** Previous pointer (here we are using left pointer for previous) **/
        node2.setLeft(node1);
        node3.setLeft(node2);
        node4.setLeft(node3);
        node5.setLeft(node4);
        node6.setLeft(node5);
        node7.setLeft(node6);

        System.out.print("Print DLL Forward: ");
        printForward(node1);

        System.out.print("\nPrint DLL Reverse: ");
        printReverse(node7);

        BinaryTreeNode<Integer> bstRoot = SortedDLLToBalancedBST.createBSTRecurrsive(node1);
        System.out.println("\nBinary Search Tree : ");
        BTPrinter.print(bstRoot);
    }

    private static void printForward(BinaryTreeNode<Integer> node) {
        while (!Objects.isNull(node)) {
            System.out.print(node.getData() + " ");
            node = node.getRight();
        }
    }

    private static void printReverse(BinaryTreeNode<Integer> node) {
        while (!Objects.isNull(node)) {
            System.out.print(node.getData() + " ");
            node = node.getLeft();
        }
    }
}

