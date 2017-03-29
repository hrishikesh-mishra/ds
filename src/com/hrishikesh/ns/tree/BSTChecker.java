package com.hrishikesh.ns.tree;

import java.util.Objects;

/**
 * Problem:
 * Check tree is BST or not.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/bst-checker/
 */
public class BSTChecker {

    public <E extends Comparable> boolean isBST(BinaryTreeNode<E> node) {
        if (Objects.isNull(node)) return true;

        if (!Objects.isNull(node.getLeft())
                && node.getData().compareTo(findMin(node.getLeft()).getData()) < 0)
            return false;

        if (!Objects.isNull(node.getRight())
                && node.getData().compareTo(findMax(node.getRight()).getData()) > 0)
            return false;

        if (!isBST(node.getLeft()) || !isBST(node.getRight()))
            return false;

        return true;
    }


    private <E extends Comparable> BinaryTreeNode<E> findMin(BinaryTreeNode<E> node) {
        if (Objects.isNull(node) || Objects.isNull(node.getLeft())) return node;
        return findMin(node.getLeft());
    }

    public <E extends Comparable> BinaryTreeNode<E> findMax(BinaryTreeNode<E> node) {
        if (Objects.isNull(node) || Objects.isNull(node.getRight())) return node;
        return findMax(node.getRight());
    }
}

class BSTCheckerTest {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root1 = new BinaryTreeNode<>(4,
                new BinaryTreeNode<>(2),
                new BinaryTreeNode<>(8,
                        new BinaryTreeNode<>(5,
                                null,
                                new BinaryTreeNode<>(7,
                                        new BinaryTreeNode<>(6),
                                        null
                                )
                        ),
                        new BinaryTreeNode<>(11)

                )
        );

        BinaryTreeNode<Integer> root2 = new BinaryTreeNode<>(4,
                new BinaryTreeNode<>(2),
                new BinaryTreeNode<>(8,
                        new BinaryTreeNode<>(5,
                                null,
                                new BinaryTreeNode<>(7,
                                        new BinaryTreeNode<>(6),
                                        null
                                )
                        ),
                        new BinaryTreeNode<>(1)

                )
        );

        BinaryTreePrinter printer = new BinaryTreePrinter();
        BSTChecker bstChecker = new BSTChecker();

        printer.print(root1);
        System.out.println("Is BST ?  " + bstChecker.isBST(root1));

        printer.print(root2);
        System.out.println("Is BST ?  " + bstChecker.isBST(root2));

    }
}
