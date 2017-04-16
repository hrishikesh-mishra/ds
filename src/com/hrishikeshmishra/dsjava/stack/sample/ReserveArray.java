package com.hrishikeshmishra.dsjava.stack.sample;

import com.hrishikeshmishra.dsjava.stack.core.ArrayStack;
import com.hrishikeshmishra.dsjava.stack.core.Stack;

import java.util.Arrays;

/**
 * Created by hrishikesh.mishra on 11/02/16.
 */
public class ReserveArray {

    public static <E> void reserve(E [] a){
        Stack<E> buffer = new ArrayStack<>(a.length);
        for (int i=0; i< a.length; i++) buffer.push(a[i]);
        for (int i =0; i<a.length; i++) a[i] = buffer.pop();
        return;
    }


    public static void main(String[] args) {
        Integer [] a = {4, 8, 12, 24, 221};
        String [] s = {"alan", "turing", "kunuth"};
        System.out.println(" a = " + Arrays.toString(a));
        System.out.println(" s = " + Arrays.toString(s));
        System.out.println("Reversing ... ");
        reserve(a);
        reserve(s);
        System.out.println(" a = " + Arrays.toString(a));
        System.out.println(" s = " + Arrays.toString(s));
    }
}
