package com.hrishikesh.practices.binarytree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * Problem:
 * Extreme nodes in alternate order
 * Given a binary tree, print nodes of extreme corners of each level but in alternate order.
 * – print rightmost node of 1st level
 * – print leftmost node of 2nd level
 * – print rightmost node of 3rd level
 * – print leftmost node of 4th level
 * – print rightmost node of 5th level
 * ;
 * ;
 * Algorithm:
 * - Set flag = leftMost
 * - Traverse tree in level order
 * - At each level print either first or last element of level based on flag value
 * - After print switch the flag
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/extreme-nodes-print-in-alternate-order-in-binary-tree/
 */
public class BinaryTreeAlternateExtremeNodesPrinter {

    public static void print(BinaryTreeNode<Integer> root) {
        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);


        /** Flag to indicate which should print at current level **/
        boolean isRightMostPrint = true;

        /** Flag to indicate when fresh level started **/
        boolean isFreshLevelProcessingStarted = false;

        /** Last element of each level **/
        int lastElement = root.getData();

        /** First element of each level **/
        int firstElement = root.getData();

        /** Iterate all element of tree **/
        while (!queue.isEmpty()) {

            /** Dequeue first element of queue **/
            BinaryTreeNode<Integer> node = queue.remove();

            /** Check is new level started or not **/
            if (!Objects.isNull(node)) {

                /** Check is fresh started or not, if yes then update current node value to firstElement **/
                if (isFreshLevelProcessingStarted) {
                    firstElement = node.getData();
                    isFreshLevelProcessingStarted = false;
                }

                /** Push left child to queue **/
                addToQueueIfNotNull(queue, node.getLeft());

                /** Push right child to queue **/
                addToQueueIfNotNull(queue, node.getRight());

                /** Update last element value to current node value **/
                lastElement = node.getData();

            } else {

                /** Check which one should print for previous level **/
                if (isRightMostPrint) {
                    System.out.print(lastElement + " ");
                } else {
                    System.out.print(firstElement + " ");
                }

                /** Alternate flag **/
                isRightMostPrint = !isRightMostPrint;

                /** Changing level **/
                if (!queue.isEmpty()) {
                    queue.add(null);
                }

                /** Reset flag for fresh level start **/
                isFreshLevelProcessingStarted = true;
            }
        }
    }

    private static void addToQueueIfNotNull(Queue<BinaryTreeNode<Integer>> queue,
                                            BinaryTreeNode<Integer> node) {
        if (!Objects.isNull(node)) {
            queue.add(node);
        }
    }
}

class BinaryTreeAlternateExtremeNodesPrinterTest {

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(25,
                new BinaryTreeNode<>(18,
                        new BinaryTreeNode<>(19,
                                null,
                                new BinaryTreeNode<>(15)),
                        new BinaryTreeNode<>(20,
                                new BinaryTreeNode<>(18),
                                new BinaryTreeNode<>(25))),
                new BinaryTreeNode<>(50,
                        new BinaryTreeNode<>(35,
                                new BinaryTreeNode<>(20,
                                        null,
                                        new BinaryTreeNode<>(25)),
                                new BinaryTreeNode<>(40)),
                        new BinaryTreeNode<>(60,
                                new BinaryTreeNode<>(55),
                                new BinaryTreeNode<>(70)))
        );


        BTPrinter.print(root);

        System.out.print("\nExtreme nodes in alternate order : ");
        BinaryTreeAlternateExtremeNodesPrinter.print(root);
    }
}

