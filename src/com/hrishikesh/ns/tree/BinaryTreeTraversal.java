package com.hrishikesh.ns.tree;


import com.hrishikesh.ns.stack.LinkedStack;
import com.hrishikesh.ns.stack.Stack;
import com.hrishikesh.ns.queue.LinkedQueue;
import com.hrishikesh.ns.queue.Queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Problem:
 * Binary Tree Traversal
 * 1. PreOrder (recursive & non-recursive)
 * 2. PostOrder (recursive & non-recursive)
 * 3. InOrder (recursive & non-recursive)
 * 4. Level Order
 * 5. Level Order in reverse
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/binary-tree-traversal/
 */
public class BinaryTreeTraversal<E> {

    public void traversePreOrder(BinaryTreeNode<E> node) {
        if (!Objects.isNull(node)) {
            System.out.print(node.getData() + ", ");
            traversePreOrder(node.getLeft());
            traversePreOrder(node.getRight());
        }
    }

    public void traversePreOrderNonRecursive(BinaryTreeNode<E> root) {
        if (Objects.isNull(root))
            return;

        Stack<BinaryTreeNode<E>> stack = new LinkedStack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            BinaryTreeNode<E> node = stack.pop();
            System.out.print(node.getData() + ", ");

            if (!Objects.isNull(node.getRight()))
                stack.push(node.getRight());

            if (!Objects.isNull(node.getLeft()))
                stack.push(node.getLeft());
        }
    }


    public void traverseInOrder(BinaryTreeNode<E> node) {
        if (!Objects.isNull(node)) {
            traverseInOrder(node.getLeft());
            System.out.print(node.getData() + ", ");
            traverseInOrder(node.getRight());
        }
    }

    public void traverseInOrderNonRecursive(BinaryTreeNode<E> root) {

        if (Objects.isNull(root))
            return;

        BinaryTreeNode<E> node;
        Stack<BinaryTreeNode<E>> stack = new LinkedStack<>();
        List<BinaryTreeNode<E>> visitedNode = new ArrayList<>();

        stack.push(root);

        while (!stack.isEmpty()) {

            if (!Objects.isNull(stack.top().getLeft())
                    && !visitedNode.contains(stack.top().getLeft())) {
                stack.push(stack.top().getLeft());
            } else {
                node = stack.pop();
                visitedNode.add(node);
                System.out.print(node.getData() + ", ");
                if (!Objects.isNull(node.getRight())
                        && !visitedNode.contains(node.getRight()))
                    stack.push(node.getRight());
            }
        }
    }

    public void traversePostOrder(BinaryTreeNode<E> node) {
        if (!Objects.isNull(node)) {
            traversePostOrder(node.getLeft());
            traversePostOrder(node.getRight());
            System.out.print(node.getData() + ", ");
        }
    }

    public void traversePostOrderNonRecursive(BinaryTreeNode<E> root) {
        if (Objects.isNull(root))
            return;

        Stack<BinaryTreeNode<E>> stack = new LinkedStack<>();
        List<BinaryTreeNode<E>> visitedNode = new ArrayList<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            if (!Objects.isNull(stack.top().getLeft())
                    && !visitedNode.contains(stack.top().getLeft())) {
                stack.push(stack.top().getLeft());
            } else if (!Objects.isNull(stack.top().getRight())
                    && !visitedNode.contains(stack.top().getRight())) {
                stack.push(stack.top().getRight());
            } else {
                visitedNode.add(stack.top());
                System.out.print(stack.pop().getData() + ", ");
            }
        }
    }

    public void traverseLevelOrder(BinaryTreeNode<E> root) {
        if (Objects.isNull(root))
            return;

        Queue<BinaryTreeNode<E>> queue = new LinkedQueue<>();
        queue.enQueue(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode<E> node = queue.deQueue();
            System.out.print(node.getData() + ", ");

            if (!Objects.isNull(node.getLeft()))
                queue.enQueue(node.getLeft());

            if (!Objects.isNull(node.getRight()))
                queue.enQueue(node.getRight());
        }
    }


}

class BinaryTreeTraversalTest {
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

        BinaryTreeTraversal treeTraversal = new BinaryTreeTraversal();
        System.out.print("\nRecursive PreOrder traverse: ");
        treeTraversal.traversePreOrder(root);
        System.out.print("\nNon-Recursive PreOrder traverse: ");
        treeTraversal.traversePreOrderNonRecursive(root);


        System.out.print("\n\nRecursive InOrder traverse: ");
        treeTraversal.traverseInOrder(root);
        System.out.print("\nNon-Recursive InOrder traverse: ");
        treeTraversal.traverseInOrderNonRecursive(root);

        System.out.print("\n\nRecursive PostOrder traverse: ");
        treeTraversal.traversePostOrder(root);
        System.out.print("\nNon-Recursive PostOrder traverse: ");
        treeTraversal.traversePostOrderNonRecursive(root);

        System.out.print("\n\nLevel order traversal: ");
        treeTraversal.traverseLevelOrder(root);
        System.out.print("\nLevel order traversal in reverse: ");

    }

}