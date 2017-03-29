package com.hrishikesh.dsjava.search_tree.core.impl;

import java.util.Objects;

/**
 * Created by hrishikesh.mishra on 18/04/16.
 */
public class AVLTree {

    private AVLNode root;

    public void insert(int k){
        AVLNode node = new AVLNode(k);
        insertAVL(this.root, node);
    }

    private void insertAVL(AVLNode parent, AVLNode node) {

        if(Objects.isNull(parent)){
            parent = node;
        }else {
            if(node.key < parent.key){
                /** insertion in left subtree **/
                if(Objects.isNull(parent.getLeft())){
                    parent.setLeft(node);
                    node.setParent(parent);
                    reBalanceTree(parent);
                }else {
                    insertAVL(parent.getLeft(), node);
                }
            }else if (node.key > parent.key){
                /** insertion in right subtree **/
                if(Objects.isNull(parent.getRight())){
                    parent.setRight(node);
                    node.setParent(parent);
                    reBalanceTree(parent);
                }else {
                    insertAVL(parent.getRight(), node);
                }
            }else {
                /** Do nothing because key already exists in tree **/
            }
        }
    }

    private void reBalanceTree(AVLNode node) {

    }

    public static class AVLNode {
        private AVLNode left;
        private AVLNode right;
        private AVLNode parent;
        private int key;
        private boolean isBalanced;

        public AVLNode(int key) {
            this.key = key;
            this.left = this.right = this.parent = null;
            this.isBalanced = false;
        }

        public AVLNode getLeft() {
            return left;
        }

        public void setLeft(AVLNode left) {
            this.left = left;
        }

        public AVLNode getRight() {
            return right;
        }

        public void setRight(AVLNode right) {
            this.right = right;
        }

        public AVLNode getParent() {
            return parent;
        }

        public void setParent(AVLNode parent) {
            this.parent = parent;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public boolean isBalanced() {
            return isBalanced;
        }

        public void setBalanced(boolean isBalanced) {
            this.isBalanced = isBalanced;
        }

        @Override
        public String toString() {
            return String.valueOf(key);
        }
    }


}
