package com.hrishikesh.practices.binarytree;

import java.util.Objects;

/**
 * Problem:
 * Lowest Common Ancestor Binary Tree
 * ;
 * Solution:
 * - Traverse tree in PostOrder fashion
 * -  If node is null then return null.
 * -  If node is equal to n1 or n2 return node
 * -  If left and right both are not null then return node and its a LCA
 * -  If at most of node is null then return not null node.
 *
 * @author hrishikesh.mishra
 * @video https://www.youtube.com/watch?v=13m9ZCB8gjw
 * @link http://hrishikeshmishra.com/lowest-common-ancestor-binary-tree/
 */
public class BinaryTreeLowestCommonAncestor {

    /**
     * <p>
     * This algorithm assume that both nodes are in tree.
     * </p>
     *
     * @param node
     * @param n1
     * @param n2
     * @return
     */
    public static BinaryTreeNode<Integer> findLCA(BinaryTreeNode<Integer> node, int n1, int n2) {

        /** Base case when node is null **/
        if (Objects.isNull(node)) {
            return null;
        }

        /** If node data is equal to n1 or n2 **/
        if (node.getData() == n1 || node.getData() == n2) {
            return node;
        }

        BinaryTreeNode<Integer> left = findLCA(node.getLeft(), n1, n2);
        BinaryTreeNode<Integer> right = findLCA(node.getRight(), n1, n2);

        /** If both left and right are not null then LCA is current node so, return this. **/
        if (!Objects.isNull(left) && !Objects.isNull(right)) {
            return node;
        } else {
            /** Else return not null child **/
            return Objects.isNull(left) ? right : left;
        }
    }
}


class BinaryTreeLowestCommonAncestorTest {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(3,
                new BinaryTreeNode<>(6,
                        new BinaryTreeNode<>(2),
                        new BinaryTreeNode<>(11,
                                new BinaryTreeNode<>(9),
                                new BinaryTreeNode<>(5))),
                new BinaryTreeNode<>(8,
                        null,
                        new BinaryTreeNode<>(13,
                                new BinaryTreeNode<>(7),
                                null)));

        BTPrinter.print(root);

        System.out.println("LCA of 2 and 5 :" + BinaryTreeLowestCommonAncestor.findLCA(root, 2, 5).getData());
        System.out.println("LCA of 9 and 13 :" + BinaryTreeLowestCommonAncestor.findLCA(root, 9, 13).getData());
        System.out.println("LCA of 8 and 7 :" + BinaryTreeLowestCommonAncestor.findLCA(root, 8, 7).getData());

    }
}