package com.hrishikeshmishra.ns.tree;

/**
 * <p>
 *     Binary tree node.
 * </p>
 * Created by hrishikesh.mishra
 */
public class BinaryTreeNode<E> {
    protected E data;
    protected BinaryTreeNode<E> left;
    protected BinaryTreeNode<E> right;

    public BinaryTreeNode(E data) {
        this(data, null, null);
    }

    public BinaryTreeNode(E data, BinaryTreeNode<E> left, BinaryTreeNode<E> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public BinaryTreeNode<E> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<E> left) {
        this.left = left;
    }

    public BinaryTreeNode<E> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<E> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node(" +  data + ')';
    }
}
