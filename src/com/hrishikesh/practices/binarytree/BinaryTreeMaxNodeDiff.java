package com.hrishikesh.practices.binarytree;

import java.util.Objects;

/**
 * Problem:
 * Maximum difference between node and its ancestor
 * Given a Binary Tree you need to  find maximum value which you  can get by subtracting
 * value of node B from value of node A, where A and B are two nodes of the binary tree and
 * A is an ancestor of B .
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/maximum-difference-node-ancestor/
 */
public class BinaryTreeMaxNodeDiff {

    /**
     * We Need a container class to hold result of previously computed
     * Maximum difference and last Node that has minimum value
     */
    public static class ResultSet {
        private int maxDiff;
        private int lastMinNode;

        public ResultSet(int maxDiff, int lastMinNode) {
            this.maxDiff = maxDiff;
            this.lastMinNode = lastMinNode;
        }

        public int getMaxDiff() {
            return maxDiff;
        }

        public int getLastMinNode() {
            return lastMinNode;
        }
    }


    public static ResultSet find(BinaryTreeNode<Integer> node) {

        /** When node is null, it will possible when a node has only one child **/
        if (Objects.isNull(node)) {
            return new ResultSet(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        /** If node is leaf node **/
        if (isLeaf(node)) {
            return new ResultSet(Integer.MIN_VALUE, node.getData());
        }


        /** Process left and right child first and find out a node that has mini value and max difference **/
        ResultSet leftSubTreeResultSet = find(node.getLeft());
        ResultSet rightSubTreeResultSet = find(node.getRight());


        int minNodeValueFromChildren = Math.min(leftSubTreeResultSet.lastMinNode,
                rightSubTreeResultSet.lastMinNode);

        int newMinDiff = node.getData() - minNodeValueFromChildren;


        /**
         * Now return two things
         * MaxDiff with will max of three thing
         *   - left child max
         *   - right child max or
         *   - Diff between node and previous minimum node
         *
         * Min Node, it will also be min of three thing,
         *   - Current node
         *   - left child min node
         *   - right child min node.
         */
        return new ResultSet(
                Math.max(newMinDiff,
                        Math.max(leftSubTreeResultSet.maxDiff,
                                rightSubTreeResultSet.maxDiff)),

                Math.min(node.getData(),
                        Math.min(leftSubTreeResultSet.lastMinNode,
                                rightSubTreeResultSet.lastMinNode))
        );

    }

    public static boolean isLeaf(BinaryTreeNode<Integer> node) {
        return Objects.isNull(node.getLeft()) && Objects.isNull(node.getRight());
    }
}


class BinaryTreeMaxNodeDiffTest{
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(8,
                new BinaryTreeNode<>(3,
                        new BinaryTreeNode<>(1),
                        new BinaryTreeNode<>(6,
                                new BinaryTreeNode<>(4),
                                new BinaryTreeNode<>(7))),
                new BinaryTreeNode<>(10,
                        null,
                        new BinaryTreeNode<>(14,
                                new BinaryTreeNode<>(13),
                                null)));


        BTPrinter.print(root);

        System.out.println("Max diff: " + BinaryTreeMaxNodeDiff.find(root).getMaxDiff());

    }
}