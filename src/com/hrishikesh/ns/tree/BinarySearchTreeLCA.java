package com.hrishikesh.ns.tree;

import java.util.Objects;

/**
 * <p>
 *     Binary Search Tree Least Common Ancestor
 *
 *     @Link http://hrishikeshmishra.com/binary-search-tree-least-common-ancestor-lca/
 * </p>
 * Created by hrishikesh.mishra
 */
public class BinarySearchTreeLCA {

    public <E extends Comparable> BinaryTreeNode<E> getLCA(BinaryTreeNode<E> node, E a, E b){
        if(Objects.isNull(node)) return null;
        if(node.getData().equals(a) || node.getData().equals(b)) return node;
        else if(isGreaterThanBoth(node.getData(), a, b)) return getLCA(node.getLeft(), a, b);
        else if(isLessThanBoth(node.getData(), a, b)) return getLCA(node.getRight(), a , b);
        else return node;
    }

    private <E extends Comparable> boolean isGreaterThanBoth(E val, E a, E b){
        return (val.compareTo(a) > 0 && val.compareTo(b) > 0)? true : false;
    }

    private <E extends Comparable> boolean isLessThanBoth(E val, E a, E b){
        return (val.compareTo(a) < 0 && val.compareTo(b) < 0)? true : false;
    }
}

class BinarySearchTreeLCATest {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(4,
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

        BinarySearchTreeLCA lcaFinder = new BinarySearchTreeLCA();
        BinaryTreePrinter printer = new BinaryTreePrinter();
        printer.print(root);

        System.out.println("LCA for 2, 7 :" + lcaFinder.getLCA(root, 2, 7));
        System.out.println("LCA for 5, 11 :" + lcaFinder.getLCA(root, 5, 11));
        System.out.println("LCA for 7, 6:" + lcaFinder.getLCA(root, 7, 6));

    }
}