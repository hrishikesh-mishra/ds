package com.hrishikesh.practices.binarytree;

import java.util.Objects;

/**
 * Problem:
 * Binary Tree top view without HashMap but using DLL
 *
 * @author hrishikesh.mishra
 */
public class BinaryTreeTopViewWithoutHashMap {

    public static class DoublyLinkedList {
        DoublyLinkedList previous;
        DoublyLinkedList next;
        int data;
        int level;

        public DoublyLinkedList() {
            level = Integer.MAX_VALUE;
        }
    }

    public static void viewTop(BinaryTreeNode<Integer> node, DoublyLinkedList dll) {
        viewTopHelper(node, dll, 0);
    }

    public static void viewTopHelper(BinaryTreeNode<Integer> node, DoublyLinkedList dll, int level) {

        /** Base case: When node is empty **/
        if (Objects.isNull(node)) {
            return;
        }

        /** View for node when level is smaller **/
        if (level < dll.level) {
            dll.level = level;
            dll.data = node.getData();
        }

        /** For left child **/
        if (!Objects.isNull(node.getLeft())) {

            if (Objects.isNull(dll.previous)) {
                DoublyLinkedList temp = new DoublyLinkedList();
                temp.next = dll;
                dll.previous = temp;
            }

            viewTopHelper(node.getLeft(), dll.previous, level + 1);
        }


        /** For right child **/
        if (!Objects.isNull(node.getRight())) {

            if (Objects.isNull(dll.next)) {
                DoublyLinkedList temp = new DoublyLinkedList();
                temp.previous = dll;
                dll.next = temp;
            }

            viewTopHelper(node.getRight(), dll.next, level + 1);
        }
    }

}

class BinaryTreeTopViewWithoutHashMapTest {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(20,
                new BinaryTreeNode<>(8,
                        new BinaryTreeNode<>(4),
                        new BinaryTreeNode<>(12,
                                new BinaryTreeNode<>(10),
                                new BinaryTreeNode<>(14))),
                new BinaryTreeNode<>(22,
                        null,
                        new BinaryTreeNode<>(25)));

        BTPrinter.print(root);

        BinaryTreeTopViewWithoutHashMap.DoublyLinkedList dll = new BinaryTreeTopViewWithoutHashMap.DoublyLinkedList();
        BinaryTreeTopViewWithoutHashMap.viewTop(root, dll);


        BinaryTreeTopViewWithoutHashMap.DoublyLinkedList head = null;

        while (!Objects.isNull(dll)) {
            head = dll;
            dll = dll.previous;
        }

        System.out.println("Top View: ");

        while (!Objects.isNull(head)) {
            System.out.printf("%d ", head.data);
            head = head.next;
        }

    }
}
