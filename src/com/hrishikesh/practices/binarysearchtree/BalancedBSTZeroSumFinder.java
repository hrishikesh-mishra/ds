package com.hrishikesh.practices.binarysearchtree;

import com.hrishikesh.practices.binarytree.BTPrinter;
import com.hrishikesh.practices.binarytree.BinaryTreeNode;

import java.util.Objects;

/**
 * Problem:
 * Find if there is a triplet in a Balanced BST that adds to zero
 * Expected time complexity is O(n^2) and only O(Logn) extra space can be used. You can modify
 * given Binary Search Tree. Note that height of a Balanced BST is always O(Logn)
 * ;
 * Solution:
 * - Here it mentioned that we can modify the BST so, we create DLL from it {@link BinarySearchTreeToDoublyLinkedList}
 * - After that, we traverse DLL and check sum only for negative number
 * - For each negative number, we traverse DLL from head and tail in this way
 * - - First sum head and tail node data if they equal to provided node return true
 * - - If its less than provided node then move head pointer to next node
 * - - If its greater than, then move tail pointer to previous node
 * - - Repeat this process till head != tail
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/find-if-there-is-a-triplet-in-a-balanced-bst-that-adds-to-zero/
 */
public class BalancedBSTZeroSumFinder {

    private static class Container {
        private BinaryTreeNode<Integer> dllHead;
        private BinaryTreeNode<Integer> dllTail;
    }

    public static boolean isTripletPresent(BinaryTreeNode<Integer> root) {

        Container container = new Container();
        createDLL(root, container);

        BinaryTreeNode<Integer> temp = container.dllHead;

        /** Traverse DLL **/
        while (!Objects.isNull(temp)) {

            /** Check current node data is negative **/
            if (isNegativeNumber(temp.getData())) {

                /** Check is there any two node, whose sum equal to current node data **/
                if (checkSum(container.dllHead, container.dllTail, makePositive(temp.getData()))) {
                    return true;
                }
            }
        }

        return false;
    }

    private static int makePositive(int number) {
        return number * -1;
    }

    private static boolean checkSum(BinaryTreeNode<Integer> head, BinaryTreeNode<Integer> tail, int sum) {

        /** Iterate until head == tail **/
        while (head != tail) {

            /** Calculate sum of two nodes **/
            int localSum = head.getData() + tail.getData();

            /** If both sum equal to each other return true **/
            if (localSum == sum) {
                return true;
            } else if (localSum < sum) {
                /** If localSum lesser then move head right to get higher value node **/
                head = head.getRight();
            } else {
                /** If local sum larger then move tail left to get lesser value node **/
                tail = tail.getLeft();
            }
        }
        return false;
    }


    private static boolean isNegativeNumber(int number) {
        return number < 0;
    }

    private static void createDLL(BinaryTreeNode<Integer> root, Container container) {

        /** When root is null **/
        if (Objects.isNull(root)) {
            return;
        }

        /** Process InOrder fashion **/

        /** Go extreme left **/
        if (!Objects.isNull(root.getLeft())) {
            createDLL(root.getLeft(), container);
        }


        /** Previous node right pointer to current node **/
        if (!Objects.isNull(container.dllTail)) {
            container.dllTail.setRight(root);
        } else {
            /** Will execute only time to update dll head pointer **/
            container.dllHead = root;
        }

        root.setLeft(container.dllTail);
        container.dllTail = root;

        if (!Objects.isNull(root.getRight())) {
            createDLL(root.getRight(), container);
        }
    }
}


class BalancedBSTZeroSumFinderTest {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(6,
                new BinaryTreeNode<>(-13,
                        null,
                        new BinaryTreeNode<>(-8)),
                new BinaryTreeNode<>(14,
                        new BinaryTreeNode<>(13,
                                new BinaryTreeNode<>(7),
                                null),
                        new BinaryTreeNode<>(15)));

        BTPrinter.print(root);

        System.out.println("Is Triplet Present? : " + BalancedBSTZeroSumFinder.isTripletPresent(root));
    }
}
