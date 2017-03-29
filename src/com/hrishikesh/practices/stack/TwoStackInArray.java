package com.hrishikesh.practices.stack;

/**
 * Problem:
 * Implement two stacks in an array
 * ;
 * Algorithm:
 * - Take an array, for stack 1 push/pop from start of array and for stack 2  push/pop from end.
 * - Push to stack 1
 * - - If top1 + 1 >= top2 then
 * - - - throw Stack Full Exception
 * - - array[top++] = item
 * - Same for Stack 2
 * - Pop Stack1
 * - - If top1 < 0 then
 * - - - throw Stack Empty Exception
 * - - return array[top--]
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/implement-two-stacks-in-an-array/
 */
public class TwoStackInArray {

    private int[] stack;
    private int top1;
    private int top2;

    public TwoStackInArray(int size) {
        stack = new int[size];
        top1 = -1;
        top2 = size;
    }

    public void push1(int data) {
        if (!isSpaceAvailable()) {
            throw new StackOverflowError("Stack is full");
        }

        stack[++top1] = data;
    }

    public void push2(int data) {
        if (!isSpaceAvailable()) {
            throw new StackOverflowError("Stack is full");
        }

        stack[--top2] = data;
    }

    public int pop1() {
        if (isStack1Empty()) {
            throw new RuntimeException("Stack1 underflow.");
        }

        return stack[top1--];

    }


    public int pop2() {
        if (isStack2Empty()) {
            throw new RuntimeException("Stack1 underflow.");
        }

        return stack[top2++];
    }

    private boolean isSpaceAvailable() {
        return (top2 - top1) - 1 > 0;
    }

    private boolean isStack1Empty() {
        return top1 == -1;
    }

    private boolean isStack2Empty() {
        return top2 == stack.length;
    }
}


class TwoStackInArrayTest {

    public static void main(String[] args) {

        TwoStackInArray stack = new TwoStackInArray(6);
        stack.push1(1);
        stack.push1(2);
        stack.push1(3);
        stack.push2(4);
        System.out.println("Stack 1 Pop: " + stack.pop1());
        System.out.println("Stack 1 Pop: " + stack.pop1());
        System.out.println("Stack 1 Pop: " + stack.pop1());
        System.out.println("Stack 2 Pop: " + stack.pop2());
    }
}