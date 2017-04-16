package com.hrishikeshmishra.practices.binarytree;

import java.util.Objects;

/**
 * Problem:
 * Two Mirror Trees Checker
 * Given a Two Binary Trees, write a function that returns true if one is mirror of other, else returns false.
 * ;
 * Algorithm:
 * - Traverse tree in preOrder
 * - - If node1  is null && ndoe2 is null  then return tree
 * - - Check node1.data == node2.data
 * - - Recursively call with node1.left and node2.right
 * - - Recursively call with node1.right and node2.left
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/two-mirror-trees/
 */
public class MirrorBinaryTreeChecker {

    public static boolean check(BinaryTreeNode<Integer> node1, BinaryTreeNode<Integer> node2) {

        if (Objects.isNull(node1) && Objects.isNull(node2)) {
            return true;
        }

        return node1.getData() == node2.getData() &&
                check(node1.getLeft(), node2.getRight()) &&
                check(node1.getRight(), node2.getLeft());
    }
}

class MirrorBinaryTreeCheckerTest {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root1 = new BinaryTreeNode<>(1,
                new BinaryTreeNode<>(3),
                new BinaryTreeNode<>(2,
                        new BinaryTreeNode<>(5),
                        new BinaryTreeNode<>(4)));

        BinaryTreeNode<Integer> root2 = new BinaryTreeNode<>(1,
                new BinaryTreeNode<>(2,
                        new BinaryTreeNode<>(4),
                        new BinaryTreeNode<>(5)),
                new BinaryTreeNode<>(3));

        System.out.println("Tree1:");
        BTPrinter.print(root1);

        System.out.println("Tree2:");
        BTPrinter.print(root2);

        System.out.println("Are tree1 and tree2  mirror: " + MirrorBinaryTreeChecker.check(root1, root2));
    }
}
