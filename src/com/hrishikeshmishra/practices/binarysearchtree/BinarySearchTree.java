package com.hrishikeshmishra.practices.binarysearchtree;

import com.hrishikeshmishra.practices.binarytree.BTPrinter;
import com.hrishikeshmishra.practices.binarytree.BinaryTreeNode;

import java.util.Objects;

/**
 * Problem:
 * Implement Binary Search Tree search, insert delete.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/implement-binary-search-tree-search-insert-delete/
 */
public class BinarySearchTree {

    public static BinaryTreeNode<Integer> search(BinaryTreeNode<Integer> node, int key) {

        /** Base case when node is null **/
        if (Objects.isNull(node)) {
            return null;
        }

        if (node.getData() == key) {
            /** If node is equal to key return node **/
            return node;

        } else if (node.getData() > key) {

            /** If key is greater than node go left **/
            return search(node.getLeft(), key);
        } else {

            /** If key is less than node go right **/
            return search(node.getRight(), key);
        }
    }

    public static BinaryTreeNode<Integer> insert(BinaryTreeNode<Integer> node, int key) {

        if (Objects.isNull(node)) {
            return new BinaryTreeNode<>(key);
        }

        if (node.getData() == key) {
            /** If node is equal to key return node **/
            return node;

        } else if (node.getData() > key) {

            /** If key is greater than node go left **/
            node.setLeft(insert(node.getLeft(), key));
        } else {

            /** If key is less than node go right **/
            node.setRight(insert(node.getRight(), key));
        }

        return node;
    }

    public static BinaryTreeNode<Integer> delete(BinaryTreeNode<Integer> node, int key) {

        /** Base case when node is null.**/
        if (Objects.isNull(node)) {
            return null;
        }

        /** When key is greater than node data then, go right sub-tree **/
        if (node.getData() < key) {
            node.setRight(delete(node.getRight(), key));
        } else if (node.getData() > key) {
            /** When key is less than node data then, go left sub-tree **/
            node.setLeft(delete(node.getLeft(), key));
        } else {
            /** When node data == key, means node found  **/

            if (isLeaf(node)) {
                /** When delete node is leaf node then, return null **/
                return null;
            } else if (hasOnlyOneChild(node)) {
                /** When delete node has only one child then, return not null sub-tree **/
                return Objects.isNull(node.getLeft()) ? node.getRight() : node.getLeft();
            } else {
                /** When delete node has two children, then find in order successor **/
                BinaryTreeNode<Integer> inOrderSuccessorNode = getInOrderSuccessor(node);

                /** Replace node data with InOrder successor node data **/
                node.setData(inOrderSuccessorNode.getData());

                /** Recursively search and delete InOrder successor node from right sub-tree **/
                node.setRight(delete(node.getRight(), inOrderSuccessorNode.getData()));
            }
        }
        return node;
    }

    private static boolean isLeaf(BinaryTreeNode<Integer> node) {
        return Objects.isNull(node.getLeft()) && Objects.isNull(node.getRight());
    }

    private static boolean hasOnlyOneChild(BinaryTreeNode<Integer> node) {
        return Objects.isNull(node.getLeft()) ^ Objects.isNull(node.getRight());
    }

    private static BinaryTreeNode<Integer> getInOrderSuccessor(BinaryTreeNode<Integer> node) {
        node = node.getRight();
        while (!Objects.isNull(node.getLeft())) {
            node = node.getLeft();
        }
        return node;
    }

}


class BinarySearchTreeTest {

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = BinarySearchTree.insert(null, 25);
        BTPrinter.print(root);
        root = BinarySearchTree.insert(root, 20);
        root = BinarySearchTree.insert(root, 30);
        root = BinarySearchTree.insert(root, 23);
        BTPrinter.print(root);

        System.out.println("Search 23 : " + BinarySearchTree.search(root, 23));
        System.out.println("Search 100 : " + BinarySearchTree.search(root, 100));


        BinaryTreeNode<Integer> root1 = BinarySearchTree.insert(null, 20);
        root1 = BinarySearchTree.insert(root1, 30);
        root1 = BinarySearchTree.insert(root1, 3);
        root1 = BinarySearchTree.insert(root1, 10);
        root1 = BinarySearchTree.insert(root1, 2);
        root1 = BinarySearchTree.insert(root1, 30);
        root1 = BinarySearchTree.insert(root1, 27);
        root1 = BinarySearchTree.insert(root1, 36);
        root1 = BinarySearchTree.insert(root1, 26);
        root1 = BinarySearchTree.insert(root1, 28);

        BTPrinter.print(root1);

        root1 = BinarySearchTree.delete(root1, 20);
        System.out.println("After delete 20: ");
        BTPrinter.print(root1);

        root1 = BinarySearchTree.delete(root1, 27);
        System.out.println("After delete 27: ");
        BTPrinter.print(root1);

    }
}