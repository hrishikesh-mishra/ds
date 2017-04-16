package com.hrishikeshmishra.practices.stack;

/**
 * Problem:
 * Reverse a stack using recursion
 * ;
 * ;
 * Solution:
 * - The main part of this is add an element at bottom of stack
 * - That can be done by pop all stack element in temporary variable till stack not beacome empty
 * - This call will be through recursion
 * ;
 * Algorithm
 * - Pop an element from stack
 * - Recursively call this function till stack not become empty
 * - Now call insertAtBottom function with popped element
 * ;
 * ;
 * InsertAtBottom Function
 * - If stack is empty then
 * - - Add given element to stack
 * - - And return from function
 * - Pop element from stack
 * - And recursively call InsertAtBottom
 * - Add pop element back to stack
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/reverse-a-stack-using-recursion/
 */
public class StackReverse {


    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }

        /** First push all element to program stack **/
        int element = stack.pop();
        reverse(stack);

        /** Insert element at bottom of stack **/
        insertAtBottom(stack, element);
    }

    private static void insertAtBottom(Stack<Integer> stack, int element) {

        /** if stack is empty then add element in stack **/
        if (stack.isEmpty()) {
            stack.push(element);
            return;
        }

        /** temporarily remove all element from stack to add element at bottom  **/
        int temp = stack.pop();
        insertAtBottom(stack, element);

        /** Add remove elements back to stack after adding bottom element **/
        stack.push(temp);
    }

}

class StackReverseTest {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack);
        StackReverse.reverse(stack);
        System.out.println(stack);
    }
}
