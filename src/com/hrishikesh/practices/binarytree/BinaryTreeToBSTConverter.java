package com.hrishikesh.practices.binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Problem:
 * Binary Tree to BST
 * For a given a binary tree, converts the binary tree to binary search tree(BST) and
 * returns the root of the converted binary search tree.
 * Note : The conversion must be done in such a way that keeps the original structure of Binary Tree.
 * ;
 * Solution:
 * - Traverse array in InOrder fashion and store node value in array
 * - Sort array
 * - Again traverse tree and replace node with array
 *
 * @author hrishikesh.mishra
 * @video https://www.youtube.com/watch?v=wBFttOncVUc
 * @link http://hrishikeshmishra.com/binary-tree-to-bst-creator/
 */
public class BinaryTreeToBSTConverter {

    public static void convert(BinaryTreeNode<Integer> root) {

        /** List to hold tree node value  **/
        List<Integer> list = new ArrayList<>();

        /** Populate node value in list **/
        populateNodeValueInList(root, list);

        /** Sort list in ascending order **/
        Collections.sort(list);

        /** Counter to hold current index because java doesn't support call reference **/
        AtomicInteger counter = new AtomicInteger(0);

        /** Replace node with sorted list **/
        replaceNodeValue(root, list, counter);
    }

    private static void replaceNodeValue(BinaryTreeNode<Integer> node, List<Integer> list, AtomicInteger counter) {

        /** Base case when node is null **/
        if (Objects.isNull(node)) {
            return;
        }

        /** InOrder processing  **/

        /** First go left **/
        replaceNodeValue(node.getLeft(), list, counter);

        /** Set sorted data to node **/
        node.setData(list.get(counter.get()));

        /** Increment counter value **/
        incrementCounterValue(counter);

        /** And now go to right child **/
        replaceNodeValue(node.getRight(), list, counter);

    }

    private static void incrementCounterValue(AtomicInteger counter) {
        counter.incrementAndGet();
    }


    private static void populateNodeValueInList(BinaryTreeNode<Integer> node, List<Integer> list) {

        /** Base case when node is null **/
        if (Objects.isNull(node)) {
            return;
        }

        /** InOrder traverse **/

        /** First go left **/
        populateNodeValueInList(node.getLeft(), list);

        /** Process node (here add node value in output list )**/
        list.add(node.getData());

        /** Then go right **/
        populateNodeValueInList(node.getRight(), list);
    }


}

class BinaryTreeToBSTConverterTest {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(10,
                new BinaryTreeNode<>(2,
                        new BinaryTreeNode<>(8),
                        new BinaryTreeNode<>(4)),
                new BinaryTreeNode<>(7)
        );

        System.out.println("Actual Tree ");
        BTPrinter.print(root);
        BinaryTreeToBSTConverter.convert(root);
        System.out.println("BST : ");
        BTPrinter.print(root);
    }
}
