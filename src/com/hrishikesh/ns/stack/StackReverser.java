package com.hrishikesh.ns.stack;

/**
 * Problem:
 * Given a stack how to reverse the contents of stacks using only
 * stack operation (push and pop)
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/reverse-stack-content-only-using-push-and-pop/
 */
public class StackReverser {

    public <E> void reverse(Stack<E> stack) {
        if (stack.isEmpty()) return;

        E data = stack.pop();
        reverse(stack);
        insertAtBottom(stack, data);
    }

    private <E> void insertAtBottom(Stack<E> stack, E data) {
        if (stack.isEmpty()) {
            stack.push(data);
            return;
        }
        E temp = stack.pop();
        insertAtBottom(stack, data);
        stack.push(temp);
    }
}

class StackReverserTest {
    public static void main(String[] args) {
        Stack<Integer> stack = new LinkedStack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);

        System.out.println("Before reverse: " + stack);
        StackReverser reverser = new StackReverser();
        reverser.reverse(stack);

        System.out.println("After reverse:" + stack);
    }

}

