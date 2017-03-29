package com.hrishikesh.ns.list;

/**
 * Created by hrishikesh.mishra
 */
public class DoubleLinkedListNode {

    private int data;
    private DoubleLinkedListNode previous;
    private DoubleLinkedListNode next;

    public DoubleLinkedListNode(int data) {
        this.data = data;
    }

    public DoubleLinkedListNode(int data, DoubleLinkedListNode previous, DoubleLinkedListNode next) {
        this.data = data;
        this.previous = previous;
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public DoubleLinkedListNode getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleLinkedListNode previous) {
        this.previous = previous;
    }

    public DoubleLinkedListNode getNext() {
        return next;
    }

    public void setNext(DoubleLinkedListNode next) {
        this.next = next;
    }
}
