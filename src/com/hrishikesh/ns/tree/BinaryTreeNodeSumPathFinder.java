package com.hrishikesh.ns.tree;

import java.util.Objects;

/**
 * Problem:
 * For given a sum check whether there exists a path from root to any of the nodes.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/binary-tree-sum-check-for-a-path/
 */
public class BinaryTreeNodeSumPathFinder {

    public <E> boolean isPathExistsWithSum(BinaryTreeNode<E> node, int sum) {
        if (Objects.isNull(node)) return false;
        if ((Integer) node.getData() == sum) return true;
        return isPathExistsWithSum(node.getLeft(), sum - (Integer) node.getData())
                || isPathExistsWithSum(node.getRight(), sum - (Integer) node.getData());
    }
}

class BinaryTreeNodeSumPathFinderTest {

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
        BinaryTreeNodeSumPathFinder sumPathFinder = new BinaryTreeNodeSumPathFinder();

        printer.print(root1);
        System.out.println("Diameter is : " + maxSumFinder.getMaxSumOfLevel(root1));
        System.out.println("Is path exists with sum (8): " + sumPathFinder.isPathExistsWithSum(root1, 8));
        System.out.println("Is path exists with sum (100): " + sumPathFinder.isPathExistsWithSum(root1, 100));
    }
}
