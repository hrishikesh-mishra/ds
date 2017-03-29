package com.hrishikesh.practices.list;

import java.util.Objects;

import static com.hrishikesh.practices.list.LinkedList.*;

/**
 * Problem:
 * List multiplication
 * ;
 * Algorithm:
 * - Base case: if lis1 or list2 is empty then return 0
 * - Set following variables:
 * - - carry =0
 * - - skipCounter = 1
 * - - multi = 0
 * - - resultHead = null
 * - Iterate list2 till node2 != null
 * - - move resultNode from resultHead by skipCounter steps and
 * - - Increase skipCounter by one
 * - - Iterate list1 till node1 != null
 * - - - multi = getPreviousDigit(resultNode) + carry + node2.getData() * node1.getData()
 * - - - add multi % 10 to resultNode
 * - - - carry = multi / 10
 * - - If carry > 0 then, add to result set
 * - Return resultHead
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/list-multiplication/
 */
public class ListMultiplication {

    public static long multiplyTwoLists(Node list1, Node list2) {
        Node resultHead = null, temp = null;

        /** When one of list is empty **/
        if (Objects.isNull(list1) || Objects.isNull(list2)) {
            return 0;
        }

        int carry = 0, multi, skipCounter = 0;
        Node list2Ptr = list2;

        while (!Objects.isNull(list2Ptr)) {

            temp = move(resultHead, skipCounter - 1);

            /** Checking for first entry  **/
            if (Objects.isNull(resultHead)) {
                resultHead = temp = new Node(0);
            } else {
                temp = temp.getNext();
            }

            skipCounter++;
            Node list1Ptr = list1;
            while (!Objects.isNull(list1Ptr)) {

                multi = getPreviousDigit(temp) + carry + list2Ptr.getData() * list1Ptr.getData();
                carry = multi / 10;
                multi %= 10;
                temp.setData(multi);
                if (Objects.isNull(temp.getNext())) {
                    temp.setNext(new Node(0));
                }
                temp = temp.getNext();
                list1Ptr = list1Ptr.getNext();
            }


            if (carry > 0) {
                int sum = temp.getData() + carry;
                carry = sum / 10;
                sum %= 10;
                temp.setData(sum);
                if (carry > 0) {
                    temp.setNext(new Node(carry));
                    temp = temp.getNext();
                }
            }

            list2Ptr = list2Ptr.getNext();
        }

        StringBuilder sb = new StringBuilder();

        while (!Objects.isNull(resultHead)) {
            sb.append(resultHead.getData());
            resultHead = resultHead.getNext();
        }

        sb.reverse();
        return Long.parseLong(sb.toString());
    }

    private static Node move(Node node, int counter) {
        for (int i = 0; i < counter; i++) {
            node = node.getNext();
        }
        return node;
    }

    private static int getPreviousDigit(Node node) {
        return Objects.isNull(node) ? 0 : node.getData();
    }

}


class ListMultiplicationTest {

    public static void main(String[] args) {
        Node list1 = new Node(5, new Node(8, new Node(3)));
        Node list2 = new Node(5, new Node(8));


        System.out.println("385 X 85 = " + ListMultiplication.multiplyTwoLists(list1, list2));

        Node list3 = new Node(3);
        Node list4 = new Node(3, new Node(1));


        System.out.println("385 X 85 = " + ListMultiplication.multiplyTwoLists(list3, list4));
    }
}