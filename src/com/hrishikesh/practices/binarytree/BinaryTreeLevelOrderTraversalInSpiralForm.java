package com.hrishikesh.practices.binarytree;

import java.util.Objects;
import java.util.Stack;

/**
 * Problem:
 * Level order traversal in spiral form
 * Write a function to print spiral order traversal of a tree.
 * ;
 * Solution:
 * - Take 2 stacks
 * - And consume from one stack and push children to another stack
 * - Most important part is, during pushing to stack push in alternate fashion,
 * mean (if you are push left and then right child to stack1 and
 * in stack2 push first right child and then left child)
 * ;
 * ;
 * Algorithm:
 * - Create two stack S1 and S2
 * - Push tree root to S1
 * - Iterate till S1 and S1 not empty
 * - - Iterate till S1 is not empty
 * - - - Node = S1.pop
 * - - - Print Node
 * - - - If node has right child then
 * - - - - push right child to S2
 * - - - If node has left child then
 * - - - - push left child to S2
 * - - Iterate till S2 is not empty
 * - - - Node = S2.pop
 * - - - Print Node
 * - - - If node has left child then
 * - - - - push left child to S1
 * - - - If node has right child then
 * - - - - push right child to S2
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/level-order-traversal-in-spiral-form/
 */
public class BinaryTreeLevelOrderTraversalInSpiralForm {

    public static void traverse(BinaryTreeNode<Integer> node) {

        /** Base case **/
        if (Objects.isNull(node)) {
            return;
        }

        Stack<BinaryTreeNode<Integer>> stack1 = new Stack<>();
        Stack<BinaryTreeNode<Integer>> stack2 = new Stack<>();

        stack1.push(node);

        BinaryTreeNode<Integer> temp;

        while (!stack1.isEmpty() || !stack2.isEmpty()) {

            while (!stack1.isEmpty()) {
                temp = stack1.pop();
                System.out.print(temp.getData() + " ");

                /** Left and right child to stack2 if they are not null **/
                pushIfNotNull(temp.getRight(), stack2);
                pushIfNotNull(temp.getLeft(), stack2);
            }

            while (!stack2.isEmpty()) {
                temp = stack2.pop();
                System.out.print(temp.getData() + " ");

                /** Left and right child to stack2 if they are not null **/
                pushIfNotNull(temp.getLeft(), stack1);
                pushIfNotNull(temp.getRight(), stack1);
            }
        }
    }

    private static void pushIfNotNull(BinaryTreeNode<Integer> node,
                                      Stack<BinaryTreeNode<Integer>> stack) {
        if (!Objects.isNull(node)) {
            stack.push(node);
        }
    }

}

class BinaryTreeLevelOrderTraversalInSpiralFormTest {

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(50,
                new BinaryTreeNode<>(30,
                        new BinaryTreeNode<>(20),
                        new BinaryTreeNode<>(40)),
                new BinaryTreeNode<>(70,
                        new BinaryTreeNode<>(60),
                        new BinaryTreeNode<>(80)));

        BTPrinter.print(root);

        System.out.print("\nPrinting in spiral form : ");
        BinaryTreeLevelOrderTraversalInSpiralForm.traverse(root);
    }
}