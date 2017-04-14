package com.hrishikesh.practices.binarytree;


import java.util.Objects;

/**
 * Problem:
 * Binary Tree vertical sum using doubly linked list (DLL)
 * ;
 * Hint : Solution is based on  horizontal distances
 *
 * @author hrishikesh.mishra
 */
public class BinaryTreeVerticalSumUsingDLL {

    public static class DoublyLinkedList {
        DoublyLinkedList previous;
        DoublyLinkedList next;
        int data;
    }

    public static void sum(BinaryTreeNode<Integer> node, DoublyLinkedList dllNode) {

        /** Base case: when node is null **/
        if (Objects.isNull(node)) {
            return;
        }

        dllNode.data += node.getData();

        /** For left child **/
        if (!Objects.isNull(node.getLeft())) {

            if (Objects.isNull(dllNode.previous)) {
                DoublyLinkedList temp = new DoublyLinkedList();
                temp.next = dllNode;
                dllNode.previous = temp;
            }

            sum(node.getLeft(), dllNode.previous);

        }

        /** For right child **/
        if (!Objects.isNull(node.getRight())) {

            if (Objects.isNull(dllNode.next)) {
                DoublyLinkedList temp = new DoublyLinkedList();
                temp.previous = dllNode;
                dllNode.next = temp;
            }

            sum(node.getRight(), dllNode.next);
        }

    }

}

class BinaryTreeVerticalSumUsingDLLTest {
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

        BinaryTreeVerticalSumUsingDLL.DoublyLinkedList dll = new BinaryTreeVerticalSumUsingDLL.DoublyLinkedList();

        BinaryTreeVerticalSumUsingDLL.sum(root, dll);

        BinaryTreeVerticalSumUsingDLL.DoublyLinkedList head = null;

        while (!Objects.isNull(dll)) {
            head = dll;
            dll = dll.previous;
        }

        System.out.print("Sum : ");
        while (!Objects.isNull(head)) {
            System.out.printf("%d ", head.data);
            head = head.next;
        }

    }
}
