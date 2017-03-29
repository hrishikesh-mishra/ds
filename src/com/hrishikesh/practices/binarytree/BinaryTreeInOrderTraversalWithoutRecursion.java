package com.hrishikesh.practices.binarytree;

import java.util.Objects;
import java.util.Stack;

/**
 * Problem:
 * Inorder Tree Traversal without Recursion
 * ;
 * ;
 * Algorithm:
 * - Create a Stack
 * - stack.push (node)
 * - Move node to left i.e. node = node.left
 * - Iterate till node is not null or stack is not empty
 * - - if node is null then
 * - - - node = stack.pop
 * - - - Print node
 * - - - node = node.right
 * - - Else
 * - - - stack.push (node)
 * - - - node = ndoe.left
 *
 * @author hrishikesh.mishra
 * @link https://www.youtube.com/watch?v=nzmtCFNae9k
 * @link http://hrishikeshmishra.com/inorder-tree-traversal-without-recursion/
 */
public class BinaryTreeInOrderTraversalWithoutRecursion {

    public static void traverseInOrder(BinaryTreeNode<Integer> node) {

        /** Auxiliary to hold nodes **/
        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();

        /** Add first element to stack **/
        stack.push(node);

        /** Move pointer to ahead because we have added on node to stack **/
        node = node.getLeft();

        /** Repeat this process till node is null or stack is emtpy. **/
        while (!Objects.isNull(node) || !stack.isEmpty())

        /** If node is null, means we processed all left nodes  **/
            if (Objects.isNull(node)) {

                /** Pop last inserted element from stack **/
                node = stack.pop();

                /** Process node **/
                System.out.print(node.getData() + " ");

                /** Move pointer to right node to process **/
                node = node.getRight();
            } else {

                /** Push current to stack **/
                stack.push(node);

                /** Move to next left node node **/
                node = node.getLeft();
            }
    }

}

class BinaryTreeInOrderTraversalWithoutRecursionTest {

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1,
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

        /** Print binary tree **/
        BTPrinter.print(root);

        /** Traverse tree in InOrder **/
        BinaryTreeInOrderTraversalWithoutRecursion.traverseInOrder(root);
    }
}
