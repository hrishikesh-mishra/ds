package com.hrishikeshmishra.practices.binarytree;

import java.util.Objects;

/**
 * Problem:
 * Binary Tree K Leaves Printer
 * In a given Binary Tree print all nodes that has K Leaves
 *
 * @author hrishikesh.mishra
 */
public class BinaryTreeKLeavesPrinter {

    public static void print(BinaryTreeNode<Integer> root, int k) {
        printHelper(root, k);
    }

    private static int printHelper(BinaryTreeNode<Integer> node, int k) {
        if (Objects.isNull(node)) {
            return 0;
        }

        if (isLeafNode(node)) {

            if (k == 0) {
                System.out.printf("%d ", node.getData());
            }
            return 1;
        }

        int leftLeaves = printHelper(node.getLeft(), k);
        int rightLeaves = printHelper(node.getRight(), k);

        int totalLeaves = leftLeaves + rightLeaves;

        if (totalLeaves == k) {
            System.out.printf("%d ", node.getData());
        }

        return totalLeaves;
    }

    private static boolean isLeafNode(BinaryTreeNode<Integer> node) {
        return Objects.isNull(node.getLeft()) && Objects.isNull(node.getRight());
    }
}


class BinaryTreeKLeavesPrinterTest {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1,
                new BinaryTreeNode<>(3,
                        null,
                        new BinaryTreeNode<>(6)),
                new BinaryTreeNode<>(2,
                        new BinaryTreeNode<>(4),
                        new BinaryTreeNode<>(5,
                                new BinaryTreeNode<>(8),
                                new BinaryTreeNode<>(7))));

        BTPrinter.print(root);

        System.out.print("For Leaf nodes : 4, Nodes are: ");
        BinaryTreeKLeavesPrinter.print(root, 4);

        System.out.print("\nFor Leaf nodes : 2, Nodes are: ");
        BinaryTreeKLeavesPrinter.print(root, 2);

        System.out.print("\nFor Leaf nodes : 1, Nodes are: ");
        BinaryTreeKLeavesPrinter.print(root, 1);

        System.out.print("\nFor Leaf nodes : 0, Nodes are: ");
        BinaryTreeKLeavesPrinter.print(root, 0);
    }
}