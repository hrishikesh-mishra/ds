package com.hrishikeshmishra.practices.list;

import java.util.Objects;

import static com.hrishikeshmishra.practices.list.LinkedList.*;

/**
 * <p>
 * Add two numbers represented by linked lists
 * Number : 786, list representation: 6 -> 8 -> 7
 * </p>
 *
 * @author hrishikesh.mishra
 */
public class AddList {

    public Node addTwoList(Node list1, Node list2) {
        Node result = null, prev = null;

        int carry = 0, sum = 0;

        while (!Objects.isNull(list1) || !Objects.isNull(list2)) {

            sum = carry + getData(list1) + getData(list2);
            carry = getCarry(sum);
            sum %= 10;

            Node temp = new Node(sum);
            if (Objects.isNull(result)) {
                result = temp;
            } else {
                prev.setNext(temp);
            }

            prev = temp;

            if (!Objects.isNull(list1)) {
                list1 = list1.getNext();
            }

            if (!Objects.isNull(list2)) {
                list2 = list2.getNext();
            }
        }

        if (carry > 0) {
            prev.setNext(new Node(carry));
        }

        return result;
    }

    private int getData(Node node) {
        return Objects.isNull(node) ? 0 : node.getData();
    }

    private int getCarry(int sum) {
        return sum >= 10 ? 1 : 0;
    }
}


class AddListTest{

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        Node list1 = new Node(7, new Node(5, new Node(9, new Node(4, new Node(6)))));
        System.out.print("First List is ");
        linkedList.print(list1);

        Node list2 = new Node(8, new Node(4));
        System.out.print("Second List is ");
        linkedList.print(list2);

        AddList addList = new AddList();

        Node list3 = addList.addTwoList(list1, list2);
        System.out.print("Result List is ");
        linkedList.print(list3);




    }
}