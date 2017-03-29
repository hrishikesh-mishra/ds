package com.hrishikesh.practices.list;

import java.util.Objects;

/**
 * Problem:
 * Flattening a Linked List
 * ;
 * Given a linked list where every node represents a linked list and contains two pointers of its type:
 * (i) Pointer to next node in the main list (we call it ‘right’ pointer in below code)
 * (ii) Pointer to a linked list where this node is head (we call it ‘down’ pointer in below code).
 * All linked lists are sorted. See the following example
 * ;
 * ;
 * 5 -> 10 -> 19 -> 28
 * |    |     |     |
 * V    V     V     V
 * 7    20    22    35
 * |          |     |
 * V          V     V
 * 8          50    40
 * |                |
 * V                V
 * 30               45
 * ;
 * ;
 * Output list should be 5->7->8->10->19->20->22->28->30->35->40->45->50
 * ;
 * ;
 * Algorithm :
 * - Create result merge list head and node
 * - Iterate top node right pointer from left to right
 * - - Call list merger with result merge list node and one list
 * - Return head of merged list
 *
 * @author hrishikesh.mishra
 * @link hrishikeshmishra.com/flattening-a-linked-list/
 */
public class LinkedListFlattener {

    private Node head;

    public LinkedListFlattener(int rootData) {
        this.head = new Node(rootData);
    }

    public class Node {

        private int data;
        private Node right, down;

        public Node(int data) {
            this.data = data;
            right = null;
            down = null;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getDown() {
            return down;
        }

        public void setDown(Node down) {
            this.down = down;
        }

        @Override
        public String toString() {
            return "Node(" + data + ')';
        }
    }

    public void addToDown(Node node, int data) {
        while (!Objects.isNull(node.getDown())) {
            node = node.getDown();
        }
        node.setDown(new Node(data));
    }

    public Node getHead() {
        return head;
    }

    public void addToRight(Node node, int data) {
        node.setRight(new Node(data));
    }

    public void print(Node node) {
        while (!Objects.isNull(node)) {
            System.out.print(node.getData() + " ");
            node = node.getDown();
        }
        System.out.println();
    }

    public Node doFlattening() {

        /** Base case when only one list **/
        if (Objects.isNull(head.getRight())) {
            return head;
        }

        Node mergeListHead = null,
                temp = head;

        while (!Objects.isNull(temp)) {
            mergeListHead = merge(mergeListHead, temp);
            temp = temp.getRight();
        }

        return mergeListHead;

    }

    private Node merge(Node list1, Node list2) {
        Node resultHead = null, temp = null;

        while (!Objects.isNull(list1) || !Objects.isNull(list2)) {

            if (!Objects.isNull(list1) && !Objects.isNull(list2)) {
                if (list1.getData() > list2.getData()) {
                    temp = addToMergeList(temp, list2.getData());
                    list2 = list2.getDown();
                } else {
                    temp = addToMergeList(temp, list1.getData());
                    list1 = list1.getDown();
                }
            } else if (!Objects.isNull(list1)) {
                temp = addToMergeList(temp, list1.getData());
                list1 = list1.getDown();
            } else {
                temp = addToMergeList(temp, list2.getData());
                list2 = list2.getDown();
            }

            if (Objects.isNull(resultHead)) {
                resultHead = temp;
            }
        }

        return resultHead;
    }


    private Node addToMergeList(Node node, int data) {

        if (Objects.isNull(node)) {
            node = new Node(data);
        } else {
            node.setDown(new Node(data));
            node = node.getDown();
        }
        return node;
    }


}

class LinkedListFlattenerTest {
    public static void main(String[] args) {

        /** List 1 **/
        LinkedListFlattener list = new LinkedListFlattener(5);
        list.addToDown(list.getHead(), 7);
        list.addToDown(list.getHead(), 8);
        list.addToDown(list.getHead(), 30);

        /** List 2 **/
        list.addToRight(list.getHead(), 10);
        list.addToDown(list.getHead().getRight(), 20);

        /** List 3 **/
        list.addToRight(list.getHead().getRight(), 19);
        list.addToDown(list.getHead().getRight().getRight(), 22);
        list.addToDown(list.getHead().getRight().getRight(), 50);

        /** List 4 **/
        list.addToRight(list.getHead().getRight().getRight(), 28);
        list.addToDown(list.getHead().getRight().getRight().getRight(), 35);
        list.addToDown(list.getHead().getRight().getRight().getRight(), 40);
        list.addToDown(list.getHead().getRight().getRight().getRight(), 45);


        System.out.print("List:1 - ");
        list.print(list.getHead());

        System.out.print("List:2 - ");
        list.print(list.getHead().getRight());

        System.out.print("List:3 - ");
        list.print(list.getHead().getRight().getRight());

        System.out.print("List:4 - ");
        list.print(list.getHead().getRight().getRight().getRight());

        LinkedListFlattener.Node node = list.doFlattening();

        System.out.print("Merged list: ");
        list.print(node);

    }
}


