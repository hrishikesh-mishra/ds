package com.hrishikesh.practices.binarytree;

import java.util.Objects;

/**
 * Problem:
 * Maximum width of a binary tree
 * Given a binary tree, write a function to get the maximum width of the given tree.
 * Width of a tree is maximum of widths of all levels.
 * ;
 * ;
 * Solution:
 * - There are two solution
 * - Either use queue for order level traversal and return max width
 * - Or find height and for each find width
 * ;
 * ;
 * Algorithm for second approach:
 * - Find height of tree
 * - Set MaxWidth = 0
 * - Iterate height from 0 to height
 * - - Find width for each height
 * - - If current width > MaxWidth then
 * - - - MaxWidth = current width
 * - Return MaxWidth
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/find-maximum-width-of-a-binary-tree/
 */
public class BinaryTreeMaxWidthFinder {

    public static int findMaxWidth(BinaryTreeNode<Integer> root) {
        int heightOfTree = getHeight(root);

        int maxWidth = 0;
        for (int i = 0; i <= heightOfTree; ++i) {
            int width = getWidthOfTreeAtLevel(root, i);
            maxWidth = width > maxWidth ? width : maxWidth;
        }

        return maxWidth;
    }

    private static int getWidthOfTreeAtLevel(BinaryTreeNode<Integer> node, int height) {
        if (Objects.isNull(node)) {
            return 0;
        }

        if (height == 0) {
            return 1;
        } else if (height > 0) {
            return getHeight(node.getLeft(), height - 1) + getHeight(node.getRight(), height - 1);
        }

        return 0;

    }

    private static int getHeight(BinaryTreeNode<Integer> root) {
        return getHeight(root, 0);
    }

    private static int getHeight(BinaryTreeNode<Integer> node, int height) {
        if (Objects.isNull(node)) {
            return 0;
        }

        return 1 + Math.max(getHeight(node.getLeft(), height),
                getHeight(node.getLeft(), height));

    }


}

class BinaryTreeMaxWidthFinderTest {
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


        BTPrinter.print(root);
        System.out.println("Width : " + BinaryTreeMaxWidthFinder.findMaxWidth(root));

    }
}