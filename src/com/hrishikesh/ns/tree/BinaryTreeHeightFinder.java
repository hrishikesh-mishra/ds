package com.hrishikesh.ns.tree;


import com.hrishikesh.ns.queue.LinkedQueue;
import com.hrishikesh.ns.queue.Queue;

import java.util.Objects;

/**
 * Problem:
 * Find height of binary tree
 * 1. max depth
 * 2. min depth
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/binary-tree-max-depth-min-depth/
 */
public class BinaryTreeHeightFinder {

    public <E> int findHeight(BinaryTreeNode<E> node) {
        if (Objects.isNull(node)) return 0;

        return Math.max(
                findHeight(node.getLeft()),
                findHeight(node.getRight())
        ) + 1;
    }

    /**
     * Traverse in BFS and find first node that has no child
     * that will min height ;
     *
     * @param root
     * @param <E>
     * @return
     */
    public <E> int findMinimumHeight(BinaryTreeNode<E> root) {
        if (Objects.isNull(root)) return 0;

        Queue<BinaryTreeNode<E>> queue = new LinkedQueue<>();
        queue.enQueue(root);
        queue.enQueue(null);

        int minHeight = 1;
        while (!queue.isEmpty()) {
            BinaryTreeNode<E> node = queue.deQueue();
            if (!Objects.isNull(node)) {
                if (Objects.isNull(node.getLeft()) && Objects.isNull(node.getLeft()))
                    return minHeight + 1;

                if (!Objects.isNull(node.getLeft())) queue.enQueue(node.getLeft());
                if (!Objects.isNull(node.getRight())) queue.enQueue(node.getRight());
            } else {
                if (!queue.isEmpty()) {
                    minHeight++;
                    queue.enQueue(null);
                }
            }
        }
        return minHeight;
    }
}

class BinaryTreeHeightFinderTest {
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

        BinaryTreeHeightFinder finder = new BinaryTreeHeightFinder();
        BinaryTreePrinter printer = new BinaryTreePrinter();

        System.out.println("\n\nTree : \n ");
        printer.print(root);

        System.out.println("Height of binary tree: " + finder.findHeight(root));
        System.out.println("Min height of binary tree: " + finder.findMinimumHeight(root));

    }
}