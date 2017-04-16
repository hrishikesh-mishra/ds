package com.hrishikeshmishra.ns.tree;

import com.hrishikeshmishra.ns.stack.LinkedStack;
import com.hrishikeshmishra.ns.stack.Stack;
import com.hrishikeshmishra.ns.queue.LinkedQueue;
import com.hrishikeshmishra.ns.queue.Queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Problem:
 * Given an algorithm for printing the level order data in reverse order.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/printing-the-level-order-data-in-reverse-order-in-graph/
 */
public class BinaryTreeReverseLevelOrder {

    public <E> void reverseOrder(BinaryTreeNode<E> root) {
        if (Objects.isNull(root)) return;

        List<BinaryTreeNode<E>> levelNodes = new ArrayList<>();
        Stack<List<BinaryTreeNode<E>>> stack = new LinkedStack<>();
        Queue<BinaryTreeNode<E>> queue = new LinkedQueue<>();

        queue.enQueue(root);
        queue.enQueue(null);

        while (!queue.isEmpty()) {

            BinaryTreeNode<E> node = queue.deQueue();
            if (!Objects.isNull(node)) {
                levelNodes.add(node);
                if (!Objects.isNull(node.getLeft())) queue.enQueue(node.getLeft());
                if (!Objects.isNull(node.getRight())) queue.enQueue(node.getRight());
            } else {  /** new level start from here **/
                if (!queue.isEmpty()) {
                    stack.push(levelNodes);
                    levelNodes = new ArrayList<>();
                    queue.enQueue(null);
                }
            }
        }

        stack.push(levelNodes);

        while (!stack.isEmpty()) {
            levelNodes = stack.pop();
            for (BinaryTreeNode<E> node : levelNodes)
                System.out.print(node.getData() + ", ");
            System.out.println("");
        }
    }

}

class BinaryTreeReverseLevelOrderTest {
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

        BinaryTreeReverseLevelOrder reverseLevelOrder = new BinaryTreeReverseLevelOrder();
        BinaryTreePrinter printer = new BinaryTreePrinter();

        System.out.println("\n\nTree : \n ");
        printer.print(root);

        System.out.println("\nReverse level order : \n");
        reverseLevelOrder.reverseOrder(root);
    }
}
