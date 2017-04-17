package com.hrishikeshmishra.practices.binarysearchtree;

import com.hrishikeshmishra.practices.binarytree.BinaryTreeNode;

import java.util.Objects;

import static com.hrishikeshmishra.practices.binarysearchtree.BinarySearchTreeChecker.isBST;
import static com.hrishikeshmishra.practices.binarytree.BTPrinter.print;

/**
 * Problem:
 * Check a binary tree is binary search tree (BST )
 *
 * @author hrishikesh.mishra
 */
public class BinarySearchTreeChecker {


    public static boolean isBST(BinaryTreeNode<Integer> node) {
        return isBSTHelper(node, null);
    }

    /**
     * Solution is based on inorder traversal
     *
     * @param node
     * @param prev
     * @return
     */
    private static boolean isBSTHelper(BinaryTreeNode<Integer> node, BinaryTreeNode<Integer> prev) {

        /** Base case : when node is null. **/
        if (Objects.isNull(node)) {
            return true;
        }

        /** Go first extreme left and check left sub-tree **/
        if (!isBSTHelper(node.getLeft(), prev)) {
            return false;
        }


        /** check node value must be less than previous node value **/
        if (!Objects.isNull(prev) && prev.getData() >= node.getData()) {
            return false;
        }

        /** Update previous node **/
        prev = node;

        /** Check for right sub-tree **/
        return isBSTHelper(node.getRight(), prev);
    }
}


class BinarySearchTreeCheckerTest {
    public static void main(String[] args) {

        BinaryTreeNode<Integer> root1 = new BinaryTreeNode<>(20,
                new BinaryTreeNode<>(10,
                        new BinaryTreeNode<>(1),
                        new BinaryTreeNode<>(25)),
                new BinaryTreeNode<>(35,
                        null,
                        new BinaryTreeNode<>(6)));

        BinaryTreeNode<Integer> root2 = new BinaryTreeNode<>(20,
                new BinaryTreeNode<>(10,
                        new BinaryTreeNode<>(1),
                        new BinaryTreeNode<>(15)),
                new BinaryTreeNode<>(35,
                        null,
                        new BinaryTreeNode<>(60)));

        System.out.println("Tree 1:");
        print(root1);
        System.out.printf("Is BST ? %b " , isBST(root1));

        System.out.println("\n\nTree 2:");
        print(root2);
        System.out.printf("Is BST ? %b " , isBST(root2));




    }


}