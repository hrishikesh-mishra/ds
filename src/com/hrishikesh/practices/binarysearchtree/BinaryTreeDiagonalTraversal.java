package com.hrishikesh.practices.binarysearchtree;

import com.hrishikesh.practices.binarytree.BTPrinter;
import com.hrishikesh.practices.binarytree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Problem:
 * Binary Tree Diagonal Traversal
 *
 * @author hrishikesh.mishra
 */
public class BinaryTreeDiagonalTraversal {

    public static void print(BinaryTreeNode<Integer> root) {
        Map<Integer, List<Integer>> diagonalMap = new LinkedHashMap<>();
        printHelper(root, diagonalMap, 0);

        for (Map.Entry<Integer, List<Integer>> entry : diagonalMap.entrySet()) {
            System.out.printf("Diagonal : %d, Nodes: %s\n", entry.getKey(), entry.getValue());
        }

    }

    private static void printHelper(BinaryTreeNode<Integer> node, Map<Integer, List<Integer>> diagonalMap, int diagonal) {

        if (Objects.isNull(node)) {
            return;
        }

        List<Integer> diagonalNodes = diagonalMap.getOrDefault(diagonal, new ArrayList<>());
        diagonalNodes.add(node.getData());
        diagonalMap.put(diagonal, diagonalNodes);

        printHelper(node.getLeft(), diagonalMap, diagonal + 1);
        printHelper(node.getRight(), diagonalMap, diagonal);

    }


}


class BinaryTreeDiagonalTraversalTest {
    public static void main(String[] args) {

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(8,
                new BinaryTreeNode<>(3,
                        new BinaryTreeNode<>(1),
                        new BinaryTreeNode<>(6,
                                new BinaryTreeNode<>(4),
                                new BinaryTreeNode<>(7))),
                new BinaryTreeNode<>(10,
                        null,
                        new BinaryTreeNode<>(14,
                                new BinaryTreeNode<>(13),
                                null)));

        BTPrinter.print(root);

        System.out.println("Diagonal Print: ");
        BinaryTreeDiagonalTraversal.print(root);
    }
}