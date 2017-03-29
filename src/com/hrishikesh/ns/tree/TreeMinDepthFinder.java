package com.hrishikesh.ns.tree;

import com.hrishikesh.ns.queue.LinkedQueue;
import com.hrishikesh.ns.queue.Queue;

import java.util.Objects;

/**
 * Problem:
 * Find min depth of tree.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/find-min-depth-of-tree/
 */
public class TreeMinDepthFinder {

    public <E> int find(BinaryTreeNode<E> node) {
        if (Objects.isNull(node))
            return 0;

        int height = 1;
        Queue<BinaryTreeNode<E>> queue = new LinkedQueue<>();
        queue.enQueue(node);
        queue.enQueue(null);

        while (!queue.isEmpty()) {
            BinaryTreeNode<E> tempNode = queue.deQueue();
            if (!Objects.isNull(tempNode)) {

                /** Here first node who has no children **/
                if (Objects.isNull(tempNode.getLeft()) && Objects.isNull(tempNode.getRight()))
                    return height;

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

class TreeMinDepthFinderTest {

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

        TreeMinDepthFinder minDepthFinder = new TreeMinDepthFinder();
        System.out.println("Min depth of tree is: " + minDepthFinder.find(root));
    }
}
