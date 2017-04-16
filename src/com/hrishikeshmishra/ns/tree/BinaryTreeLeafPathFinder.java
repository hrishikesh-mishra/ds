package com.hrishikeshmishra.ns.tree;

import com.hrishikeshmishra.ns.queue.LinkedQueue;
import com.hrishikeshmishra.ns.queue.Queue;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Problem:
 * For a binary tree, print out all its root-to-leaf paths.
 * ;
 * Solution
 * Do BSF store child-parent map, of leaf node traverse back to root
 * through this map.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/print-all-path-for-a-binary-tree/
 */
public class BinaryTreeLeafPathFinder {

    public <E> void printLeafToRootPath(BinaryTreeNode<E> root) {
        if (Objects.isNull(root)) return;

        Queue<BinaryTreeNode<E>> queue = new LinkedQueue<>();
        Map<BinaryTreeNode<E>, BinaryTreeNode<E>> childParentMap = new LinkedHashMap<>();
        queue.enQueue(root);
        childParentMap.put(root, null);

        while (!queue.isEmpty()) {
            BinaryTreeNode<E> node = queue.deQueue();

            /** current node is leaf node (because it's both children are null. **/
            if (Objects.isNull(node.getLeft()) && Objects.isNull(node.getRight())) {
                printPath(node, childParentMap);
                continue; /** No need of further computation **/
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

    private <E> void printPath(BinaryTreeNode<E> node, Map<BinaryTreeNode<E>, BinaryTreeNode<E>> childParentMap) {
        System.out.print("\nPath for leaf : " + node.getData() + " :: ");
        while (!Objects.isNull(node)) {
            System.out.print(node.getData() + " => ");
            node = childParentMap.get(node);
        }

    }
}


class BinaryTreeLeafPathFinderTest {

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
        BinaryTreeLeafPathFinder pathFinder = new BinaryTreeLeafPathFinder();

        printer.print(root1);
        pathFinder.printLeafToRootPath(root1);

    }
}