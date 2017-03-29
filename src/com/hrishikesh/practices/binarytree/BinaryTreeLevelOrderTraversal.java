package com.hrishikesh.practices.binarytree;


import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * Problem:
 * Level Order Tree Traversal ( Non-Recursive and Recursive )
 * ;
 * Solution
 * - For non recursion use queue
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/level-order-tree-traversal-non-recursive-and-recursive/
 */
public class BinaryTreeLevelOrderTraversal {

    public static void levelTraverseInOrderNonRecursive(BinaryTreeNode<Integer> node) {

        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        queue.add(node);

        System.out.println("\n\nNon recursive order : ");

        while (!queue.isEmpty()) {
            node = queue.remove();
            System.out.print(node.getData() + " ");

            if (!Objects.isNull(node.getLeft())) {
                queue.add(node.getLeft());
            }

            if (!Objects.isNull(node.getRight())) {
                queue.add(node.getRight());
            }
        }

    }


    public static void levelTraverseInOrderRecursive(BinaryTreeNode<Integer> node) {
        int height = getHeight(node);
        for (int i = 0; i <= height; i++) {
            System.out.println("\nNodes @ level " + i);
            printNodeOfHeight(node, i);

        }
    }

    private static void printNodeOfHeight(BinaryTreeNode<Integer> node, int height) {

        /** In case node is null **/
        if (Objects.isNull(node)) {
            return;
        }

        /** When height == 0 then print node **/
        if (height == 0) {
            System.out.print(node.getData() + " ");
        } else if (height > 0) {
            /** Go to left and right child with height -1 **/
            printNodeOfHeight(node.getLeft(), height - 1);
            printNodeOfHeight(node.getRight(), height - 1);
        }

    }


    private static int getHeight(BinaryTreeNode<Integer> node) {
        return getHeight(node, 0);
    }

    private static int getHeight(BinaryTreeNode<Integer> node, int height) {

        /** When node is null **/
        if (Objects.isNull(node)) {
            return height;
        }

        /** Get height of left and right child **/
        int heightOfLeftTree = getHeight(node.getLeft(), height),
                heightOfRightTree = getHeight(node.getLeft(), height);

        /** Height of tree is max of left or right child + 1 **/
        return Math.max(heightOfLeftTree, heightOfRightTree) + 1;
    }

}

class BinaryTreeLevelOrderTraversalTest {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1,
                new BinaryTreeNode<>(2,
                        new BinaryTreeNode<>(4,
                                new BinaryTreeNode<>(8),
                                null
                        ),
                        new BinaryTreeNode<>(5,
                                new BinaryTreeNode<>(6,
                                        null,
                                        new BinaryTreeNode<>(7)

                                ),
                                null
                        )
                ),
                new BinaryTreeNode<>(3)
        );
        /** Print binary tree **/
        BTPrinter.print(root);

        /** In recursive order **/
        BinaryTreeLevelOrderTraversal.levelTraverseInOrderRecursive(root);

        /** In non recursive order **/
        BinaryTreeLevelOrderTraversal.levelTraverseInOrderNonRecursive(root);
    }
}
