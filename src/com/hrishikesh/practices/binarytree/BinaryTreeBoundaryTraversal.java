package com.hrishikesh.practices.binarytree;

import java.util.Objects;

/**
 * Problem:
 * Binary Tree Boundary Traversal
 * ;
 * Hint: Problem is divided into three sections
 * - Left side view
 * - Leaf view from left to right
 * - Right side view
 *
 * @author hrishikesh.mishra
 */
public class BinaryTreeBoundaryTraversal {

    public static void print(BinaryTreeNode<Integer> root) {
        printLeftSideView(root);
        printLeaf(root);
        printRightSideView(root.getRight());
    }

    private static void printRightSideView(BinaryTreeNode<Integer> node) {
        if (Objects.isNull(node) || isLeaf(node)) {
            return;
        }

        printRightSideView(node.getRight());
        printNodeValue(node);
    }

    private static void printLeaf(BinaryTreeNode<Integer> node) {
        if (Objects.isNull(node)) {
            return;
        }

        if (isLeaf(node)) {
            printNodeValue(node);
        }

        printLeaf(node.getLeft());
        printLeaf(node.getRight());

    }

    private static void printLeftSideView(BinaryTreeNode<Integer> node) {
        if (Objects.isNull(node) || isLeaf(node)) {
            return;
        }

        printNodeValue(node);
        printLeftSideView(node.getLeft());
    }

    private static boolean isLeaf(BinaryTreeNode<Integer> node) {
        return Objects.isNull(node.getLeft()) && Objects.isNull(node.getRight());
    }

    private static void printNodeValue(BinaryTreeNode<Integer> node) {
        System.out.printf("%d ", node.getData());
    }


}

class BinaryTreeBoundaryTraversalTest {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(20,
                new BinaryTreeNode<>(8,
                        new BinaryTreeNode<>(4),
                        new BinaryTreeNode<>(12,
                                new BinaryTreeNode<>(10),
                                new BinaryTreeNode<>(14))),
                new BinaryTreeNode<>(22,
                        null,
                        new BinaryTreeNode<>(25)));

        BTPrinter.print(root);

        System.out.print("Boundary Traversal: ");

        BinaryTreeBoundaryTraversal.print(root);
    }
}
