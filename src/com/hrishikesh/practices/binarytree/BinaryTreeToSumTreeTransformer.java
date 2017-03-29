package com.hrishikesh.practices.binarytree;

import java.util.Objects;

/**
 * Problem:
 * Transform to Sum Tree
 * Given a Binary Tree where each node has positive and negative values.
 * ;
 * Convert this to a tree where each node contains the sum of the left and
 * right sub trees in the original tree. The values of leaf nodes are changed to 0.
 * ;
 * For example, the following tree
 *              10
 *           /      \
 *         -2        6
 *        /   \     /  \
 *      8     -4   7    5
 *;
 * should be changed to
 *                 20(4-2+12+6)
 *              /              \
 *            4(8-4)        12(7+5)
 *            /   \           /  \
 *            0      0       0    0
 *            7
 *
 *  ;
 *  ;
 *  Algorithm:
 *  - Traverse tree in postOrder
 *  - - If node is null then
 *  - - - return 0
 *  - - If node is leaf node then
 *  - - - temp = node.data
 *  - - - node.data = 0
 *  - - - return temp
 *  - - leftSum = Recursive call with left subtree (i.e. node->left)
 *  - - rightSum = Recursive call with righ subtree (i.e. node->right)
 *  - - temp = node.data
 *  - - node.data = leftSum + rightSum
 *  - - return node.data + temp
 *
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/transform-to-sum-tree/
 */
public class BinaryTreeToSumTreeTransformer {

    public static void transform(BinaryTreeNode<Integer> root) {
        transformRecursively(root);
    }

    private static int transformRecursively(BinaryTreeNode<Integer> node) {
        if (Objects.isNull(node)) {
            return 0;
        }

        if (isLeaf(node)) {
            int temp = node.getData();
            node.setData(0);
            return temp;
        }

        int leftSubTreeSum = transformRecursively(node.getLeft());
        int rightSubTreeSum = transformRecursively(node.getRight());

        int temp = node.getData();
        node.setData(leftSubTreeSum + rightSubTreeSum);
        return temp + node.getData();
    }

    private static boolean isLeaf(BinaryTreeNode<Integer> node) {
        return (Objects.isNull(node.getLeft()) &&
                Objects.isNull(node.getRight()));
    }
}


class BinaryTreeToSumTreeTransformerTest {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(10,
                new BinaryTreeNode<>(-2,
                        new BinaryTreeNode<>(8),
                        new BinaryTreeNode<>(-4)),
                new BinaryTreeNode<>(6,
                        new BinaryTreeNode<>(7),
                        new BinaryTreeNode<>(5)));

        BTPrinter.print(root);
        BinaryTreeToSumTreeTransformer.transform(root);
        System.out.println("Sum Tree ");
        BTPrinter.print(root);
    }
}