package com.hrishikesh.practices.binarytree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * Problem:
 * Binary Tree Isomorphic Checker
 * Given two binary trees check they are isomorphic for not.
 * Two trees are isomorphic if they have the same shape, means we can get exact same tree by just swapping
 * left and right children of a number of nodes.
 *
 * @author hrishikesh.mishra
 */
public class BinaryTreeIsomorphicChecker {

    public static boolean check(BinaryTreeNode<Integer> root1, BinaryTreeNode<Integer> root2) {

        Queue<BinaryTreeNode<Integer>> queue1 = new LinkedList<>();
        Queue<BinaryTreeNode<Integer>> queue2 = new LinkedList<>();

        queue1.add(root1);
        queue2.add(root2);

        /** Iterating till both queues are not empty **/
        while (!queue1.isEmpty() && !queue2.isEmpty()) {


            BinaryTreeNode<Integer> node1 = queue1.remove();
            BinaryTreeNode<Integer> node2 = queue2.remove();

            /** Comparing nodes value **/
            if (!node1.getData().equals(node2.getData())) {
                return false;
            }


            /** When node 1 left child is not empty **/
            if (!Objects.isNull(node1.getLeft())) {

                /** Comparing node 1 left child value with node 2 left child value **/
                if (!Objects.isNull(node2.getLeft()) &&
                        node1.getLeft().getData().equals(node2.getLeft().getData())) {

                    queue1.add(node1.getLeft());
                    queue2.add(node2.getLeft());

                }
                /** Comparing  node 1 left child value with node 2 right child value **/
                else if (!Objects.isNull(node2.getRight()) &&
                        node1.getLeft().getData().equals(node2.getRight().getData())) {

                    queue1.add(node1.getLeft());
                    queue2.add(node2.getRight());

                }
                /** When node 1 value not matched with any node 2 **/
                else {
                    return false;
                }

            }

            /** When node 1 right child value is not empty **/
            if (!Objects.isNull(node1.getRight())) {

                /** Comparing node1 right value with node2 right value **/
                if (!Objects.isNull(node2.getRight()) &&
                        node1.getRight().getData().equals(node2.getRight().getData())) {

                    queue1.add(node1.getRight());
                    queue2.add(node2.getRight());

                }
                /** Comparing node 1 right child value with node 2 left child value **/
                else if (!Objects.isNull(node2.getLeft()) &&
                        node1.getRight().getData().equals(node2.getLeft().getData())) {

                    queue1.add(node1.getRight());
                    queue2.add(node2.getLeft());
                }
                /** When node 1 right child value not matched with node 2 children **/
                else {
                    return false;
                }
            }

            /** When number of children mismatched **/
            if (getNoOfChildren(node1) != getNoOfChildren(node2)) {
                return false;
            }
        }

        /** Both queues must be drained **/
        return queue1.size() == queue2.size();
    }

    private static int getNoOfChildren(BinaryTreeNode<Integer> node) {
        int children = 0;

        if (!Objects.isNull(node.getLeft())) {
            children++;
        }

        if (!Objects.isNull(node.getRight())) {
            children++;
        }

        return children;

    }
}


class BinaryTreeIsomorphicCheckerTest {
    public static void main(String[] args) {

        BinaryTreeNode<Integer> root1 = new BinaryTreeNode<>(1,
                new BinaryTreeNode<>(2,
                        new BinaryTreeNode<>(4),
                        new BinaryTreeNode<>(5,
                                new BinaryTreeNode<>(7),
                                new BinaryTreeNode<>(8))),
                new BinaryTreeNode<>(3,
                        new BinaryTreeNode<>(6),
                        null));

        BinaryTreeNode<Integer> root2 = new BinaryTreeNode<>(1,
                new BinaryTreeNode<>(3,
                        null,
                        new BinaryTreeNode<>(6)),
                new BinaryTreeNode<>(2,
                        new BinaryTreeNode<>(4),
                        new BinaryTreeNode<>(5,
                                new BinaryTreeNode<>(8),
                                new BinaryTreeNode<>(7))));

        System.out.println("Binary Tree 1: ");
        BTPrinter.print(root1);

        System.out.println("Binary Tree 2: ");
        BTPrinter.print(root2);

        System.out.println("Isomorphic Tree ? " + BinaryTreeIsomorphicChecker.check(root1, root2));


    }
}