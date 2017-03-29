package com.hrishikesh.practices.binarysearchtree;

import com.hrishikesh.practices.binarytree.BTPrinter;
import com.hrishikesh.practices.binarytree.BinaryTreeNode;

import java.util.Objects;

/**
 * Problem:
 * Find k-th smallest element in BST (Order Statistics in BST)
 * Given root of binary search tree and K as input, find K-th smallest element in BST.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/find-k-th-smallest-element-in-bst-order-statistics-in-bst/
 */
public class BSTKthSmallest {

    public static class ResultSet {
        private int value;
        private int counter;
    }

    public static int findKthSmallest(BinaryTreeNode<Integer> root, int k) {

        ResultSet resultSet = new ResultSet();
        findKthSmallest(root, k, resultSet);

        if (resultSet.counter < k){
            throw new RuntimeException("No such element");
        }
        return resultSet.value;
    }

    private static ResultSet findKthSmallest(BinaryTreeNode<Integer> node, int k, ResultSet resultSet) {

        if (Objects.isNull(node) || resultSet.counter >= k) {
            return resultSet;
        }

        findKthSmallest(node.getLeft(), k, resultSet);

        resultSet.counter++;

        if (resultSet.counter == k) {
            resultSet.value = node.getData();
        }

        findKthSmallest(node.getRight(), k, resultSet);

        return resultSet;

    }

}

class BSTKthSmallestTest {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> bstRoot = new BinaryTreeNode<>(20,
                new BinaryTreeNode<>(3,
                        new BinaryTreeNode<>(2),
                        new BinaryTreeNode<>(10)),
                new BinaryTreeNode<>(30,
                        new BinaryTreeNode<>(27,
                                new BinaryTreeNode<>(26),
                                new BinaryTreeNode<>(28)),
                        new BinaryTreeNode<>(35)));

        BTPrinter.print(bstRoot);

        System.out.println("2nd Smallest : " + BSTKthSmallest.findKthSmallest(bstRoot, 2));
        System.out.println("4th Smallest : " + BSTKthSmallest.findKthSmallest(bstRoot, 4));
        System.out.println("7th Smallest : " + BSTKthSmallest.findKthSmallest(bstRoot, 7));
        System.out.println("70th Smallest : " + BSTKthSmallest.findKthSmallest(bstRoot, 70));

    }
}

