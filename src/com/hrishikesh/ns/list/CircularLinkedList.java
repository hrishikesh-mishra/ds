package com.hrishikesh.ns.list;

import sun.plugin.dom.exception.InvalidStateException;

import java.util.StringJoiner;

/**
 * Circular linked list
 */
public class CircularLinkedList {

    protected CLLNode tail;
    protected int length;

    public CircularLinkedList() {
        this.tail = null;
        this.length = 0;
    }

    public void addToHead(int data){
        CLLNode temp = new CLLNode(data);
        if(isEmpty()){
            tail = temp;
            tail.setNext(tail);
        }else {
            temp.setNext(tail.getNext());
            tail.setNext(temp);
        }
        length ++;
    }

    public void addToTail(int data){
        addToHead(data);
        tail = tail.getNext();

    }

    public boolean isEmpty(){
        return length == 0;
    }

    public int peek(){
        if(isEmpty())
            throw new InvalidStateException("List is empty.");
        return tail.getNext().getData();
    }


    public int peekTail(){
        if(isEmpty())
            throw new InvalidStateException("List is empty.");
        return tail.getData();
    }

    public int removeFromHead(){
        if(isEmpty())
            throw new InvalidStateException("List is empty.");

        if(length > 1 ){
            CLLNode node = tail.getNext();
            tail.setNext(tail.getNext().getNext());
            node.setNext(null);
            length--;
            return node.getData();
        }else {
            CLLNode node = tail.getNext();
            length--;
            node.setNext(null);
            tail = null;
            return node.getData();
        }
    }

    public int removeFromTail(){
        if(isEmpty())
            throw new InvalidStateException("List is empty.");

        if(length >1){
            CLLNode secondLast = tail.getNext();
            while(secondLast.getNext() != tail)
                secondLast = secondLast.getNext();

            CLLNode temp = tail;
            secondLast.setNext(tail.getNext());
            tail = tail.getNext();
            temp.setNext(null);

            length--;
            return temp.getData();
        }else {
            length--;
            CLLNode temp = tail;
            temp.setNext(null);
            tail = null;
            return temp.getData();
        }
    }

    public boolean contains(int data){
        if(isEmpty()) return false;
        CLLNode node = tail;

        while(node.getData() != data && node.getNext() != tail)
            node = node.getNext();

        return node.getData() == data;
    }

    public int remove(int data){
        if(isEmpty())
            throw new InvalidStateException("List is empty.");

        if(length >1){
            CLLNode pre = tail;
            CLLNode node = tail.getNext();
            while (node.getData() != data && node != tail){
                node = node.getNext();
                pre = pre.getNext();
            }

            if(node.getData() == data) {
                if(tail == node) tail = tail.getNext();
                pre.setNext(node.getNext());
                node.setNext(null);
                length--;
                return node.getData();
            }
        }else {
            CLLNode node = tail;
            if(node.getData() == data){
                tail = null;
                length--;
                return node.getData();
            }
        }

        throw new IllegalArgumentException("Item not found.");

    }


    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        if(!isEmpty()) {
            int lastResult = tail.getData();
            CLLNode tmp = tail.getNext();
            while (tmp != tail){
                sj.add(String.valueOf(tmp.getData()));
                tmp = tmp.getNext();
            }
            sj.add(String.valueOf(lastResult));
        }

       return sj.toString();
    }

    public static void main(String[] args) {
        CircularLinkedList cll = new CircularLinkedList();
        cll.addToHead(13);
        cll.addToHead(12);
        cll.addToHead(11);
        System.out.println(cll);
        cll.addToTail(10);
        System.out.println(cll);

        System.out.println("Peek : " + cll.peek());
        System.out.println("Peek Tail : " + cll.peekTail());

        System.out.println(cll);
        System.out.println("Remove head : " + cll.removeFromHead());
        System.out.println(cll);

        System.out.println("Remove head : " + cll.removeFromHead());
        System.out.println(cll);

        System.out.println("Remove head : " + cll.removeFromHead());
        System.out.println(cll);

        System.out.println("Remove head : " + cll.removeFromHead());
        System.out.println(cll);

        cll.addToTail(10);
        cll.addToHead(9);
        System.out.println(cll);

        System.out.println("Remove Tail : " + cll.removeFromTail());
        System.out.println(cll);
        System.out.println("Remove Tail : " + cll.removeFromTail());
        System.out.println(cll);

        cll.addToHead(1);
        System.out.println(cll);
        System.out.println("Contain 1 : " + cll.contains(1));
        System.out.println("Contain 2 : " + cll.contains(2));

        cll.addToTail(11);
        System.out.println(cll);
        System.out.println("Contain 11 : " + cll.contains(11));
        System.out.println("Contain 2 : " + cll.contains(2));

        cll.addToTail(100);
        System.out.println(cll);
        System.out.println("Contain 11 : " + cll.contains(11));
        System.out.println("Contain 1000 : " + cll.contains(1000));

        cll.removeFromTail();cll.removeFromTail();cll.removeFromTail();
        System.out.println(cll);

        cll.addToHead(1);
        System.out.println(cll);
        System.out.println("Remove 1: " + cll.remove(1));
        System.out.println(cll);


        cll.addToHead(2);
        cll.addToHead(1);
        System.out.println(cll);
        System.out.println("Remove 1: " + cll.remove(1));
        System.out.println(cll);

        System.out.println("Remove 2: " + cll.remove(2));
        System.out.println(cll);
    }

}

