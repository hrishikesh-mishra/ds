package com.hrishikesh.ns.tree;

import com.hrishikesh.ns.queue.LinkedQueue;
import com.hrishikesh.ns.queue.Queue;

import java.util.Objects;

/**
 * Problem:
 * Find deepest node of binary tree
 * ;
 * Solution:
 * Deepest node in binary tree is last node of queue of BFS.
 * ;
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/find-deepest-node-of-binary-tree/
 */
public class BinaryDeepestNodeFinder {

    public <E> BinaryTreeNode<E> findDeepestNode(BinaryTreeNode<E> root) {
        if (Objects.isNull(root)) return null;

        Queue<BinaryTreeNode<E>> queue = new LinkedQueue<>();
        queue.enQueue(root);

        BinaryTreeNode<E> deepestNode = root;

        while (!queue.isEmpty()) {
            deepestNode = queue.deQueue();
            if (!Objects.isNull(deepestNode.getLeft())) queue.enQueue(deepestNode.getLeft());
            if (!Objects.isNull(deepestNode.getRight())) queue.enQueue(deepestNode.getRight());
        }

        return deepestNode;
    }
}

class BinaryDeepestNodeFinderTest {

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
                                new BinaryTreeNode<>("H",
                                        new BinaryTreeNode<>("J"),
                                        null
                                ),
                                null
                        )
                )
        );

        BinaryTreePrinter printer = new BinaryTreePrinter();
        printer.print(root);

        BinaryDeepestNodeFinder deepestNodeFinder = new BinaryDeepestNodeFinder();
        System.out.println("\n\nDeepest Node: " + deepestNodeFinder.findDeepestNode(root).getData());

    }
}

