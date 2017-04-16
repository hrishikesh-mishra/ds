package com.hrishikeshmishra.ns.tree;

import com.hrishikeshmishra.ns.queue.LinkedQueue;
import com.hrishikeshmishra.ns.queue.Queue;

import java.util.Objects;

/**
 * Problem:
 * Find size of binary tree
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/find-size-of-binary-tree/
 */
public class BinaryTreeSize {

    public <E> int getSize(BinaryTreeNode<E> node) {
        if (Objects.isNull(node)) return 0;
        return 1 + (getSize(node.getLeft()) + getSize(node.getRight()));
    }

    public <E> int getSizeNonRecursive(BinaryTreeNode<E> root) {
        Queue<BinaryTreeNode<E>> queue = new LinkedQueue<>();

        queue.enQueue(root);
        int counter = 0;

        while (!queue.isEmpty()) {
            counter++;

            BinaryTreeNode<E> node = queue.deQueue();

            if (!Objects.isNull(node.getLeft()))
                queue.enQueue(node.getLeft());

            if (!Objects.isNull(node.getRight()))
                queue.enQueue(node.getRight());

        }
        return counter;
    }

}

class BinaryTreeSizeTest {
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

        BinaryTreeSize sizeFinder = new BinaryTreeSize();
        System.out.println("Size is : " + sizeFinder.getSize(root));
        System.out.println("Size is (non-recursive): " + sizeFinder.getSizeNonRecursive(root));

    }
}

