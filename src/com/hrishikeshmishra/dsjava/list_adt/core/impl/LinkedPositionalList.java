package com.hrishikeshmishra.dsjava.list_adt.core.impl;

import com.hrishikeshmishra.dsjava.list_adt.core.Position;

import java.util.List;
import java.util.Objects;

/**
 * Created by hrishikesh.mishra on 24/03/16.
 */
public class LinkedPositionalList<E> implements PositionalList<E>{


    private static class Node<E> implements Position<E>{

        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E e, Node<E> p, Node<E> n){
            element = e;
            prev = p;
            next = n;
        }

        public E getElement() throws IllegalStateException {
            if(Objects.isNull(next)){
                throw new IllegalStateException("Position no longer valid");
            }
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

    }

    private Node<E> header;
    private Node<E> trailer;
    private int size= 0;


    public LinkedPositionalList() {
        header = new Node<E>(null, null, null);
        trailer = new Node<E>(null, header, null);
        header.setNext(trailer);
    }

    private Node<E> validate(Position<E> p) throws IllegalArgumentException{
        if(!(p instanceof  Node)) throw new IllegalArgumentException("Invalid p");
        Node<E> node = (Node<E>) p; // safe cast
        if(Objects.isNull(node.getNext()))
            throw new IllegalArgumentException("P is no longer in the  list ");
        return node;
    }

    private Position<E> position(Node<E> node){
        if(node == header || node == trailer) return null;
        return node;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Position<E> first() {
        return position(header.getNext());
    }

    @Override
    public Position<E> last() {
        return position(header.getPrev());
    }

    @Override
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }

    @Override
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    private Position<E> addBetween(E e, Node<E> pred, Node<E> succs){
        Node<E> newest = new Node<>(e, pred, succs);
        pred.setNext(newest);
        succs.setPrev(newest);
        size++;
        return newest;
    }

    @Override
    public Position<E> addFirst(E e) {
        return addBetween(e, header, header.getNext());
    }

    @Override
    public Position<E> addLast(E e) {
        return addBetween(e, trailer.getPrev(), trailer);
    }

    @Override
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    @Override
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    @Override
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    @Override
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        predecessor.setPrev(predecessor);
        size--;

        E answer = node.getElement();
        node.setNext(null);
        node.setPrev(null);
        node.setElement(null);

        return answer;
    }

    @Override
    public Iterable<Position<E>> positions() {
        List<Position<E>> snapshot = new java.util.ArrayList<>();
        if(isEmpty()) return null;

        Position<E> walk = first();
        while (!Objects.isNull(walk)){
            snapshot.add(walk);
            walk = after(walk);
        }
        return snapshot;
    }
}
