package com.hrishikeshmishra.practices.stack;

/**
 * Problem:
 * Next Greater Element (NGE)
 * ;
 * Given an array, print the Next Greater Element (NGE) for every element.
 * The Next greater Element for an element x is the first greater element on the right side of x in array.
 * Elements for which no greater element exist, consider next greater element as -1.
 * ;
 * ; Facts about NGE :
 * a) For any array, rightmost element always has next greater element as -1.
 * b) For an array which is sorted in decreasing order, all elements have next greater element as -1.
 * c) For the input array [4, 5, 2, 25}, the next greater elements for each element are as follows.
 * ;
 * For the input array [4, 5, 2, 25}, the next greater elements for each element are as follows.
 * Element  -->     NGE
 * 4        -->     5
 * 5        -->     25
 * 2        -->     25
 * 25       -->     -1
 * ;
 * ;
 * Algorithm:
 * - Create Stack to hold larger values
 * - Iterate given array from start to end
 * - - If stack not empty and stack.top < array[i] then,
 * - - - Print "NSF of stack.pop is array[i]"
 * - - stack.push (array[i])
 * - Iterate stack till not empty
 * - - Print "NSF of stack.pop is -1 "
 * ;
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/next-greater-element-nge/
 */
public class NextGreaterElement {

    public static void findNSG(int[] array) {

        /** In case no element in array **/
        if (array.length == 0) {
            return;
        }

        /** In case one element in array **/
        if (array.length == 1) {
            System.out.println(array[0] + " --> " + -1);
        }


        Stack<Integer> stack = new Stack<>();

        /** Iterate all element one by one **/
        for (int i = 0; i < array.length; i++) {

            /** Get current element **/
            int currentElement = array[i];

            /**
             * In all previous element that are smaller than current element,
             * because current element will be NGE for those smaller elements.
             */
            while (!stack.isEmpty() && stack.peek() < currentElement) {
                System.out.println(stack.pop() + " --> " + currentElement);
            }

            /** Push current element to stack, to get NGE for this in next right elements in array **/
            stack.push(currentElement);
        }

        /** Print all those elements who's NGE was not found **/
        while (!stack.isEmpty()) {
            /** Default NGE is -1 **/
            System.out.println(stack.pop() + " --> " + -1);
        }
    }
}


class NextGreaterElementTest {
    public static void main(String[] args) {
        int[] array1 = {4, 5, 2, 25};
        int[] array2 = {400, 350, 300, 250};

        NextGreaterElement.findNSG(array1);
        System.out.println("\n");
        NextGreaterElement.findNSG(array2);

    }
}

//