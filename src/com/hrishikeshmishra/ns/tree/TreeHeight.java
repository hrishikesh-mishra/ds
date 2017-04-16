package com.hrishikeshmishra.ns.tree;

import com.hrishikeshmishra.ns.queue.LinkedQueue;
import com.hrishikeshmishra.ns.queue.Queue;

import java.util.Objects;

/**
 * Problem:
 * Find height of tree
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/find-height-of-tree/
 */
public class TreeHeight {
    public <E> int calculate(BinaryTreeNode<E> node) {
        if (Objects.isNull(node))
            return 0;

        return (1 + Math.max(
                calculate(node.getLeft()),
                calculate(node.getRight())
        ));
    }

    public <E> int calculateNonRecursive(BinaryTreeNode<E> node) {
        if (Objects.isNull(node))
            return 0;

        int height = 1;
        Queue<BinaryTreeNode<E>> queue = new LinkedQueue<>();
        queue.enQueue(node);
        queue.enQueue(null);

        while (!queue.isEmpty()) {
            BinaryTreeNode<E> tempNode = queue.deQueue();
            if (!Objects.isNull(tempNode)) {
                if (!Objects.isNull(tempNode.getLeft()))
                    queue.enQueue(tempNode.getLeft());

                if (!Objects.isNull(tempNode.getRight()))
                    queue.enQueue(tempNode.getRight());

            } else if (!queue.isEmpty()) {
                /** A way to mark one level is end here. **/
                height++;
                queue.enQueue(null);
            }
        }
        return height;
    }

}

class TreeHeightTest {
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

        TreeHeight treeHeight = new TreeHeight();
        System.out.println("Height of tree is: " + treeHeight.calculate(root));
        System.out.println("Height of tree is (non recursive): " + treeHeight.calculateNonRecursive(root));
    }
}
