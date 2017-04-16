package com.hrishikeshmishra.practices.binarytree;

import java.util.Objects;

/**
 * Problem:
 * Root to leaf path sum
 * ;
 * Given a Binary Tree and a sum s, your task is to check whether
 * there is a root to leaf path in that tree with the following sum.
 * ;
 * Algorithm:
 * - Traverse tree from root to leaf
 * - If  node is null then return false
 * - If its leaf and sum - leaf.data == 0 then return true
 * - Recursively call left child wilt sum - node.data
 * - Recursively call right child with sum - node.data
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/root-to-leaf-path-sum/
 */
public class RootTLeafPathSumChecker {

    public static boolean check(BinaryTreeNode<Integer> root, int sum) {

        /** Base case when node is null **/
        if (Objects.isNull(root)) {
            return false;
        }

        /** When node is leaf and sum becomes zero then only true **/
        if (isLeaf(root) && sum - root.getData() == 0) {
            return true;
        }

        /** Recursively check both children **/
        return check(root.getLeft(), sum - root.getData()) ||
                check(root.getRight(), sum - root.getData());

    }

    public static boolean isLeaf(BinaryTreeNode<Integer> node) {
        return Objects.isNull(node.getLeft()) && Objects.isNull(node.getRight());

    }
}


class RootTLeafPathSumCheckerTest {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(10,
                new BinaryTreeNode<>(8,
                        new BinaryTreeNode<>(3),
                        new BinaryTreeNode<>(5)),
                new BinaryTreeNode<>(2,
                        new BinaryTreeNode<>(2),
                        null));

        BTPrinter.print(root);

        System.out.println("Sum = 23 ?  " + RootTLeafPathSumChecker.check(root, 23));
        System.out.println("Sum = 18 ?  " + RootTLeafPathSumChecker.check(root, 18));
        System.out.println("Sum = 12 ?  " + RootTLeafPathSumChecker.check(root, 12));
        System.out.println("Sum = 14 ?  " + RootTLeafPathSumChecker.check(root, 14));
        System.out.println("Sum = 10 ?  " + RootTLeafPathSumChecker.check(root, 10));


    }
}