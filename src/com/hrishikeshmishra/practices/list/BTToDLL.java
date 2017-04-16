package com.hrishikeshmishra.practices.list;

import java.util.Objects;

/**
 * Binary Tree (BT) to Double Linked List (DLL)
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/binary-tree-bt-to-double-linked-list-dll/
 */
public class BTToDLL {

    public static class Node {
        private int data;
        private Node left, right;

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    private Node dllHead;
    private Node treeRoot;

    public void convert(Node treeRoot) {

        /** Base case **/
        if (Objects.isNull(treeRoot)) return;

        /** Go right end of tree **/
        convert(treeRoot.getRight());

        /** insert root to DLL **/
        treeRoot.setRight(dllHead);

        /** change left pointer to previous head **/
        if (!Objects.isNull(dllHead)) {
            dllHead.setLeft(treeRoot);
        }

        /** Change head to DLL **/
        dllHead = treeRoot;

        /** Convert child of tree **/
        convert(treeRoot.getLeft());
    }

    private void printList(Node head) {
        System.out.println("Extracted Double Linked List is : ");
        while (!Objects.isNull(head)) {
            System.out.print(head.getData() + " ");
            head = head.getRight();
        }
    }

    public static void main(String[] args)
    {
        /* Constructing below tree
               5
             /   \
            3     6
           / \     \
          1   4     8
         / \       / \
        0   2     7   9  */

        BTToDLL tree = new BTToDLL();
        tree.treeRoot = new Node(5);
        tree.treeRoot.left = new Node(3);
        tree.treeRoot.right = new Node(6);
        tree.treeRoot.left.right = new Node(4);
        tree.treeRoot.left.left = new Node(1);
        tree.treeRoot.right.right = new Node(8);
        tree.treeRoot.left.left.right = new Node(2);
        tree.treeRoot.left.left.left = new Node(0);
        tree.treeRoot.right.right.left = new Node(7);
        tree.treeRoot.right.right.right = new Node(9);

        tree.convert(tree.treeRoot);
        tree.printList(tree.dllHead);
    }
}
