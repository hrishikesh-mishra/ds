package com.hrishikesh.practices.list;

import java.util.NoSuchElementException;
import java.util.Objects;

import static com.hrishikesh.practices.list.LinkedList.*;

/**
 * Problem:
 * Implement Queue using Linked List
 * ;
 * Algorithm:
 * - For Enqueue Operation
 * - - Iterate till node.next = null
 * - - Add new node to node.next
 * - For Dequeue Operation
 * - - Remove it from head and
 * - - Update head pointer i.e. head = head.next
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/implement-queue-using-linked-list/
 */
public class ListQueue {

    private Node head;

    public void enqueue(int data) {
        head = new Node(data, head);
    }

    public int dequeue() {

        /** Base case when no element in queue **/
        if (Objects.isNull(head)) {
            throw new NoSuchElementException("Queue is empty");
        }

        /** When one element in queue **/
        if (Objects.isNull(head.getNext())) {
            int data = head.getData();
            head = null;
            return data;
        }

        /** When more than one element in list **/
        Node temp = head.getNext(), prev = head;
        while (!Objects.isNull(temp.getNext())) {
            prev = prev.getNext();
            temp = temp.getNext();
        }

        prev.setNext(temp.getNext());

        return temp.getData();
    }

    public void print() {
        LinkedList.print(head);
    }
}

class ListQueueTest {

    public static void main(String[] args) {

        ListQueue queue = new ListQueue();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.print();
        System.out.println("Dequeue: " + queue.dequeue());
        queue.print();
        System.out.println("Dequeue: " + queue.dequeue());

    }
}
