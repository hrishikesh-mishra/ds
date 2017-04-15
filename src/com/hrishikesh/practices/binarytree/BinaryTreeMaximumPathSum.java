package com.hrishikesh.practices.binarytree;

import java.util.Objects;

/**
 * Problem:
 * Binary Tree Maximum Path Sum
 * For a binary tree, find the maximum path sum. The path may start and end at any node in the tree.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/binary-tree-maximum-path-sum/
 */
public class BinaryTreeMaximumPathSum {

    private static class Result {
        int maxValue;

        public Result(int maxValue) {
            this.maxValue = maxValue;
        }
    }

    public static int find(BinaryTreeNode<Integer> root) {
        Result result = new Result(Integer.MIN_VALUE);
        findHelper(root, result);
        return result.maxValue;
    }

    private static int findHelper(BinaryTreeNode<Integer> node, Result result) {
        /** Base case: when node is null **/
        if (Objects.isNull(node)) {
            return 0;
        }

        int leftMax = findHelper(node.getLeft(), result);
        int rightMax = findHelper(node.getRight(), result);

        /**
         * If max sum path goes through current node then there are four different possible paths
         * 1. Through node only
         * 2. Left + Node
         * 3. Right + Node
         * 4. Left + Node + Right
         **/

        /** First Three cases
         * 1. Node,
         * 2. Node + Left
         * 3. Node + Right
         **/
        int maxSum = Math.max(Math.max(leftMax, rightMax) + node.getData(), node.getData());

        /**
         * Last Case
         * Left + Node + Right
         */
        int newMaxSum = Math.max(maxSum, leftMax + node.getData() + rightMax);


        /** Updating max result **/
        if (newMaxSum > result.maxValue) {
            result.maxValue = newMaxSum;
        }

        return maxSum;
    }
}


class BinaryTreeMaximumPathSumTest {
    public static void main(String[] args) {

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(100,
                new BinaryTreeNode<>(20,
                        new BinaryTreeNode<>(10),
                        new BinaryTreeNode<>(30)
                ),
                new BinaryTreeNode<>(10,
                        null,
                        new BinaryTreeNode<>(-14,
                                new BinaryTreeNode<>(13),
                                new BinaryTreeNode<>(3))));

        BTPrinter.print(root);

        System.out.println("Maximum Path Sum: " + BinaryTreeMaximumPathSum.find(root));

    }
}