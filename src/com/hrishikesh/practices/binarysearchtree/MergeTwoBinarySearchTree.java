package com.hrishikesh.practices.binarysearchtree;

import com.hrishikesh.practices.binarytree.BTPrinter;
import com.hrishikesh.practices.binarytree.BinaryTreeNode;

import java.util.Objects;
import java.util.Stack;

/**
 * Problem:
 * Merge two Binary Search Tree  with limited extra space
 * Given two Binary Search Trees(BST), print the elements of both BSTs in sorted form.
 * The expected time complexity is O(m+n) where m is the number of nodes in first tree and
 * n is the number of nodes in second tree. Maximum allowed auxiliary space is O(height of
 * the first tree + height of the second tree).
 * ;
 * Solution
 * - Process tree1 in recursive InOrder
 * - Process tree2 in non recursive InOrder
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/merge-two-binary-search-tree-with-limited-extra-space/
 */
public class MergeTwoBinarySearchTree {


    /**
     * Container class to hold following data
     * - tree 1 current processing node
     * - tree 2 current processing node
     * - tree 2 stack use traverse InOrder recursive
     */
    public static class Container {
        private BinaryTreeNode<Integer> tree1CurrentNode;
        private BinaryTreeNode<Integer> tree2CurrentNode;
        private Stack<BinaryTreeNode<Integer>> tree2stack;

        public Container(BinaryTreeNode<Integer> tree1CurrentNode, BinaryTreeNode<Integer> tree2CurrentNode) {
            this.tree1CurrentNode = tree1CurrentNode;
            this.tree2CurrentNode = tree2CurrentNode;
            this.tree2stack = new Stack<>();
            this.tree2stack.push(tree2CurrentNode);
        }
    }

    public static void mergePrint(BinaryTreeNode<Integer> root1, BinaryTreeNode<Integer> root2) {

        Container container = new Container(root1, root2);


        traverseRecursiveInOrderTree1(root1, container);

        container.tree1CurrentNode = null;
        /** Print remaining part of tree 2, if it exists **/
        traverseNonRecursiveInOrderTree2(container);


    }

    public static void traverseRecursiveInOrderTree1(BinaryTreeNode<Integer> node1, Container container) {

        /** Base case when node is null **/
        if (Objects.isNull(node1)) {
            return;
        }

        /** Recursively traverse tree 1 left sub-tree **/
        traverseRecursiveInOrderTree1(node1.getLeft(), container);

        /** Process node **/

        if (isPrintableTree1Node(node1, container.tree2CurrentNode)) {
            System.out.print(node1.getData() + " ");
        } else {
            container.tree1CurrentNode = node1;

            /** Invoke tree2 print in non-recursive **/
            traverseNonRecursiveInOrderTree2(container);

            System.out.print(node1.getData() + " ");
        }

        /** Recursively traverse tree 2 right sub-tree **/
        traverseRecursiveInOrderTree1(node1.getRight(), container);

    }


    /**
     * Tree 1 is only printable when current node of tree is null or
     * node value of current node of tree 1 less than current node of tree 2
     *
     * @param tree1CurrentNode
     * @param tree2CurrentNode
     * @return
     */
    private static boolean isPrintableTree1Node(BinaryTreeNode<Integer> tree1CurrentNode,
                                                BinaryTreeNode<Integer> tree2CurrentNode) {

        if (!Objects.isNull(tree2CurrentNode)) {
            return tree1CurrentNode.getData() < tree2CurrentNode.getData();
        } else {
            return true;
        }

    }


    private static void traverseNonRecursiveInOrderTree2(Container container) {

        Stack<BinaryTreeNode<Integer>> stack = container.tree2stack;

        /** Set current node for stack **/
        BinaryTreeNode<Integer> node = (!stack.isEmpty()) ? stack.peek() : null;
        boolean isFirstPush = true;

        /** InOrder non recursive traversal **/
        while (!Objects.isNull(node) || !stack.isEmpty()) {

            /** Node is null, mean all left child of node is processed, now its time to process node and right sub-tree **/
            if (Objects.isNull(node)) {

                /** Check its printable of tree 2 node or not **/
                if (!isPrintableTree2Node(container.tree1CurrentNode, stack.peek())) {
                    break;
                } else {
                    node = stack.pop();
                    System.out.print(node.getData() + " ");
                }

                node = node.getRight();

            } else {
                if (!isFirstPush) {
                    stack.push(node);
                }

                isFirstPush = false;
                node = node.getLeft();
            }
        }

        /** Update current node of tree 2 **/
        container.tree2CurrentNode = (!stack.isEmpty()) ? stack.peek() : null;

    }

    /**
     * Tree 2 node will only printable if tree 1 current is null or
     * tree 2 current node value is less than or equal to current node value of tree1
     * @param tree1CurrentNode
     * @param tree2CurrentNode
     * @return
     */
    private static boolean isPrintableTree2Node(BinaryTreeNode<Integer> tree1CurrentNode,
                                                BinaryTreeNode<Integer> tree2CurrentNode) {

        if (!Objects.isNull(tree1CurrentNode)) {
            return tree1CurrentNode.getData() >= tree2CurrentNode.getData();
        } else {
            return true;
        }

    }


}


class MergeTwoBinarySearchTreeTest {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root1 = new BinaryTreeNode<>(20,
                new BinaryTreeNode<>(3,
                        new BinaryTreeNode<>(2),
                        new BinaryTreeNode<>(10)),
                new BinaryTreeNode<>(30,
                        new BinaryTreeNode<>(27,
                                new BinaryTreeNode<>(26),
                                new BinaryTreeNode<>(28)),
                        new BinaryTreeNode<>(35)));


        BinaryTreeNode<Integer> root2 = new BinaryTreeNode<>(10,
                new BinaryTreeNode<>(7,
                        new BinaryTreeNode<>(5),
                        new BinaryTreeNode<>(8)),
                new BinaryTreeNode<>(30,
                        new BinaryTreeNode<>(25,
                                new BinaryTreeNode<>(24),
                                new BinaryTreeNode<>(29)),
                        new BinaryTreeNode<>(50)));


        BTPrinter.print(root1);
        BTPrinter.print(root2);

        MergeTwoBinarySearchTree.mergePrint(root1, root2);


    }
}

