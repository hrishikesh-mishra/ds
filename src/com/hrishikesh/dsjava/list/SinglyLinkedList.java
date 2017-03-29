package com.hrishikesh.dsjava.list;

import java.util.Objects;

/**
 * Singly Linked List
 *
 * Q3.
 */
public class SinglyLinkedList<E> {

    private static class Node<E>{
        private E element;
        private Node<E> next;

       public Node(E e, Node<E> n){
           element = e;
           next = n;
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

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    ", next=" + next +
                    '}';
        }
    }


    private Node<E> head = null;
    private Node<E> tail = null;
    private int size =0 ;

    public SinglyLinkedList() {
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return size ==0 ;
    }

    public E getFirst(){
        if(isEmpty()){
            return null;
        }

        return head.getElement();
    }
    public E last(){
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);
        if (size ==0){
            tail = head;
        }
        size ++;
    }

    public void addLast(E e){
        Node<E> newest  = new Node<>(e, null);
        if(isEmpty()){
            head = newest;
        }else {
            tail.setNext(newest);
        }

        tail = newest;
        size ++;
    }

    public E removeFirst(){
        if(isEmpty()) return null;

        E answer = head.getElement();
        head = head.getNext();
        size --;

        if(size == 0){
            tail = null;
        }

        return answer;
    }

    @Override
    public String toString() {
        return "SinglyLinkedList{" +
                "head=" + head +
                ", tail=" + tail +
                ", size=" + size +
                '}';
    }

    public void walk(){
        Node<E> node = head;
        while(!Objects.isNull(node)){
            System.out.print(node.getElement() + " -> ");
            node = node.getNext();
        }
    }

    public E getSecondLast(){
        Node<E> node = head.getNext();
        Node<E> secondLastNode = head;
        while(!Objects.isNull(node.getNext())){
            node = node.getNext();
            secondLastNode = secondLastNode.getNext();

        }
        return Objects.isNull(secondLastNode)?null : secondLastNode.getElement();
    }
    public static void main(String[] args) {
        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
        singlyLinkedList.addLast(1);
        singlyLinkedList.addLast(2);
        singlyLinkedList.addLast(3);
        singlyLinkedList.addLast(4);
        singlyLinkedList.walk();
        System.out.println("\n\n\n" + singlyLinkedList.getSecondLast());

    }
}
