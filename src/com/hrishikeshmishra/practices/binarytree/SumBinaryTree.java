package com.hrishikeshmishra.practices.binarytree;

import java.util.Objects;

/**
 * Problem:
 * Sum Tree
 * A SumTree is a Binary Tree where value of every node x is equal to sum of nodes present
 * in its left subtree and right subtree of x. An empty tree is SumTree and sum of an empty
 * tree can be considered as 0. A leaf node is also considered as SumTree.
 * ;
 * Following is an example of SumTree.
 * ;
 *         13
 *       /    \
 *     10      3
 *  /   \   /   \
 * 4     6  1    2
 * ;
 * Algorithm:
 * - Create a dummy class to hold result
 * - Traverse tree in post order
 * - - If node is null then return true
 * - - If node is leaf node then return true
 * - - Recursively call for left sub tree
 * - - Recursively call for right sub tree
 * - - Check node sum of left child and right child
 * - - Return node.data = left.data + right.data && isLeftSubTreeSumTree && isRightSubTreeSumTree
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/binary-sum-tree/
 */
public class SumBinaryTree {

    private static class ResultSet {
        private boolean isSumTree;
        private int sum;

        public ResultSet(boolean isSumTree, int sum) {
            this.isSumTree = isSumTree;
            this.sum = sum;
        }
    }

    public static boolean isSumTree(BinaryTreeNode<Integer> root) {
        return checkSumTreeRecursively(root).isSumTree;
    }

    private static ResultSet checkSumTreeRecursively(BinaryTreeNode<Integer> node) {

        /** An empty tree is always sum tree **/
        if (Objects.isNull(node)) {
            return new ResultSet(true, 0);
        }

        /** A leaf node is always sum tree **/
        if (isLeaf(node)) {
            return new ResultSet(true, node.getData());
        }

        /** Traverse in post order **/

        /** Validate left sub tree  **/
        ResultSet leftSubTreeResult = checkSumTreeRecursively(node.getLeft());

        /** Validate right sub tree **/
        ResultSet rightSubTreeResult = checkSumTreeRecursively(node.getRight());

        /** Validate current node **/
        if (leftSubTreeResult.isSumTree &&
                rightSubTreeResult.isSumTree &&
                node.getData() == leftSubTreeResult.sum + rightSubTreeResult.sum) {

            return new ResultSet(true, node.getData());
        } else {
            return new ResultSet(false, 0);
        }
    }

    private static boolean isLeaf(BinaryTreeNode<Integer> node) {
        return Objects.isNull(node.getLeft()) &&
                Objects.isNull(node.getRight());
    }
}


class SumBinaryTreeTest {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root1 = new BinaryTreeNode<>(13,
                new BinaryTreeNode<>(10,
                        new BinaryTreeNode<>(4),
                        new BinaryTreeNode<>(6)),
                new BinaryTreeNode<>(3,
                        new BinaryTreeNode<>(2),
                        new BinaryTreeNode<>(1)));

        BTPrinter.print(root1);
        System.out.println("Is sum tree: " + SumBinaryTree.isSumTree(root1));

        System.out.println("------------------------------------------------------------");

        BinaryTreeNode<Integer> root2 = new BinaryTreeNode<>(100,
                new BinaryTreeNode<>(80,
                        new BinaryTreeNode<>(60,
                                new BinaryTreeNode<>(35),
                                new BinaryTreeNode<>(25)),
                        new BinaryTreeNode<>(20)),
                new BinaryTreeNode<>(20,
                        new BinaryTreeNode<>(20,
                                new BinaryTreeNode<>(7),
                                new BinaryTreeNode<>(13)),
                        null));


        BTPrinter.print(root2);
        System.out.println("Is sum tree: " + SumBinaryTree.isSumTree(root2));


    }
}

