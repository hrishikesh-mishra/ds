package com.hrishikeshmishra.practices.binarytree;

import java.util.Objects;

/**
 * Leaf at same level
 * Given a Binary Tree, check if all leaves are at same level or not.
 * Solution:
 * - Find the height of binary tree then,
 * - for each leaf check with same height.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/check-leaf-at-same-level-of-binary-tree/
 */
public class BinaryTreeLeafAtSameLevelChecker {

    public static boolean checkLeafLevel(BinaryTreeNode<Integer> node) {
        /** Get height of tree **/
        int height = getHeight(node);

        /** All leaf node must be at same height **/
        return checkLeafLevel(node, height, 1);

    }

    private static boolean checkLeafLevel(BinaryTreeNode<Integer> node, int height, int level) {

        /** Base case when node is null **/
        if (Objects.isNull(node)) {
            return true;
        }

        /** Check current node is leaf node **/
        if (isLeafNode(node)) {
            /** If yes, then height and level must be equal **/
            return level == height;
        }

        /** Recursively check for left and right subtree **/
        return checkLeafLevel(node.getLeft(), height, level + 1) &&
                checkLeafLevel(node.getRight(), height, level + 1);
    }

    /**
     * Leaf node checker
     *
     * @param node
     * @return
     */
    private static boolean isLeafNode(BinaryTreeNode<Integer> node) {
        return Objects.isNull(node.getLeft()) &&
                Objects.isNull(node.getRight());
    }

    /**
     * Height of tree calculator
     *
     * @param node
     * @return
     */
    private static int getHeight(BinaryTreeNode<Integer> node) {
        if (Objects.isNull(node)) {
            return 0;
        }

        return 1 + Math.max(getHeight(node.getLeft()),
                getHeight(node.getRight()));
    }

}


class BinaryTreeLeafAtSameLevelCheckerTest {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root1 = new BinaryTreeNode<>(12,
                new BinaryTreeNode<>(5,
                        new BinaryTreeNode<>(3),
                        null),
                new BinaryTreeNode<>(7,
                        null,
                        new BinaryTreeNode<>(1)));

        BinaryTreeNode<Integer> root2 = new BinaryTreeNode<>(12,
                new BinaryTreeNode<>(5,
                        new BinaryTreeNode<>(3),
                        null),
                new BinaryTreeNode<>(7));

        BinaryTreeNode<Integer> root3 = new BinaryTreeNode<>(12,
                new BinaryTreeNode<>(5,
                        new BinaryTreeNode<>(3,
                                new BinaryTreeNode<>(1),
                                null),
                        new BinaryTreeNode<>(9,
                                new BinaryTreeNode<>(2),
                                null)),
                null);

        BTPrinter.print(root1);
        System.out.println("Leaf at same level ? " + BinaryTreeLeafAtSameLevelChecker.checkLeafLevel(root1));

        BTPrinter.print(root2);
        System.out.println("\nLeaf at same level ? " + BinaryTreeLeafAtSameLevelChecker.checkLeafLevel(root2));

        BTPrinter.print(root3);
        System.out.println("\nLeaf at same level ? " + BinaryTreeLeafAtSameLevelChecker.checkLeafLevel(root3));

    }
}