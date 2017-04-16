package com.hrishikeshmishra.dsjava.stack.core;

/**
 * Created by hrishikesh.mishra on 11/02/16.
 */
public class DoublyLinkedList<E> {

    private static class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E e, Node<E> p, Node<E> n) {
            element = e; prev = p; next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setPrev(Node<E> p) {
            prev = p;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    private Node<E> header;
    private Node<E> tailer;
    private int size = 0;

    public DoublyLinkedList() {
        header = new Node<E>(null, null, null);
        tailer = new Node<E>(null, null, null);
        header.setNext(tailer);
    }

    public int size(){
       return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if(isEmpty()) return null;
        return header.getNext().getElement();
    }

    public E last(){
        if(isEmpty()) return null;
        return tailer.getPrev().getElement();
    }



    public void addFirst(E e){
        addBetween(e, header, header.getNext());
    }

    public void addLast(E e){
        addBetween(e,tailer.getPrev(), tailer);
    }

    public E removeFist(){
        if(isEmpty()) return null;
        return remove(header.getNext());
    }

    public E removeLast(){
        if(isEmpty()) return null;
        return remove(tailer.getPrev());
    }

    public void addBetween(E e, Node<E> predecessor, Node<E> successor){
        Node<E> node = new Node<>(e, predecessor, successor);
        predecessor.setNext(node);
        successor.setPrev(node);
        size++;
    }

    public E remove(Node<E> node){
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size --;
        return node.getElement();
    }
}
