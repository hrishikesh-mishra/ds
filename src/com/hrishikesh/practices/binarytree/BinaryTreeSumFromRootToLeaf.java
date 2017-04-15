package com.hrishikesh.practices.binarytree;

import java.util.Objects;

/**
 * Problem:
 * Check is Sum from Root To Leaf exist or not in Binary Tree
 *
 * @author hrishikesh.mishra
 */
public class BinaryTreeSumFromRootToLeaf {

    public static boolean isSumExists(BinaryTreeNode<Integer> node, int sum) {

        /** Base case: when node is null **/
        if (Objects.isNull(node)) {
            return false;
        }
        /** When node is leaf node **/
        else if (isLeaf(node)) {
            return node.getData() == sum;
        }

        /** Recursively checking in left and right sub-tree **/
        return isSumExists(node.getLeft(), sum - node.getData()) ||
                isSumExists(node.getRight(), sum - node.getData());

    }

    private static boolean isLeaf(BinaryTreeNode<Integer> node) {
        return Objects.isNull(node.getLeft()) && Objects.isNull(node.getRight());
    }
}


class BinaryTreeSumFromRootToLeafTest {

    public static void main(String[] args) {

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(10,
                new BinaryTreeNode<>(8,
                        new BinaryTreeNode<>(3),
                        new BinaryTreeNode<>(5)),
                new BinaryTreeNode<>(2,
                        new BinaryTreeNode<>(2),
                        null));

        BTPrinter.print(root);

        System.out.println("Sum = 21 ?   " + BinaryTreeSumFromRootToLeaf.isSumExists(root, 21));
        System.out.println("Sum = 10 ?   " + BinaryTreeSumFromRootToLeaf.isSumExists(root, 10));
        System.out.println("Sum = 23 ?   " + BinaryTreeSumFromRootToLeaf.isSumExists(root, 23));
        System.out.println("Sum = 12 ?   " + BinaryTreeSumFromRootToLeaf.isSumExists(root, 12));

    }
}
