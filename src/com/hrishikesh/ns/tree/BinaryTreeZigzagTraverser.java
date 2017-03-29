package com.hrishikesh.ns.tree;

import com.hrishikesh.ns.queue.LinkedQueue;
import com.hrishikesh.ns.queue.Queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Problem:
 * Binary Tree ZigZag Traversal
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/binary-tree-zigzag-traversal/
 */
public class BinaryTreeZigzagTraverser {

    public <E> void traverse(BinaryTreeNode<E> root) {

        Queue<BinaryTreeNode<E>> queue = new LinkedQueue<>();
        queue.enQueue(root);
        queue.enQueue(null);

        boolean isLeftToRightDirection = true;
        List<BinaryTreeNode<E>> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            BinaryTreeNode<E> node = queue.deQueue();

            if (!Objects.isNull(node)) {
                list.add(node);
                if (!Objects.isNull(node.getLeft())) queue.enQueue(node.getLeft());
                if (!Objects.isNull(node.getRight())) queue.enQueue(node.getRight());
            } else {
                /** Left to right direction  **/
                if (isLeftToRightDirection)
                    for (BinaryTreeNode<E> temp : list)
                        System.out.print(temp.getData() + " => ");

                /** Right to left direction **/
                else
                    for (int i = list.size() - 1; i >= 0; i--)
                        System.out.print(list.get(i).getData() + " => ");

                if (!queue.isEmpty()) {
                    isLeftToRightDirection = !isLeftToRightDirection;
                    queue.enQueue(null);
                    list.clear();
                }
            }
        }
    }
}

class BinaryTreeZigzagTraverserTest {
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
        BinaryTreePrinter printer = new BinaryTreePrinter();
        BinaryTreeZigzagTraverser zigzagTraverser = new BinaryTreeZigzagTraverser();

        printer.print(root);
        zigzagTraverser.traverse(root);
    }
}
