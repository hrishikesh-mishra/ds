package com.hrishikesh.practices.stack;

/**
 * Problem:
 * Sort a stack using recursion
 * ;
 * Solution:
 * - Just another version of {@link StackReverse}
 * ;
 * Algorithm:
 * ;
 * Sort (stack) Function
 * - - If stack is empty then
 * - - - return ;
 * - - Set temp = stack.pop
 * - - Call Sort(stack)
 * - - Call function AddInSortedOrder(stack, temp)
 * ;
 * AddInSortedOrder(stack, element) Function
 * - If stack is empty or stack.top < element then,
 * - - stack.push (element)
 * - - return ;
 * - Set temp = stack.pop
 * - Call AddInSortedOrder(stack, element)
 * - stack.push(temp)
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/sort-a-stack-using-recursion/
 */
public class StackSort {

    public static void sort(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }

        /** First push all elements to program stack **/
        int element = stack.pop();
        sort(stack);

        /** Insert element in sorted order stack **/
        pushInSortedOrder(stack, element);
    }

    private static void pushInSortedOrder(Stack<Integer> stack, int element) {

        /**
         * Insert element in stack only when stack is empty
         * or
         * top element of stack is less than or equal to requested element
         */
        if (stack.isEmpty() || stack.peek() <= element) {
            stack.push(element);
            return;
        }

        /** temporarily remove all element from stack **/
        int temp = stack.pop();
        pushInSortedOrder(stack, element);

        /** Add remove elements back to stack after adding requested element **/
        stack.push(temp);
    }

}


class StackSortTest {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        stack.push(10);
        stack.push(7);
        stack.push(40);
        stack.push(1);
        stack.push(3);
        stack.push(18);

        System.out.println(stack);
        StackSort.sort(stack);
        System.out.println(stack);

    }
}