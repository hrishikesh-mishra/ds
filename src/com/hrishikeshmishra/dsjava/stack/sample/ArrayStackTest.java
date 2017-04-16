package com.hrishikeshmishra.dsjava.stack.sample;

import com.hrishikeshmishra.dsjava.stack.core.ArrayStack;
import com.hrishikeshmishra.dsjava.stack.core.Stack;

import java.util.Arrays;

/**
 * Created by hrishikesh.mishra on 17/01/16.
 */
public class ArrayStackTest {

    public static void main(String[] args) {
        Integer [] a = {1, 2, 3, 5, 6, 7};
        String [] s = {"ram", "mohan", "sohan", "jin"};
        System.out.println(" a = " + Arrays.toString(a));
        System.out.println(" s = " + Arrays.toString(s));
        System.out.println("Reversing .... ");
        reverse(a);
        reverse(s);
        System.out.println(" a = " + Arrays.toString(a));
        System.out.println(" s = " + Arrays.toString(s));
    }
    public static <E> void reverse(E [] a){
        Stack<E> buffer = new ArrayStack<>(a.length);
        for (int i=0; i<a.length; i++) buffer.push(a[i]);
        for (int i=0; i<a.length; i++) a[i] = buffer.pop();
    }


}
