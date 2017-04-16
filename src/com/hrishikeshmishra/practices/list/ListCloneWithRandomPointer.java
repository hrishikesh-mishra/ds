package com.hrishikeshmishra.practices.list;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.hrishikeshmishra.practices.list.ListCloneWithRandomPointer.*;

/**
 * Problem:
 * Clone a linked list with next and random pointer
 * ;
 * Algorithm:
 * - Create a hash map
 * - Create new list head pointer and list node
 * - Now iterate given list from start to end
 * - - Create a new list node by just coping data from original list
 * - - And put original node random node pointer in hash map
 * - Now iterate once again original list and update new list random node pointer with reference to map.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/clone-a-linked-list-with-next-and-random-pointer/
 */
public class ListCloneWithRandomPointer {


    public static class Node {
        private int data;
        private Node next, random;

        //Node constructor
        public Node(int data) {
            this.data = data;
            this.next = this.random = null;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getRandom() {
            return random;
        }

        public void setRandom(Node random) {
            this.random = random;
        }

        @Override
        public String toString() {
            return "Node(" + data + ')';
        }
    }

    public static Node clone(Node head) {
        Node cloneNode = null, node = head;

        Map<Node, Node> map = new HashMap<>();

        while (!Objects.isNull(node)) {
            cloneNode = new Node(node.getData());
            map.put(node, cloneNode);
            node = node.getNext();
        }

        node = head;

        while (!Objects.isNull(node)) {
            cloneNode = map.get(node);
            cloneNode.setNext(map.get(node.getNext()));
            cloneNode.setRandom(map.get(node.getRandom()));
            node = node.getNext();
        }

        return map.get(head);
    }

    public static void print(Node node) {
        while (!Objects.isNull(node)) {
            System.out.print(node.getData() + " ");
            node = node.getNext();
        }

        System.out.println("");
    }

}


class ListCloneWithRandomPointerTest {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);


        node1.setRandom(node3);
        node2.setRandom(node4);
        node3.setRandom(node5);
        node4.setRandom(node1);


        print(node1);

        Node clone = ListCloneWithRandomPointer.clone(node1);
        print(clone);
    }
}