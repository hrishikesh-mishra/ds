package com.hrishikeshmishra.ns.tree;

import java.util.Objects;

/**
 * Problem:
 * Binary Tree searcher
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/binary-tree-searcher/
 */
public class BinaryTreeSearcher<E> {

    public boolean search(BinaryTreeNode<E> node, E data) {
        if (Objects.isNull(node))
            return false;

        if (node.getData().equals(data))
            return true;

        return (search(node.getLeft(), data) || search(node.getRight(), data));
    }
}


class BinaryTreeSearcherTest {
    public static void main(String[] args) {
        BinaryTreeNode<String> root = new BinaryTreeNode<>("F",
                new BinaryTreeNode<>("B",
                        new BinaryTreeNode<>("A"),
                        new BinaryTreeNode<>("D",
                                new BinaryTreeNode<>("C"),
                                new BinaryTreeNode<>("E")
                        )
                ),
                new BinaryTreeNode<>("G",
                        null,
                        new BinaryTreeNode<>("I",
                                new BinaryTreeNode<>("H"),
                                null
                        )
                )
        );

        BinaryTreeSearcher<String> treeSearcher = new BinaryTreeSearcher<>();
        System.out.println("Search 'A' :" + treeSearcher.search(root, "A"));
        System.out.println("Search 'R' :" + treeSearcher.search(root, "R"));
    }
}