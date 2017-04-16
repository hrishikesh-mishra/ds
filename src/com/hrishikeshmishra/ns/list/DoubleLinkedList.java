package com.hrishikeshmishra.ns.list;

/**
 * Created by hrishikesh.mishra
 */
public class DoubleLinkedList {

    private DoubleLinkedListNode head;
    private DoubleLinkedListNode tail;
    private int size;

    public DoubleLinkedList() {
        head = new DoubleLinkedListNode(Integer.MIN_VALUE, null, null);
        tail = new DoubleLinkedListNode(Integer.MAX_VALUE, head, null);
        head.setNext(tail);
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return getSize() <=  0;
    }

    public void insertAtHead(int data){
        DoubleLinkedListNode newNode = new DoubleLinkedListNode(data, head, head.getNext());
        newNode.getNext().setPrevious(newNode);
        head.setNext(newNode);
        size++;
    }

    public void insertAtEnd(int data){
        DoubleLinkedListNode newNode = new DoubleLinkedListNode(data, tail.getPrevious(), tail);
        newNode.getPrevious().setNext(newNode);
        tail.setPrevious(newNode);
        size++;
    }

    public void insert(int position, int data){
        if (position < 1 || (!isEmpty() && position > size))
            throw new IllegalArgumentException("Position should not be less than 1 and greater than size");

        if(position == 1 ){
            DoubleLinkedListNode newNode = new DoubleLinkedListNode(data, head, head.getNext());
            head.getNext().setPrevious(newNode);
            head.setNext(newNode);
        }else {
            DoubleLinkedListNode temp = head.getNext();
            for(int counter = 1; counter < position; counter++ ) temp = temp.getNext();

            DoubleLinkedListNode newNode = new DoubleLinkedListNode(data, temp.getPrevious(), temp);
            temp.getPrevious().setNext(newNode);
            temp.setPrevious(newNode);
        }
        size++;
    }

    public int removeFirst(){
        if(isEmpty())
            throw new IllegalStateException("List is empty!");

        DoubleLinkedListNode temp = head.getNext();
        temp.getNext().setPrevious(temp.getPrevious());
        head.setNext(temp.getNext());
        temp.setNext(null);
        temp.setPrevious(null);
        size --;
        return temp.getData();
    }

    public int removeLast(){
        if(isEmpty())
            throw new IllegalStateException("List is empty!");

        DoubleLinkedListNode temp = tail.getPrevious();
        tail.setPrevious(temp.getPrevious());
        temp.getPrevious().setNext(tail);
        temp.setNext(null);
        temp.setPrevious(null);
        size --;
        return temp.getData();
    }


    public int remove(int position){
        if(isEmpty())
            throw new IllegalStateException("List is empty!");

        if (position < 1 || position > size)
            throw new IllegalArgumentException("Position should not be less than 1 and greater than size");

        DoubleLinkedListNode temp = head.getNext();

        if(position == 1){
            head.setNext(temp.getNext());
            temp.getNext().setPrevious(temp.getPrevious());

            temp.setPrevious(null); temp.setNext(null);
        }else {
            for (int counter = 1; counter < position; counter ++) temp = temp.getNext();
            DoubleLinkedListNode next = temp.getNext(), previous = temp.getPrevious();
            next.setPrevious(temp.getPrevious()); previous.setNext(temp.getNext());
            temp.setNext(null); temp.setPrevious(null);
        }
        size--;
        return temp.getData();
    }

    public void traverseForward(){
        DoubleLinkedListNode node = head.getNext();

        System.out.print("Forward Traverse : [");
        while (node != tail){
            System.out.print(node.getData() + ", ");
            node = node.getNext();
        }
        System.out.print("]");
    }

    public void traverseReverse(){
        DoubleLinkedListNode node = tail.getPrevious();
        System.out.print("Reverse Traverse : [");
        while (node != head){
            System.out.print(node.getData() + ", ");
            node = node.getPrevious();
        }
        System.out.print("]");
    }
}



class DoubleLinkedListTest{

    public static void main(String[] args) {

        DoubleLinkedList dll = new DoubleLinkedList();
        print(dll);

        dll.insertAtEnd(1);
        print(dll);

        dll.insertAtHead(2);
        print(dll);

        int temp = dll.removeFirst();
        System.out.println(" First Removed :" + temp);
        print(dll);

        temp = dll.removeLast();
        System.out.println(" Last Removed :" + temp);
        print(dll);

        dll.insert(1, 12);
        dll.insert(1, 6);
        dll.insert(2, 7);
        dll.insert(3, 10);
        print(dll);

        temp = dll.remove(1);
        System.out.println(" Position 1, Removed :" + temp);
        print(dll);

        temp = dll.remove(3);
        System.out.println(" Position 3, Removed :" + temp);
        print(dll);

        temp = dll.remove(1);
        System.out.println(" Position 1, Removed :" + temp);
        print(dll);

        temp = dll.remove(1);
        System.out.println(" Position 1, Removed :" + temp);
        print(dll);

    }
    public static void print(DoubleLinkedList dll){
        System.out.println("-----------------------------------------------------------------");
        dll.traverseForward();
        System.out.println();
        dll.traverseReverse();
        System.out.println("\n-----------------------------------------------------------------\n");
    }
}


