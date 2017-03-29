package com.hrishikesh.ns.tree;

import com.hrishikesh.ns.queue.LinkedQueue;
import com.hrishikesh.ns.queue.Queue;

import java.util.Objects;

/**
 * Problem
 * Given an algorithm for finding the level that has maximum sum in the binary tree.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/maximum-sum-path-in-the-binary-tree/
 */
public class BinaryTreeLevelMaxSumFinder {

    public <E> int getMaxSumOfLevel(BinaryTreeNode<E> root) {
        if (Objects.isNull(root)) return 0;
        Queue<BinaryTreeNode<E>> queue = new LinkedQueue<BinaryTreeNode<E>>();

        queue.enQueue(root);
        queue.enQueue(null);
        int maxSum = 0, currentLevelSum = 0;
        while (!queue.isEmpty()) {
            BinaryTreeNode<E> node = queue.deQueue();

            if (!Objects.isNull(node)) {
                currentLevelSum += (Integer) node.getData();
                if (!Objects.isNull(node.getLeft())) queue.enQueue(node.getLeft());
                if (!Objects.isNull(node.getRight())) queue.enQueue(node.getRight());
            } else {
                if (!queue.isEmpty()) {
                    maxSum = Math.max(maxSum, currentLevelSum);
                    currentLevelSum = 0;
                    queue.enQueue(null);
                }
            }
        }
        return maxSum;
    }
}

class BinaryTreeLevelMaxSumFinderTest {

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
        BinaryTreeLevelMaxSumFinder maxSumFinder = new BinaryTreeLevelMaxSumFinder();

        printer.print(root1);
        System.out.println("Max sum  is : " + maxSumFinder.getMaxSumOfLevel(root1));

    }
}