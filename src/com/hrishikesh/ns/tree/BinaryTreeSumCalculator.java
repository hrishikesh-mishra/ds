package com.hrishikesh.ns.tree;

import com.hrishikesh.ns.queue.LinkedQueue;
import com.hrishikesh.ns.queue.Queue;

import java.util.Objects;

/**
 * Problem:
 * Calculate sum all node in binary tree.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/sum-all-node-in-binary-tree/
 */
public class BinaryTreeSumCalculator {

    public <E> int sum(BinaryTreeNode<E> node) {
        if (Objects.isNull(node)) return 0;
        return (Integer) node.getData() +
                sum(node.getLeft()) +
                sum(node.getRight());
    }

    public <E> int sumRecursive(BinaryTreeNode<E> root) {
        if (Objects.isNull(root)) return 0;

        Queue<BinaryTreeNode<E>> queue = new LinkedQueue<>();
        queue.enQueue(root);
        int sum = 0;

        while (!queue.isEmpty()) {
            BinaryTreeNode<E> node = queue.deQueue();
            sum += (Integer) node.getData();
            if (!Objects.isNull(node.getLeft())) queue.enQueue(node.getLeft());
            if (!Objects.isNull(node.getRight())) queue.enQueue(node.getRight());
        }
        return sum;
    }
}

class BinaryTreeSumCalculatorTest {
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
        BinaryTreeSumCalculator sumCalculator = new BinaryTreeSumCalculator();

        printer.print(root1);
        System.out.println("Sum of node value are: " + sumCalculator.sum(root1));
        System.out.println("Sum of node value are: " + sumCalculator.sumRecursive(root1));
    }
}
