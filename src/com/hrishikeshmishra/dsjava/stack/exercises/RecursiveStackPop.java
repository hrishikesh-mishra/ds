package com.hrishikeshmishra.dsjava.stack.exercises;

import com.hrishikeshmishra.dsjava.stack.core.LinkedStack;
import com.hrishikeshmishra.dsjava.stack.core.Stack;

/**
 *
 * R-6.5
 * Give a recursive method for removing all the
 * elements from a stack.
 *
 * Created by hrishikesh.mishra on 11/02/16.
 */
public class RecursiveStackPop {

    private Stack<Integer> stack = new LinkedStack<>();

    public void buildStack(){
        for (int i = 0 ; i<10; i++)
            stack.push(i);
    }

    public void recursiveRemove(){
        if (stack.isEmpty()) return;
        System.out.println("Pop : " + stack.pop());
        recursiveRemove();
    }

    public static void main(String[] args) {
        RecursiveStackPop recursiveStackPop = new RecursiveStackPop();
        recursiveStackPop.buildStack();
        recursiveStackPop.recursiveRemove();
    }
}
