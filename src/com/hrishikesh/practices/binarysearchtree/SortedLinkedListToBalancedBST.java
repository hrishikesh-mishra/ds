package com.hrishikesh.practices.binarysearchtree;

import com.hrishikesh.practices.binarytree.BTPrinter;
import com.hrishikesh.practices.binarytree.BinaryTreeNode;

import java.util.Objects;

import static com.hrishikesh.practices.list.LinkedList.Node;

/**
 * Problem:
 * Sorted Linked List to Balanced BST
 * Given a Singly Linked List which has data members sorted in ascending order.
 * Construct a Balanced Binary Search Tree which has same data members as the given Linked List.
 * ;
 * Solution:
 * - This algorithm first, creates leaves then internal nodes
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/sorted-linked-list-to-balanced-bst/
 */
public class SortedLinkedListToBalancedBST {

    /**
     * Container Class to hold linked list current head position
     * because JAVA doesn't support call by reference
     */
    public static class Container {
        private Node head;

        public Container(Node head) {
            this.head = head;
        }
    }


    public static BinaryTreeNode<Integer> createBST(Node head) {
        Container container = new Container(head);
        return createBST(container, getSize(head));
    }

    private static BinaryTreeNode<Integer> createBST(Container container, int n) {

        /** When n reached extreme left or right **/
        if (n <= 0) {
            return null;
        }

        /** First create left sub tree **/
        BinaryTreeNode<Integer> left = createBST(container, n / 2);

        /** Create root node **/
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(container.head.getData());

        /** Move head pointer **/
        container.head = container.head.getNext();

        /** Create right sub tree **/
        BinaryTreeNode<Integer> right = createBST(container, n - (n / 2) - 1);

        /** Update pointers **/
        root.setLeft(left);
        root.setRight(right);

        /** Return created root **/
        return root;
    }

    private static int getSize(Node node) {
        int size = 0;

        while (!Objects.isNull(node)) {
            size++;
            node = node.getNext();
        }

        return size;
    }

}


class SortedLinkedListToBalancedBSTTest {
    public static void main(String[] args) {
        Node head = new Node(1, new Node(2, new Node(3, new Node(4,
                new Node(5, new Node(6, new Node(7)))))));

        System.out.print("List: ");
        print(head);

        BinaryTreeNode<Integer> root = SortedLinkedListToBalancedBST.createBST(head);
        System.out.println("\nBinary Search Tree :");
        BTPrinter.print(root);
    }

    private static void print(Node node) {
        while (!Objects.isNull(node)) {
            System.out.print(node.getData() + " ");
            node = node.getNext();
        }
    }

}
