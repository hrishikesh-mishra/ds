package com.hrishikeshmishra.practices.binarytree;

import java.util.Objects;

/**
 * Problem:
 * Check for Balanced Tree
 * ;
 * Given a binary tree, find if it is height balanced or not.
 * ;
 * A tree is height balanced if difference between heights of left and right subtrees is not more
 * than one for all nodes of tree.
 * Expected time complexity is O(n).
 * ;
 * ;
 * Algorithm:
 * - Create a class to hold height and isBalanced boolean of a tree
 * - Traverse tree in post order
 * - - If node is null then
 * - - - return height=0 and isBalanced = true
 * - - If node is leaf node then
 * - - - return height=1 and isBalanced = true
 * - - Recursively call for left sub tree
 * - - Recursively call for right sub tree
 * - - If leftSubTree is balanced and rightSubTree is balanced then
 * - - - return height = Max(leftSubTree.height and leftSubTree.height) + 1 and balanced = true
 * - - Else
 * - - - return balanced = false and height =0
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/check-for-height-balanced-tree/
 */
public class HeightBalancedTreeChecker {

    /**
     * To hold result
     **/
    private static class ResultSet {
        private int height;
        private boolean isBalanced;

        public ResultSet(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }

    public static boolean isHeightBalanced(BinaryTreeNode<Integer> root) {
        return computeHeightAndCheckBalanced(root, new ResultSet(0, true)).isBalanced;
    }

    private static ResultSet computeHeightAndCheckBalanced(BinaryTreeNode<Integer> node, ResultSet resultSet) {

        /** Empty tree is always height balanced **/
        if (Objects.isNull(node)) {
            return new ResultSet(0, true);
        }

        /** A leaf node is always height balanced **/
        if (isLeaf(node)) {
            return new ResultSet(1, true);
        }

        /** Compute height and check balanced status of left and right sub tree first **/
        ResultSet leftSubTreeResult = computeHeightAndCheckBalanced(node.getLeft(), resultSet);
        ResultSet rightSubTreeResult = computeHeightAndCheckBalanced(node.getRight(), resultSet);

        /** Computer height and check balanced for current node **/
        if (leftSubTreeResult.isBalanced && rightSubTreeResult.isBalanced &&
                isHeightBalanced(leftSubTreeResult, rightSubTreeResult)) {

            return new ResultSet(Math.max(leftSubTreeResult.height, resultSet.height) + 1, true);
        } else {
            return new ResultSet(0, false);
        }
    }

    private static boolean isHeightBalanced(ResultSet leftSubTreeResult, ResultSet rightSubTreeResult) {
        return Math.abs(leftSubTreeResult.height - rightSubTreeResult.height) <= 1;
    }

    private static boolean isLeaf(BinaryTreeNode<Integer> node) {
        return Objects.isNull(node.getLeft()) &&
                Objects.isNull(node.getRight());
    }

}


class HeightBalancedTreeCheckerTest {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root1 = new BinaryTreeNode<>(1,
                new BinaryTreeNode<>(2,
                        new BinaryTreeNode<>(4,
                                new BinaryTreeNode<>(7),
                                null),
                        new BinaryTreeNode<>(5)),
                new BinaryTreeNode<>(3,
                        new BinaryTreeNode<>(6),
                        null));

        BTPrinter.print(root1);
        System.out.println("Is height balanced : " + HeightBalancedTreeChecker.isHeightBalanced(root1));

        System.out.println("----------------------------------------------------------------------");
        BinaryTreeNode<Integer> root2 = new BinaryTreeNode<>(1,
                new BinaryTreeNode<>(2,
                        new BinaryTreeNode<>(4,
                                new BinaryTreeNode<>(7),
                                null),
                        new BinaryTreeNode<>(5)),
                new BinaryTreeNode<>(3));

        BTPrinter.print(root2);
        System.out.println("Is height balanced : " + HeightBalancedTreeChecker.isHeightBalanced(root2));
    }
}