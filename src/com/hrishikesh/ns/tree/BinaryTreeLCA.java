package com.hrishikesh.ns.tree;

import java.util.Objects;

/**
 * <p>
 *     Binary Tree LCA
 *
 *     {@link "http://hrishikeshmishra.com/lowest-common-ancestor-lca-of-binary-tree/"}
 * </p>
 * Created by hrishikesh.mishra
 */
public class BinaryTreeLCA {

    public BinaryTreeNode<Integer> findLCA(BinaryTreeNode<Integer> node, int a, int b){
        if (Objects.isNull(node)) return null;

        /** if one of the value matches with node data then, return node. **/
        if(node.getData() == a  || node.getData() == b)  return node;

        /** Recursively call both subtree of node. **/
        BinaryTreeNode<Integer> left = findLCA(node.getLeft(), a, b);
        BinaryTreeNode<Integer> right = findLCA(node.getRight(), a, b);

        /** If both found in either left or right subtree **/
        if(!Objects.isNull(left) && !Objects.isNull(right)) return node;

        /** If no value found in both subtree **/
        if(Objects.isNull(left)  && Objects.isNull(right)) return null;

        /** If one of the value found in tree. **/
        return (!Objects.isNull(left) ? left: right);
    }
}

class  BinaryTreeLCATest {
    public static void main(String[] args) {

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(3,
                new BinaryTreeNode<>(6,
                        new BinaryTreeNode<>(2),
                        new BinaryTreeNode<>(11,
                                new BinaryTreeNode<>(9),
                                new BinaryTreeNode<>(5)
                        )
                ),
                new BinaryTreeNode<>(8,
                        null,
                        new BinaryTreeNode<>(13,
                                new BinaryTreeNode<>(3),
                                null
                        )
                )
        );


        BinaryTreePrinter printer = new BinaryTreePrinter();
        printer.print(root);

        BinaryTreeLCA lcaFinder = new BinaryTreeLCA();
        System.out.println("LCA of 2 and 5:  " + lcaFinder.findLCA(root, 2, 5));
        System.out.println("LCA of 11 and 2:  " + lcaFinder.findLCA(root, 11, 2));
        System.out.println("LCA of 8 and 7:  " + lcaFinder.findLCA(root, 8, 7));

        /** This is the problem with this implementation **/
        System.out.println("LCA of 8 and 70:  " + lcaFinder.findLCA(root, 8, 70));

    }
}
