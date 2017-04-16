package com.hrishikeshmishra.practices.binarysearchtree;

import com.hrishikeshmishra.practices.binarytree.BTPrinter;
import com.hrishikeshmishra.practices.binarytree.BinaryTreeNode;

import java.util.Objects;

/**
 * Problem:
 * In Binary Search Tree two nodes are out of order
 * ;
 * In given binary search tree two nodes are swapped, fix this.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/binary-search-tree-two-nodes-order/
 */
public class BinarySearchTreeTwoOutOfOrderNode {

    private static class Nodes {
        private BinaryTreeNode<Integer> previousNode;
        private BinaryTreeNode<Integer> firstSwappedNode;
        private BinaryTreeNode<Integer> secondSwappedNode;
    }

    public static void fix(BinaryTreeNode<Integer> root) {
        Nodes nodes = new Nodes();

        fixHelper(root, nodes);

        BinaryTreeNode<Integer> firstNode = nodes.firstSwappedNode;
        BinaryTreeNode<Integer> secondNode = nodes.secondSwappedNode;

        if (!Objects.isNull(firstNode) && !Objects.isNull(secondNode)) {
            int temp = firstNode.getData();
            firstNode.setData(secondNode.getData());
            secondNode.setData(temp);
        }
    }

    private static void fixHelper(BinaryTreeNode<Integer> node, Nodes nodes) {
        if (Objects.isNull(node)) {
            return;
        }

        fixHelper(node.getLeft(), nodes);

        checkNodeValues(node, nodes);
        nodes.previousNode = node;

        fixHelper(node.getRight(), nodes);

    }

    private static void checkNodeValues(BinaryTreeNode<Integer> node, Nodes nodes) {

        if (Objects.isNull(nodes.previousNode)) {
            return;
        }

        if (node.getData() < nodes.previousNode.getData()) {
            addInvalidNode(nodes.previousNode, node,  nodes);
        }

    }

    private static void addInvalidNode(BinaryTreeNode<Integer> node1 ,
                                       BinaryTreeNode<Integer> node2, Nodes nodes) {
        if (Objects.isNull(nodes.firstSwappedNode)) {
            nodes.firstSwappedNode = node1;
        } else if (Objects.isNull(nodes.secondSwappedNode)) {
            nodes.secondSwappedNode = node2;
        } else {
            throw new RuntimeException("More than two nodes are invalid!");
        }
    }
}

class BinarySearchTreeTwoOutOfOrderNodeTest {

    public static void main(String[] args) {

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(100,
                new BinaryTreeNode<>(120,
                        new BinaryTreeNode<>(30),
                        new BinaryTreeNode<>(80)),
                new BinaryTreeNode<>(70,
                        new BinaryTreeNode<>(110),
                        new BinaryTreeNode<>(130)));

        BTPrinter.print(root);

        System.out.print("Before fix: ");
        traverseInOrder(root);
        BinarySearchTreeTwoOutOfOrderNode.fix(root);
        System.out.print("\nAfter fix: ");
        traverseInOrder(root);

    }

    public static void traverseInOrder(BinaryTreeNode<Integer> node) {
        if (Objects.isNull(node)) {
            return;
        }

        traverseInOrder(node.getLeft());
        System.out.printf("%d ", node.getData());
        traverseInOrder(node.getRight());
    }
}