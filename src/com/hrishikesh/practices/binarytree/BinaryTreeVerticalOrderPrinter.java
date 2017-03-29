package com.hrishikesh.practices.binarytree;

import java.util.Objects;

/**
 * Problem:
 * Print a Binary Tree in Vertical Order
 * ;
 * Solution:
 * - There could be two different solution
 * - One is using sorted map, in which or every horizontal distance we all element
 * - Second, double Iteration over tree
 * - - First scan whole tree to find max and min distance from root
 * - - Again, scan whole tree and print base on vertical level
 * ;
 * ;
 * Algorithm for second solution:
 * - Iterate whole tree to find min and max horizontal distance
 * - Now iterate from min to max horizontal distance and print node of tree
 *
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/print-a-binary-tree-in-vertical-order/
 */
public class BinaryTreeVerticalOrderPrinter {

    private static class HorizontalDistance {
        private int min;
        private int max;
    }

    public static void printVertical(BinaryTreeNode<Integer> root) {

        HorizontalDistance distance = new HorizontalDistance();
        findMinMaxDistanceFromRoot(root, distance, 0);

        /** Iterate from minimum horizontal distance to maximum horizontal distance **/
        for (int i = distance.min; i <= distance.max; i++) {
            printVertical(root, i, 0);
            System.out.println();
        }
    }

    private static void printVertical(BinaryTreeNode<Integer> node, int lineNumber, int horizontalDistance) {

        /** Base case when node is null **/
        if (Objects.isNull(node)) {
            return;
        }

        /** Print only when lineNumber == horizontalDistance **/
        if (lineNumber == horizontalDistance) {
            System.out.print(node.getData() + " ");
        }

        /** Process left and right child recursively **/
        printVertical(node.getLeft(), lineNumber, horizontalDistance - 1);
        printVertical(node.getRight(), lineNumber, horizontalDistance + 1);
    }


    private static void findMinMaxDistanceFromRoot(BinaryTreeNode<Integer> node,
                                                   HorizontalDistance maxMinDistance, int currentHorizontalDistance) {

        /** Base case when node is null **/
        if (Objects.isNull(node)) {
            return;
        }

        /** Update minimum horizontal distance value **/
        if (currentHorizontalDistance < maxMinDistance.min) {
            maxMinDistance.min = currentHorizontalDistance;
        }

        /** Update maximum horizontal distance value **/
        if (currentHorizontalDistance > maxMinDistance.max) {
            maxMinDistance.max = currentHorizontalDistance;
        }

        /** Recursively call for left sub tree **/
        findMinMaxDistanceFromRoot(node.getLeft(), maxMinDistance, currentHorizontalDistance - 1);

        /** Recursively call for right sub tree **/
        findMinMaxDistanceFromRoot(node.getRight(), maxMinDistance, currentHorizontalDistance + 1);
    }
}

class BinaryTreeVerticalOrderPrinterTest {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1,
                new BinaryTreeNode<>(2,
                        new BinaryTreeNode<>(4),
                        new BinaryTreeNode<>(5)),
                new BinaryTreeNode<>(3,
                        new BinaryTreeNode<>(6,
                                null,
                                new BinaryTreeNode<>(8)),
                        new BinaryTreeNode<>(7,
                                null,
                                new BinaryTreeNode<>(9))));


        BTPrinter.print(root);
        System.out.println("Vertical Printing : ");
        BinaryTreeVerticalOrderPrinter.printVertical(root);

    }
}
