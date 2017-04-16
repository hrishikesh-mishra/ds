package com.hrishikeshmishra.practices.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Problem:
 * Construct Binary Tree from Parent Array
 * Given an array that represents a Tree in such a way that array
 * indexes are values in tree nodes and array values give the parent
 * node of that particular index (or node). The value of the root node
 * index would always be -1 as there is no parent for root.
 * ;
 * Solution:
 * - Create an extra array
 * - Iterate twice parent array
 * - - First time only create node
 * - - second time update parent-child association link
 * ;
 * ;
 * Algorithm:
 * - Create an extra array to hold binary tree node
 * - Iterate from given array from index 0 to n-1
 * - - create node with index and put that in extra array
 * - Iterate from given array from index 0 to n-1
 * - - Get node for index and value from extra array
 * - - Add parent child association between these nodes
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/construct-binary-tree-from-parent-array/
 */
public class BinaryTreeFromParentArray {

    public static BinaryTreeNode<Integer> create(int[] parentArray) {
        /** Node to hold root reference **/
        BinaryTreeNode<Integer> root = null;

        /** Array hold nodes **/
        List<BinaryTreeNode<Integer>> nodeArray = new ArrayList<>();

        /** Create nodes **/
        for (int i = 0; i < parentArray.length; i++) {
            nodeArray.add(new BinaryTreeNode<>(i));
        }

        /** Update children of nodes **/
        for (int i = 0; i < parentArray.length; i++) {

            /** Check is root node **/
            if (isRootNode(parentArray[i])) {
                root = nodeArray.get(i);
            } else {
                addChild(nodeArray.get(parentArray[i]), nodeArray.get(i));
            }
        }

        return root;
    }

    private static boolean isRootNode(int i) {
        return i == -1;
    }

    private static void addChild(BinaryTreeNode<Integer> parent, BinaryTreeNode<Integer> child) {
        /** Add child to left only when left is null otherwise add to righ **/
        if (Objects.isNull(parent.getLeft())) {
            parent.setLeft(child);
        } else {
            parent.setRight(child);
        }
    }
}


class BinaryTreeFromParentArrayTest {
    public static void main(String[] args) {
        int[] parentArray1 = {-1, 0, 0, 1, 1, 3, 5};
        int[] parentArray2 = {1, 5, 5, 2, 2, -1, 3};

        System.out.print("Array is : ");
        printArray(parentArray1);

        BinaryTreeNode<Integer> root1 = BinaryTreeFromParentArray.create(parentArray1);
        System.out.println("Print created tree");
        BTPrinter.print(root1);

        System.out.println("---------------------------------------------------------------");

        System.out.print("Array is : ");
        printArray(parentArray2);

        BinaryTreeNode<Integer> root2 = BinaryTreeFromParentArray.create(parentArray2);
        System.out.println("Print created tree");
        BTPrinter.print(root2);

    }

    private static void printArray(int[] array) {

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }

    }


}