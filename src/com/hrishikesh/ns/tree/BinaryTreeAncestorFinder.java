package com.hrishikesh.ns.tree;

import com.hrishikesh.ns.queue.LinkedQueue;
import com.hrishikesh.ns.queue.Queue;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Problem:
 * Find ancestor of a node or in other words,
 * we can say find a path between root and node.
 * ;
 * Solution:
 * 1. Traverse tree in inorder and store child parent map and
 * use this map to render path.
 * ;
 * 2. Traverse tree in inorder and whenever found return true and call
 * left child and right child recursively.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/binary-tree-ancestor-finder/
 * ;
 */
public class BinaryTreeAncestorFinder {

    public <E> boolean findAncestorRecursive(BinaryTreeNode<E> node, E data) {
        if (Objects.isNull(node)) return false;

        if (node.getData().equals(data) || findAncestorRecursive(node.getLeft(), data)
                || findAncestorRecursive(node.getRight(), data)) {
            System.out.print(node.getData() + " => ");
            return true;
        }

        return false;
    }

    public <E> void findAncestorNonRecursive(BinaryTreeNode<E> root, E data) {

        Queue<BinaryTreeNode<E>> queue = new LinkedQueue<>();
        Map<BinaryTreeNode<E>, BinaryTreeNode<E>> childParentMap = new HashMap<>();
        queue.enQueue(root);
        childParentMap.put(root, null);

        while (!queue.isEmpty()) {
            BinaryTreeNode<E> node = queue.deQueue();
            if (node.getData().equals(data)) {
                printAncestor(childParentMap, node);
                break;
            }
            if (!Objects.isNull(node.getLeft())) {
                queue.enQueue(node.getLeft());
                childParentMap.put(node.getLeft(), node);
            }
            if (!Objects.isNull(node.getRight())) {
                queue.enQueue(node.getRight());
                childParentMap.put(node.getRight(), node);
            }
        }
    }

    private <E> void printAncestor(Map<BinaryTreeNode<E>, BinaryTreeNode<E>> childParentMap, BinaryTreeNode<E> node) {
        while (!Objects.isNull(node)) {
            System.out.print(node.getData() + " => ");
            node = childParentMap.get(node);
        }
    }
}


class BinaryTreeAncestorFinderTest {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root1 = new BinaryTreeNode<>(1,
                new BinaryTreeNode<>(2,
                        new BinaryTreeNode<>(4,
                                new BinaryTreeNode<>(8),
                                null
                        ),
                        new BinaryTreeNode<>(5,
                                new BinaryTreeNode<>(6,
                                        null,
                                        new BinaryTreeNode<>(7)

                                ),
                                null
                        )
                ),
                new BinaryTreeNode<>(3)
        );

        BinaryTreePrinter printer = new BinaryTreePrinter();
        BinaryTreeAncestorFinder ancestorFinder = new BinaryTreeAncestorFinder();

        printer.print(root1);

        System.out.print("\nRecursive path for node 7: ");
        ancestorFinder.findAncestorRecursive(root1, 7);
        System.out.print("\nNon-Recursive path for node 7: ");
        ancestorFinder.findAncestorNonRecursive(root1, 7);

        System.out.print("\n\n\nRecursive path for node 3: ");
        ancestorFinder.findAncestorRecursive(root1, 3);
        System.out.print("\nNon-Recursive path for node 3: ");
        ancestorFinder.findAncestorNonRecursive(root1, 3);

    }
}