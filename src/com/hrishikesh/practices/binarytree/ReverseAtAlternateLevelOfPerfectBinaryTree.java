package com.hrishikesh.practices.binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Problem:
 * Reverse alternate levels of a perfect binary tree
 * Given a Perfect Binary Tree, reverse the alternate level nodes of the binary tree.
 * ;
 * Solution:
 * - First traverse tree in InOrder and extract all elements of odd level from tree to list
 * - Reverse the elements of list
 * - Traverse again tree and replace elements of tree with list element.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/reverse-alternate-levels-of-a-perfect-binary-tree/
 */
public class ReverseAtAlternateLevelOfPerfectBinaryTree {

    public static void reverse(BinaryTreeNode<Integer> root) {

        /** Base case when tree is emtpy **/
        if (Objects.isNull(root)) {
            return;
        }

        /** List to hold element data of alternate level of tree **/
        List<Integer> list = new ArrayList<>();

        /** Populate list **/
        populateNodesOfAlternateLevelInList(root, list, 0);

        /** Reverse element of list **/
        Collections.reverse(list);

        /** Java doesn't support call by reference so, we need object ot hold list counter **/
        AtomicInteger listIndexCounter = new AtomicInteger(0);
        /** Now replace the value of nodes that are odd level **/
        replaceOddLevelNodeValues(root, list, 0, listIndexCounter);

    }

    private static void replaceOddLevelNodeValues(BinaryTreeNode<Integer> node, List<Integer> list,
                                                  int level, AtomicInteger listIndexCounter) {


        /** Base case when node is null **/
        if (Objects.isNull(node)) {
            return;
        }

        /** Perform in InOrder fashion **/

        /** First go to left subtree **/
        replaceOddLevelNodeValues(node.getLeft(), list, level + 1, listIndexCounter);

        /** Replace node only when level is odd **/
        if (level % 2 != 0) {
            node.setData(list.get(listIndexCounter.get()));

            /** Increment list index **/
            listIndexCounter.incrementAndGet();
        }


        /** Go to right subtree **/
        replaceOddLevelNodeValues(node.getRight(), list, level + 1, listIndexCounter);

    }

    private static void populateNodesOfAlternateLevelInList(BinaryTreeNode<Integer> node,
                                                            List<Integer> list, int level) {
        /** Base case **/
        if (Objects.isNull(node)) {
            return;
        }

        /** Process in InOrder fashion **/

        /** First go left sub tree **/
        populateNodesOfAlternateLevelInList(node.getLeft(), list, level + 1);

        /** Then, add node data to list only when level is odd **/
        if (level % 2 != 0) {
            list.add(node.getData());
        }

        /** And in last, go right sub tree **/
        populateNodesOfAlternateLevelInList(node.getRight(), list, level + 1);

    }

}

class ReverseAtAlternateLevelOfPerfectBinaryTreeTest {
    public static void main(String[] args) {

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1,
                new BinaryTreeNode<>(2,
                        new BinaryTreeNode<>(4,
                                new BinaryTreeNode<>(8),
                                new BinaryTreeNode<>(9)),
                        new BinaryTreeNode<>(5,
                                new BinaryTreeNode<>(10),
                                new BinaryTreeNode<>(11))),
                new BinaryTreeNode<>(3,
                        new BinaryTreeNode<>(6,
                                new BinaryTreeNode<>(12),
                                new BinaryTreeNode<>(13)),
                        new BinaryTreeNode<>(7,
                                new BinaryTreeNode<>(14),
                                new BinaryTreeNode<>(15))));


        BTPrinter.print(root);
        ReverseAtAlternateLevelOfPerfectBinaryTree.reverse(root);

        System.out.println("After reverse : ");
        BTPrinter.print(root);
    }
}
