package com.hrishikeshmishra.ns.tree;

import java.util.Objects;

/**
 * Problem:
 * AVL Tree
 *
 * @todo AVL deletion
 * @author hrishikesh.mishra
 * @link   http://hrishikeshmishra.com/avl-tree/
 */
public class AVLTree<E extends Comparable> {

    private AVLTreeNode<E> root;

    public AVLTree() {
    }

    public AVLTree(AVLTreeNode<E> root) {
        this.root = root;
    }

    public AVLTreeNode<E> getRoot() {
        return root;
    }

    public AVLTreeNode<E> insert(E e) {
        if (Objects.isNull(this.root))
            return this.root = insert(null, e);
        else
            return this.root = insert(this.root, e);
    }

    private AVLTreeNode<E> insert(AVLTreeNode<E> node, E data) {

        if (Objects.isNull(node))
            return new AVLTreeNode<>(data);

        /** Find in left subtree **/
        if (node.getData().compareTo(data) > 0) {

            node.setLeft(insert(node.getLeft(), data));

            /** Tree is not balanced **/
            if (!isBalanced(node)) {

                /**
                 * if inserted node value is lesser then current node value than, inserted node is in left , left
                 * so rotation is LEFT -> LEFT and we need single right rotation
                 */
                if (node.getLeft().getData().compareTo(data) > 0) {
                    node = doSingleRightRotation(node);
                    /**
                     * if inserted node value is greater then current node left child value than, inserted node is in left, right
                     * so rotation is LEFT -> RIGHT and we need double left right rotation
                     *
                     * Note: there will be no equal case
                     */
                } else {
                    node = doDoubleLeftRightRotation(node);
                }
            }
        }
        /** Find in right subtree **/
        else if (node.getData().compareTo(data) < 0) {
            node.setRight(insert(node.getRight(), data));

            /** Tree is not balanced **/
            if (!isBalanced(node)) {

                /**
                 * if inserted node value is greater then current node right child value than, inserted node is in right , right
                 * so rotation is RIGHT -> RIGHT and we need single left rotation
                 */
                if (node.getRight().getData().compareTo(data) < 0) {
                    node = doSingleLeftRotation(node);

                    /**
                     * if inserted node value is lesser then current node right child value than, inserted node is in right , left
                     * so rotation is RIGHT -> LEFT and we need double right left rotation
                     *
                     * Note: there will be no equal, when these values will equal.
                     */
                } else {
                    node = doDoubleRightLeftRotation(node);
                }
            }
        }

        /** update height of node  **/
        node.setHeight(Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1);
        return node;
    }

    private AVLTreeNode<E> doSingleRightRotation(AVLTreeNode<E> x) {
        AVLTreeNode<E> w = x.getLeft();
        x.setLeft(w.getRight());
        w.setRight(x);
        x.setHeight(Math.max(getHeight(x.getLeft()), getHeight(x.getRight())) + 1);
        w.setHeight(Math.max(getHeight(w.getLeft()), getHeight(w.getRight())) + 1);
        return w;
    }

    private AVLTreeNode<E> doSingleLeftRotation(AVLTreeNode<E> x) {
        AVLTreeNode<E> w = x.getRight();
        x.setRight(w.getLeft());
        w.setLeft(x);
        x.setHeight(Math.max(getHeight(x.getLeft()), getHeight(x.getRight())) + 1);
        w.setHeight(Math.max(getHeight(w.getLeft()), getHeight(w.getRight())) + 1);
        return w;
    }

    private AVLTreeNode<E> doDoubleLeftRightRotation(AVLTreeNode<E> z) {
        z.setLeft(doSingleLeftRotation(z.getLeft()));
        return doSingleRightRotation(z.getLeft());
    }

    private AVLTreeNode<E> doDoubleRightLeftRotation(AVLTreeNode<E> z) {
        z.setRight(doSingleRightRotation(z.getRight()));
        return doSingleLeftRotation(z.getRight());
    }

    private int getHeight(AVLTreeNode<E> node) {
        if (Objects.isNull(node)) return -1;
        else return node.getHeight();
    }

    private boolean isBalanced(AVLTreeNode<E> node) {
        if (Objects.isNull(node)) return true;
        // System.out.println( node + " Left h : " + getHeight(node.getLeft()) + ", Right h:" + getHeight(node.getRight()) + " is b " +  ( Math.abs(getHeight(node.getLeft()) - getHeight(node.getRight())) > 1));

        return Math.abs(getHeight(node.getLeft()) - getHeight(node.getRight())) <= 1;
    }


    private AVLTreeNode<E> findMax(AVLTreeNode<E> node) {
        while (!Objects.isNull(node.getRight())) node = node.getRight();
        return node;
    }

}

class AVLTreeTest {

    public static void main(String[] args) {
        AVLTree<Integer> avlTree = new AVLTree<>();
        BinaryTreePrinter printer = new BinaryTreePrinter();
        System.out.println("Insert 19");
        avlTree.insert(19);
        printer.print(avlTree.getRoot());

        avlTree.insert(23);
        avlTree.insert(34);
        avlTree.insert(12);
        printer.print(avlTree.getRoot());

        avlTree.insert(6);
        printer.print(avlTree.getRoot());

        avlTree.insert(60);
        avlTree.insert(31);
        avlTree.insert(23);
        avlTree.insert(9);
        printer.print(avlTree.getRoot());

        avlTree.insert(9);
        avlTree.insert(14);
        printer.print(avlTree.getRoot());


    }
}