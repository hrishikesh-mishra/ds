package com.hrishikeshmishra.ns.list;

import java.util.Objects;

/**
 * Created by hrishikesh.mishra on 08/05/16.
 */
public class LinkedList {

    private int size;
    private ListNode head;


    public boolean isEmpty(){
        return (size == 0);
    }


    public int getSize() {
        return size;
    }

    public ListNode getHead() {
        return head;
    }

    public void insertAtBegin(ListNode node){
        node.setNext(head);
        head = node;
        size++;
    }

    public void insertAtEnd(ListNode node){
        ListNode temp = head;

        if(isEmpty()){
            head = node;
            size++;
            return;
        }

        while(!Objects.isNull(temp.getNext()))
            temp = temp.getNext();

        temp.setNext(node);
        node.setNext(null);
        size++;
    }

    public void insertAtPosition(ListNode node, int position){
        if(position < 1 )
            throw new IllegalArgumentException("Position should not less than 1");
        if(position > 1 && position > size)
            throw new IllegalArgumentException("Position should not greater than list size");

        /**
         * Special case: when list is empty.
         */
        if(isEmpty()){
            head = node;
            node.setNext(null);
            size++;
            return;
        }

        /**
         * Special case: when inserting at head.
         */
        if(position == 1) {
            node.setNext(head);
            head = node;
            size ++;
            return;
        }

        ListNode temp = head;
        for(int i=1; i < position; i++)
            temp = temp.getNext();


        node.setNext(temp.getNext());
        temp.setNext(node);

        size++;
    }

    public ListNode removeFromBegin(){
        if(isEmpty()) return null;

        ListNode node = head;
        head = node.getNext();
        node.setNext(null);
        size--;
        return node;
    }

    public ListNode removeFromEnd(){
        if(isEmpty()) return null;

        ListNode next = head;
        ListNode prev = null;

        /**
         * Special case: when there is only one item in list
         */
        if(Objects.isNull(next.getNext())){
            head = null;
            size--;
            return next;
        }

        while(!Objects.isNull(next.getNext())){
            prev = next;
            next = next.getNext();
        }

        prev.setNext(null);
        size--;
        return next;
    }

    public ListNode removeAtPosition(int position){
        if(isEmpty()) return null;

        if(position < 1 || position > size)
            throw new IllegalArgumentException("Position should in 1 and " + size);

        /**
         * Special case: size  = 0
         */
        if(size == 1){
            ListNode node = head;
            head = null;
            size--;
            return node;
        }

        ListNode next = head, prev = null;
        for(int i=1; i <position; i++){
            prev = next;
            next = next.getNext();
        }

        prev.setNext(next.getNext());
        next.setNext(null);
        size--;
        return next;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(size);
        sb.append("[");
        ListNode node = head;
        while(!Objects.isNull(node)){
            sb.append(node);
            sb.append(",");
            node = node.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public int getPosition(int data){
        if(isEmpty())
            return -1;

        ListNode temp = head;
        int counter =0;
        while(!Objects.isNull(temp)){
            counter ++;
            if(temp.getData() == data) return counter;
            temp = temp.getNext();
        }
        return -1;
    }
}


class LinkedListTest{
    public static void main(String[] args) {

        LinkedList ll = new LinkedList();
        ll.insertAtBegin(new ListNode(1));
        System.out.println(ll);

        ll.insertAtEnd(new ListNode(2));
        System.out.println(ll);

        ll.insertAtPosition(new ListNode(11), 1);
        System.out.println(ll);

        ll.insertAtPosition(new ListNode(22), 3);
        System.out.println(ll);

        ll.insertAtPosition(new ListNode(33), 2);
        System.out.println(ll);

        int temp = ll.removeFromBegin().getData();
        System.out.println("Removed : " + temp);
        System.out.println(ll);

        temp = ll.removeFromEnd().getData();
        System.out.println("Removed : " + temp);
        System.out.println(ll);

        temp = ll.removeAtPosition(2).getData();
        System.out.println("Removed : " + temp);
        System.out.println(ll);

    }
}