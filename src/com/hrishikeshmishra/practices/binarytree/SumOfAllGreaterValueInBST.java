package com.hrishikeshmishra.practices.binarytree;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Problem:
 * Add all greater values to every node in a BST
 * ;
 * Given a Binary Search Tree (BST), modify it so that all greater
 * values in the given BST are added to every node.
 * ;
 * Solution :
 * - Reverse of InOrder traversal with sum
 * -- Second Option --
 * ---- Traverse tree in InOrder and extract all node data to array in O(n)
 * ---- Sum all data of array in reverse order in O(n)
 * ---- Again Traverse tree in InOrder and replace node value with array in O(n)
 * ;
 * Algorithm :
 * - Take sum class object to hold the sum of nodes (because java doesn't support call by reference, so we need object)
 * - Traverse reverse InOrder (means Right - Root - Left)
 * - - If node is null then
 * - - - return;
 * - - Recursively call for right child
 * - - Set sum = sum + node.data
 * - - Set node = sum
 * - - Recursively call for left child
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/add-all-greater-values-to-every-node-in-a-bst/
 */
public class SumOfAllGreaterValueInBST {

    public static void sum(BinaryTreeNode<Integer> root) {

        /**
         * Java doesn't support call by reference so, we need use
         * some object hold sum
         */
        AtomicInteger sumHolder = new AtomicInteger(0);
        sum(root, sumHolder);
    }

    private static void sum(BinaryTreeNode<Integer> node, AtomicInteger sumHolder) {

        /** Base case when node is null **/
        if (Objects.isNull(node)) {
            return;
        }

        /** Process in REVERSE of INORDER fashion **/

        /** First go straight right **/
        sum(node.getRight(), sumHolder);

        /** Process node **/
        sumHolder.set(sumHolder.get() + node.getData());
        node.setData(sumHolder.get());

        /** Now process left part **/
        sum(node.getLeft(), sumHolder);
    }

}

class SumOfAllGreaterValueInBSTTest {
    public static void main(String[] args) {

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(50,
                new BinaryTreeNode<>(30,
                        new BinaryTreeNode<>(20),
                        new BinaryTreeNode<>(40)),
                new BinaryTreeNode<>(70,
                        new BinaryTreeNode<>(60),
                        new BinaryTreeNode<>(80)));

        BTPrinter.print(root);
        SumOfAllGreaterValueInBST.sum(root);

        System.out.println("After Sum : ");
        BTPrinter.print(root);
    }
}

