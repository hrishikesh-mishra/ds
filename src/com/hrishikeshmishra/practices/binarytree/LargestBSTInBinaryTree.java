package com.hrishikeshmishra.practices.binarytree;

import java.util.Objects;

/**
 * Problem:
 * Find the largest BST subtree in a given Binary Tree
 * Given a Binary Tree, write a function that returns the size of the largest subtree
 * which is also a Binary Search Tree (BST). If the complete Binary Tree is BST, then return
 * the size of whole tree.
 * Solution:
 * - Traverse tree in PostOrder
 * - Create Node info (isBST, size, min, max)
 * ;
 * Algorithm:
 * - Create a class to hold (isBST, size, min, max) properties for every node
 * - Traverse in postOrder
 * - - If node is null then
 * - - - return null
 * - - If node is leaf then,
 * - - - return isBST = true, size = 1, min = node.data, max = node.data
 * - - left = recursively call for node.left
 * - - right = recursively call for node.right
 * - - if left.isBST == true and right.isBST == true and left.min < node.data < right.max then
 * - - - return isBST = true, size = 1+ left.size + right.size, min = left.min, max = right.max
 * - - else
 * - - - return isBST = false, size = max (left.size , right.size), min = 0, max = 0
 *
 * @author hrishikesh.mishra
 * @link https://www.youtube.com/watch?v=4fiDs7CCxkc
 * @link http://hrishikeshmishra.com/find-the-largest-bst-subtree-in-a-given-binary-tree/
 */
public class LargestBSTInBinaryTree {

    public static class NodeInfo {
        private boolean isBST;
        private int size;
        private int min;
        private int max;

        public NodeInfo(boolean isBST, int size, int min, int max) {
            this.isBST = isBST;
            this.size = size;
            this.min = min;
            this.max = max;
        }

        public boolean isBST() {
            return isBST;
        }

        public void setBST(boolean BST) {
            isBST = BST;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }
    }

    public static NodeInfo findLargestBSTSize(BinaryTreeNode<Integer> node) {

        /** Base case : when node is null **/
        if (Objects.isNull(node)) {
            return null;
        }

        /**
         * Process in PostOrder form
         *
         * First findLargest BST in left child then,
         * Find largest BST in right child and then
         * calculate largest BST for current Node.
         *
         */
        NodeInfo leftSubTreeNodeInfo = findLargestBSTSize(node.getLeft()),
                rightSubTreeNodeInfo = findLargestBSTSize(node.getRight());

        return checkAndCalculateBSTOfNode(node, leftSubTreeNodeInfo, rightSubTreeNodeInfo);

    }

    private static NodeInfo checkAndCalculateBSTOfNode(BinaryTreeNode<Integer> node,
                                                       NodeInfo leftSubTreeNodeInfo, NodeInfo rightSubTreeNodeInfo) {

        /** When node is leaf node **/
        if (Objects.isNull(leftSubTreeNodeInfo) &&
                Objects.isNull(rightSubTreeNodeInfo)) {

            return new NodeInfo(true, 1, node.getData(), node.getData());

        } else if (Objects.isNull(leftSubTreeNodeInfo)) { /** When left child is null **/

            if (rightSubTreeNodeInfo.isBST() &&
                    node.getData() <= rightSubTreeNodeInfo.getMin()) { /** If right child is BST and current node also **/

                return new NodeInfo(true, 1 + rightSubTreeNodeInfo.getSize(),
                        node.getData(), rightSubTreeNodeInfo.getMax());

            } else {
                /** In case right child is not BST or adding current node tree is not BST **/
                return new NodeInfo(false, rightSubTreeNodeInfo.getSize(), 0, 0);
            }
        } else if (Objects.isNull(rightSubTreeNodeInfo)) { /** When right child is null **/

            if (leftSubTreeNodeInfo.isBST() &&
                    node.getData() >= leftSubTreeNodeInfo.getMax()) { /** Left child is BST and current node also **/

                return new NodeInfo(true, 1 + leftSubTreeNodeInfo.getSize(),
                        leftSubTreeNodeInfo.getMin(), node.getData());

            } else {
                /** In case left child is not BST or adding current node tree is not BST any more **/
                return new NodeInfo(false, leftSubTreeNodeInfo.getSize(), 0, 0);
            }
        } else { /** Both children are not null **/

            if (leftSubTreeNodeInfo.isBST() &&
                    rightSubTreeNodeInfo.isBST()) { /** If both children are BST **/

                if (node.getData() >= leftSubTreeNodeInfo.getMax() &&
                        node.getData() <= rightSubTreeNodeInfo.getMax()) { /** After adding current node tree is still BST **/

                    return new NodeInfo(true,
                            1 + leftSubTreeNodeInfo.getSize() + rightSubTreeNodeInfo.getSize(),
                            leftSubTreeNodeInfo.getMin(), rightSubTreeNodeInfo.getMax());

                } else { /** After including current its no more BST **/

                    return new NodeInfo(false,
                            Math.max(leftSubTreeNodeInfo.getSize(),
                                    rightSubTreeNodeInfo.getSize()), 0, 0);

                }
            } else { /** One or both children are not BST **/

                return new NodeInfo(false,
                        Math.max(leftSubTreeNodeInfo.getSize(),
                                rightSubTreeNodeInfo.getSize()), 0, 0);
            }

        }

    }

}

class LargestBSTInBinaryTreeTest {

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(25,
                new BinaryTreeNode<>(18,
                        new BinaryTreeNode<>(19,
                                null,
                                new BinaryTreeNode<>(15)),
                        new BinaryTreeNode<>(20,
                                new BinaryTreeNode<>(18),
                                new BinaryTreeNode<>(25))),
                new BinaryTreeNode<>(50,
                        new BinaryTreeNode<>(35,
                                new BinaryTreeNode<>(20,
                                        null,
                                        new BinaryTreeNode<>(25)),
                                new BinaryTreeNode<>(40)),
                        new BinaryTreeNode<>(60,
                                new BinaryTreeNode<>(55),
                                new BinaryTreeNode<>(70)))
        );

        BTPrinter.print(root);

        LargestBSTInBinaryTree.NodeInfo bstNodeInfo = LargestBSTInBinaryTree.findLargestBSTSize(root);

        System.out.println("Largest BST size: " + bstNodeInfo.getSize());

    }
}
