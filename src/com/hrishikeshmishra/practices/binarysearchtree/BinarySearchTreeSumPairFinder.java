package com.hrishikeshmishra.practices.binarysearchtree;

import com.hrishikeshmishra.practices.binarytree.BTPrinter;
import com.hrishikeshmishra.practices.binarytree.BinaryTreeNode;

import java.util.Objects;
import java.util.Stack;

/**
 * Problem:
 * Find a pair with given sum in a Balanced BST
 * Given a Balanced Binary Search Tree and a target sum,
 * Expected time complexity is O(n) and
 * only O(Logn) extra space can be used. Any modification to Binary Search
 * Tree is not allowed. Note that height of a Balanced BST is always O(Logn).
 * ;
 * Solution:
 * - In Question its mentioned that we can't alter BST otherwise we can easily sove this by {@link BalancedBSTZeroSumFinder}
 * - But here we use two traversal one InOrder traversal and another reverse InOrder traversal
 * - Both traversals are non-recursive by using stack
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/find-a-pair-with-given-sum-in-a-balanced-bst/
 */
public class BinarySearchTreeSumPairFinder {

    /**
     * Container Class to hold stack and next node to push in stack
     */
    public static class Container {
        private Stack<BinaryTreeNode<Integer>> stack;
        private BinaryTreeNode<Integer> nextToPushNode;

        public Container(BinaryTreeNode<Integer> node) {
            this.nextToPushNode = node;
            this.stack = new Stack<>();


        }
    }

    public static boolean isPairExist(BinaryTreeNode<Integer> root, int sum) {

        /** Container to hold non-recursive InOrder traverse stack data **/
        Container forwardContainer = new Container(root);

        /** Container to hold non-recursive reverse InOrder stack data **/
        Container reverseContainer = new Container(root);

        BinaryTreeNode<Integer> forwardNode = traverseInOrder(forwardContainer);
        BinaryTreeNode<Integer> reverseNode = traverseReverseInOrder(reverseContainer);

        /** Repeat till one of node return null value **/
        while (!Objects.isNull(forwardNode) && !Objects.isNull(reverseNode)) {

            /** Calculate local sum **/
            int localSum = forwardNode.getData() + reverseNode.getData();

            /** if sums are equal return true **/
            if (localSum == sum) {
                return true;
            } else if (localSum < sum) {
                /** If local sum less then move one more step forward in InOrder traverse **/
                forwardNode = traverseInOrder(forwardContainer);
            } else {
                /** If local sum greater then move one step back in reverse InOrder traverse **/
                reverseNode = traverseReverseInOrder(reverseContainer);
            }
        }
        return false;
    }


    /**
     * Non-Recursive InOrder traversal (by using stack)
     *
     * @param container
     * @return
     */
    public static BinaryTreeNode<Integer> traverseInOrder(Container container) {

        Stack<BinaryTreeNode<Integer>> stack = container.stack;
        BinaryTreeNode<Integer> node = container.nextToPushNode;

        while (!Objects.isNull(node) || !stack.isEmpty()) {

            if (Objects.isNull(node)) {
                node = stack.pop();
                container.nextToPushNode = node.getRight();
                break;
            } else {
                stack.push(node);
                node = node.getLeft();
            }
        }
        return node;
    }

    /**
     * Non-Recursive reverse InOrder traversal (by using stack)
     *
     * @param container
     * @return
     */
    public static BinaryTreeNode<Integer> traverseReverseInOrder(Container container) {

        Stack<BinaryTreeNode<Integer>> stack = container.stack;
        BinaryTreeNode<Integer> node = container.nextToPushNode;

        while (!Objects.isNull(node) || !stack.isEmpty()) {

            if (Objects.isNull(node)) {
                node = stack.pop();
                container.nextToPushNode = node.getLeft();
                break;
            } else {
                stack.push(node);
                node = node.getRight();
            }
        }
        return node;
    }


}


class BinarySearchTreeSumPairFinderTest {

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(15,
                new BinaryTreeNode<>(10,
                        new BinaryTreeNode<>(8),
                        new BinaryTreeNode<>(12)),
                new BinaryTreeNode<>(20,
                        new BinaryTreeNode<>(16),
                        new BinaryTreeNode<>(25)));

        BTPrinter.print(root);

        System.out.println("Pair exists for 30 ? " + BinarySearchTreeSumPairFinder.isPairExist(root, 30));
        System.out.println("Pair exists for 32 ? " + BinarySearchTreeSumPairFinder.isPairExist(root, 32));
        System.out.println("Pair exists for 23 ? " + BinarySearchTreeSumPairFinder.isPairExist(root, 23));
        System.out.println("Pair exists for 41 ? " + BinarySearchTreeSumPairFinder.isPairExist(root, 41));
        System.out.println("Pair exists for 100 ? " + BinarySearchTreeSumPairFinder.isPairExist(root, 100));
    }
}