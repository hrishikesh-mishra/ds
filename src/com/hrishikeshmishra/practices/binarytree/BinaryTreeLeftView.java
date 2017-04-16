package com.hrishikeshmishra.practices.binarytree;

import java.util.Objects;

/**
 * Problem:
 * Left View of Binary Tree
 * Given a Binary Tree, print Left view of it. Left view of a Binary Tree is set
 * of nodes visible when tree is visited from Left side.
 * ;
 * Solution:
 * - Try to go left whenever possible otherwise go to right.
 * ;
 * ;
 * ;
 * Algorithm:
 * - Traverse in modified preOrder, in this.
 * - - If node node is null
 * - - - return
 * - - Print node
 * - - If node has left child then
 * - - - recursively call for left child
 * - - If node has right child then
 * - - - recursively call for right child
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/left-view-of-binary-tree/
 */
public class BinaryTreeLeftView {

    public static void printLeftView(BinaryTreeNode<Integer> node) {
        if (Objects.isNull(node)) {
            return;
        }

        System.out.print(node.getData() + " ");

        /** Try to go left whenever possible otherwise right **/
        printLeftView(!Objects.isNull(node.getLeft()) ?
                node.getLeft() : node.getRight());
    }
}


class BinaryTreeLeftViewTest {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1,
                new BinaryTreeNode<>(2,
                        null,
                        new BinaryTreeNode<>(12,
                                new BinaryTreeNode<>(22,
                                        new BinaryTreeNode<>(30,
                                                null,
                                                new BinaryTreeNode<>(45)),
                                        new BinaryTreeNode<>(32)),
                                null)),
                new BinaryTreeNode<>(7,
                        new BinaryTreeNode<>(3),
                        new BinaryTreeNode<>(13)));

        BTPrinter.print(root);
        System.out.print("\n\nLeft side view: ");
        BinaryTreeLeftView.printLeftView(root);
    }
}