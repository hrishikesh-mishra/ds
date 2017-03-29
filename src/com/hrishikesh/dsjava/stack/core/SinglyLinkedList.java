package com.hrishikesh.dsjava.stack.core;

/**
 * Singly Linked List
 *
 * Created by hrishikesh.mishra on 17/01/16.
 */
public class SinglyLinkedList<E> {

    private static class Node <E>{

        private E element;
        private Node<E> next;

        private Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    private Node<E> head = null;
    private Node<E> tail = null;

    private int size = 0;

    public SinglyLinkedList() {
    }

    public Node<E> getHead() {
        return head;
    }

    public void setHead(Node<E> head) {
        this.head = head;
    }

    public Node<E> getTail() {
        return tail;
    }

    public void setTail(Node<E> tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    public E first(){
        if(isEmpty()) return null;
        return head.getElement();
    }

    public E last(){
        if(isEmpty()) return null;
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);
        if(isEmpty()) tail = head;
        size++;
    }

    public void addLast(E e){
        Node<E> newNode = new Node<>(e, null);
        if(isEmpty()) head = newNode;
        else tail.setNext(newNode);
        tail = newNode;
    }

    public E removeFist() {
        if(isEmpty()) return null;
        E answer = head.getElement(); head = head.getNext(); size --;
        if(isEmpty()) tail = null;
        return answer;
    }

}
