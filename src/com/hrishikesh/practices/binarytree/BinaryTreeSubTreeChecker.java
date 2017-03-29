package com.hrishikesh.practices.binarytree;

import java.util.Objects;

/**
 *
 * Problem:
 * Check if subtree
 * Given two binary trees, the task is to complete function isSubtree,
 * Solution:
 * - @// TODO: Uncompleted
 *
 * @author hrishikesh.mishra
 * @link https://www.youtube.com/watch?v=J29eAoniIsw
 * @link http://hrishikeshmishra.com/check-if-t2-is-subtree-of-t1-in-binary-tree/
 */
public class BinaryTreeSubTreeChecker {

    public static boolean isSubTree(BinaryTreeNode<Integer> t1, BinaryTreeNode<Integer> t2) {

        /** A null tree is subtree of every tree **/
        if (Objects.isNull(t2)) {
            return true;
        }

        /** If main tree is null then its false **/
        if (Objects.isNull(t1)) {
            return false;
        }

        /** If node value of t1 == t2 **/
        if (t1.getData().equals(t2.getData())) {

            /** Validate left child and right child for bot trees **/
            if (isSubTree(t1.getLeft(), t2.getLeft()) &&
                    isSubTree(t1.getRight(), t2.getRight())) {
                return true;
            }
        }

        return isSubTree(t1.getLeft(), t2) || /** Check t1 left subtree for t2 **/
                isSubTree(t1.getRight(), t2); /** Or check in right subtree of t2 **/
    }
}

class BinaryTreeSubTreeCheckerTest {

    public static void main(String[] args) {

        BinaryTreeNode<Integer> t1 = new BinaryTreeNode<>(1,
                new BinaryTreeNode<>(2,
                        new BinaryTreeNode<>(3),
                        new BinaryTreeNode<>(4,
                                new BinaryTreeNode<>(6),
                                null)),
                new BinaryTreeNode<>(5));

        BinaryTreeNode<Integer> t2 = new BinaryTreeNode<>(2,
                new BinaryTreeNode<>(3),
                new BinaryTreeNode<>(4,
                        new BinaryTreeNode<>(6),
                        null));

        System.out.println("T1");
        BTPrinter.print(t1);
        System.out.println("\nT2");
        BTPrinter.print(t2);
        System.out.println("Is T2 SubTree of T3 : " + BinaryTreeSubTreeChecker.isSubTree(t1, t2));

        System.out.println("\n-----------------------------------------------------------------");

        t2 = new BinaryTreeNode<>(2,
                new BinaryTreeNode<>(3),
                new BinaryTreeNode<>(1));

        System.out.println("T1");
        BTPrinter.print(t1);
        System.out.println("\nT2");
        BTPrinter.print(t2);
        System.out.println("Is T2 SubTree of T3 : " + BinaryTreeSubTreeChecker.isSubTree(t1, t2));


        System.out.println("\n-----------------------------------------------------------------");

        t1 = new BinaryTreeNode<>(3,
                new BinaryTreeNode<>(2,
                        new BinaryTreeNode<>(4),
                        new BinaryTreeNode<>(5,
                                new BinaryTreeNode<>(8),
                                new BinaryTreeNode<>(9))),
                new BinaryTreeNode<>(2,
                        new BinaryTreeNode<>(4),
                        new BinaryTreeNode<>(5,
                                new BinaryTreeNode<>(9),
                                new BinaryTreeNode<>(8))));

        t2 = new BinaryTreeNode<>(2,
                new BinaryTreeNode<>(4),
                new BinaryTreeNode<>(5,
                        new BinaryTreeNode<>(9),
                        new BinaryTreeNode<>(8)));

        System.out.println("T1");
        BTPrinter.print(t1);
        System.out.println("\nT2");
        BTPrinter.print(t2);
        System.out.println("Is T2 SubTree of T3 : " + BinaryTreeSubTreeChecker.isSubTree(t1, t2));



    }
}
