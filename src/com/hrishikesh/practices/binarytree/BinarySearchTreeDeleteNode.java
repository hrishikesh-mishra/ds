package com.hrishikesh.practices.binarytree;

import java.util.Objects;

/**
 * Problem:
 * Delete a node from BST
 * Given a Binary Search Tree (BST) and a node no 'x' , your task is to delete the node 'x' from the BST
 * ;
 * Solution:
 * - If its a leaf node set null.
 * - If it has only one child then set parent to grandchild.
 * - If its has two children then find inorder successor or predecessor of that node.
 * ;
 * ;
 * Algorithm:
 * - If node is null then,
 * - - return null
 * - If node.data < key then,
 * - - node.right = recursively call for right child
 * - Else If node.data > key then,
 * - - node.left = recursively call for left child
 * - Else
 * - - If node is left node then
 * - - - return null
 * - - Else If node has only one child then,
 * - - - return child
 * - - Else
 * - - - InOrderSuccessorNode = Get InOrder Successor node
 * - - - node.data = InOrderSuccessorNode.data
 * - - - node.right = recursively call for right child with InOrderSuccessorNode.data  as key
 * - Return node
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/delete-a-node-from-bst/
 */
public class BinarySearchTreeDeleteNode {

    public static BinaryTreeNode<Integer> delete(BinaryTreeNode<Integer> node, int key) {

        /** Base case when node is empty **/
        if (Objects.isNull(node)) {
            return null;
        }

        if (key < node.getData()) {
            /** If key is less than node data value then go left subtree **/
            node.setLeft(delete(node.getLeft(), key));
        } else if (key > node.getData()) {
            /** If key is greater than node data value then go right subtree **/
            node.setRight(delete(node.getRight(), key));
        } else {
            /** If key == node.getData() **/

            /** if node has at most one child **/
            if (Objects.isNull(node.getLeft())) {
                return node.getRight();
            } else if (Objects.isNull(node.getRight())) {
                return node.getLeft();
            }

            /** If node has both children **/

            /** Get next inorder successor **/
            BinaryTreeNode<Integer> nextInOrderSuccessor = getNextInOrderSuccessor(node.getRight());
            node.setData(nextInOrderSuccessor.getData());

            /** Recursively delete next replaced key **/
            node.setRight(delete(node.getRight(), nextInOrderSuccessor.getData()));

        }


        return node;
    }

    public static BinaryTreeNode<Integer> getNextInOrderSuccessor(BinaryTreeNode<Integer> node) {
        /** Go extreme left to get next inorder successor  **/
        while (!Objects.isNull(node.getLeft())) {
            node = node.getLeft();
        }
        return node;
    }
}


class BinarySearchTreeDeleteNodeTest {

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(50,
                new BinaryTreeNode<>(30,
                        new BinaryTreeNode<>(20),
                        new BinaryTreeNode<>(40)),
                new BinaryTreeNode<>(70,
                        new BinaryTreeNode<>(60),
                        new BinaryTreeNode<>(80)));

        BTPrinter.print(root);
        System.out.println("---------------------------------------------------------");
        root = BinarySearchTreeDeleteNode.delete(root, 50);
        System.out.println("After delete 50 : ");
        BTPrinter.print(root);
        System.out.println("---------------------------------------------------------");
        root = BinarySearchTreeDeleteNode.delete(root, 20);
        System.out.println("After delete 20 : ");
        BTPrinter.print(root);

    }
}