package com.hrishikesh.ns.tree;

/**
 * <p>
 *     AVL Tree Node
 * </p>
 * Created by hrishikesh.mishra
 */
public class AVLTreeNode<E> extends BinaryTreeNode<E> {

    private int height;


    public AVLTreeNode(E data) {
        super(data);
        this.height = 0;
    }

    public AVLTreeNode(E data, int height) {
        super(data);
        this.height = height;
    }

    public AVLTreeNode(E data, BinaryTreeNode left, BinaryTreeNode right, int height) {
        super(data, left, right);
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public AVLTreeNode<E> getLeft() {
        return (AVLTreeNode<E>) super.getLeft();
    }


    public void setLeft(AVLTreeNode<E> left) {
        super.setLeft(left);
    }

    @Override
    public AVLTreeNode<E> getRight() {
        return (AVLTreeNode<E>) super.getRight();
    }

    public void setRight(AVLTreeNode<E> right) {
        super.setRight(right);
    }

    @Override
    public String toString() {
        return "(" +
                "data=" + data +
                ", height=" + height +
                ')';
    }
}
