package com.hrishikeshmishra.practices.binarytree;

import java.util.Objects;

/**
 * Problem:
 * Inorder Tree Traversal without recursion and without stack!;
 * ;
 * Solution:
 * Its possible using Threaded Binary Tree
 * ;
 * Algorithm:
 * - Iterate till node is not null,
 * - - get predecessor of node
 * - - if predecessor is null then
 * - - - Print node
 * - - - Move to right sub tree i.e. node = node.right
 * - - else if predecessor == node then
 * - - - - set predecessor.right = null
 * - - else if predecessor.right is nul then,
 * - - - predecessor.right = node
 * - - - node = node.left
 *
 * @author hrishikesh.mishra
 * @link http://en.wikipedia.org/wiki/Threaded_binary_tree
 * @link http://hrishikeshmishra.com/inorder-tree-traversal-without-recursion-and-without-stack/
 */
public class BinaryTreeInOrderTravelWithThread {


    public static void traverse(BinaryTreeNode<Integer> node) {

        /** Base case when root is null **/
        if (Objects.isNull(node)) {
            return;
        }

        while (!Objects.isNull(node)) {

            BinaryTreeNode<Integer> predecessor = findInOrderPredecessor(node);

            /** If no predecessor found **/
            if (Objects.isNull(predecessor)) {

                /** No more left elements found so, print element **/
                System.out.print(node.getData() + " ");

                /** Now move to right portion **/
                node = node.getRight();

            } else if (predecessor.getRight() == node) { /** Previously added thread **/

                /** Reset point  back to null **/
                predecessor.setRight(null);

                /** Print Node value **/
                System.out.print(node.getData() + " ");

                /** Move to right **/
                node = node.getRight();

            } else if (Objects.isNull(predecessor.getRight())) { /** Successfully found new predecessor node **/

                /** Set predecessor right pointer to current node **/
                predecessor.setRight(node);

                /** Move left **/
                node = node.getLeft();
            }
        }


    }

    private static BinaryTreeNode<Integer> findInOrderPredecessor(BinaryTreeNode<Integer> node) {

        /** When node is null or node's left child is null **/
        if (Objects.isNull(node) || Objects.isNull(node.getLeft())) {
            return null;
        }

        /** Go extreme right **/
        BinaryTreeNode<Integer> temp = node.getLeft();

        while (!Objects.isNull(temp.getRight()) && temp.getRight() != node) {
            temp = temp.getRight();
        }

        return temp;
    }
}


class BinaryTreeInOrderTravelWithThreadTest {

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(6,
                new BinaryTreeNode<>(13,
                        new BinaryTreeNode<>(1),
                        new BinaryTreeNode<>(5)),
                new BinaryTreeNode<>(8,
                        new BinaryTreeNode<>(7),
                        new BinaryTreeNode<>(11,
                                new BinaryTreeNode<>(9),
                                new BinaryTreeNode<>(3))));

        /** Print tree **/
        BTPrinter.print(root);

        /** Traversing in order tree **/
        BinaryTreeInOrderTravelWithThread.traverse(root);
    }
}