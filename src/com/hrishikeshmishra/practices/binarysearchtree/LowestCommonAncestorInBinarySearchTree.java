package com.hrishikeshmishra.practices.binarysearchtree;

import com.hrishikeshmishra.practices.binarytree.BTPrinter;
import com.hrishikeshmishra.practices.binarytree.BinaryTreeNode;

import java.util.Objects;

/**
 * Problem:
 * Lowest Common Ancestor in a Binary Search Tree.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/lowest-common-ancestor-in-a-binary-search-tree/
 */
public class LowestCommonAncestorInBinarySearchTree {

    /**
     * This algorithm assumes that both nodes are in tree
     *
     * @param node
     * @param x
     * @param y
     * @return
     */
    public static BinaryTreeNode<Integer> findLCA(BinaryTreeNode<Integer> node, int x, int y) {

        /** Base case when node is null **/
        if (Objects.isNull(node)) {
            return null;
        }

        /** If Node data is greater than both x and y then, find lca in left sub-tree  **/
        if (node.getData() > x && node.getData() > y) {
            return findLCA(node.getLeft(), x, y);
        } else if (node.getData() < x && node.getData() < y) {
            /** If node data is less than both x and y then, find lca in right sub-tree **/
            return findLCA(node.getRight(), x, y);
        } else {
            /** Else return node **/
            return node;
        }
    }
}

class LowestCommonAncestorInBinarySearchTreeTest {
    public static void main(String[] args) {

        BinaryTreeNode<Integer> bstRoot = new BinaryTreeNode<>(20,
                new BinaryTreeNode<>(3,
                        new BinaryTreeNode<>(2),
                        new BinaryTreeNode<>(10)),
                new BinaryTreeNode<>(30,
                        new BinaryTreeNode<>(27,
                                new BinaryTreeNode<>(26),
                                new BinaryTreeNode<>(28)),
                        new BinaryTreeNode<>(35)));

        BTPrinter.print(bstRoot);

        System.out.println("LCA 26 & 35 : " +
                LowestCommonAncestorInBinarySearchTree.findLCA(bstRoot, 26, 35));

        System.out.println("LCA 2 & 3 : " +
                LowestCommonAncestorInBinarySearchTree.findLCA(bstRoot, 2, 3));

        System.out.println("LCA 10 & 28 : " +
                LowestCommonAncestorInBinarySearchTree.findLCA(bstRoot, 10, 28));

    }
}
