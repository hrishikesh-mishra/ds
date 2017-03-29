package com.hrishikesh.dsjava.recursion.exercises;

/**
 *
 * C-5.13
 * Give a recursive algorithm to compute the product of two positive integers, m and n,
 * using only addition and subtraction.
 *
 * Created by hrishikesh.mishra on 15/01/16.
 */
public class Multiplication {

    public static void main(String[] args) {
        System.out.println(" 3 X 10 = " + multiply(3, 10 ));
        System.out.println(" -3 X 10 = " + multiply(-3, 10 ));
        System.out.println(" 3 X -10 = " + multiply(3, -10 ));
        System.out.println(" -3 X -10 = " + multiply(-3, -10 ));
    }

    private static int multiply(int x, int  y){
        boolean isPositiveResult = true;
        if(0 > x) isPositiveResult = !isPositiveResult;
        if(0 > y) isPositiveResult = !isPositiveResult;
        return !isPositiveResult? recursiveMultiply(Math.abs(x), Math.abs(y)) * -1 :
                recursiveMultiply(Math.abs(x), Math.abs(y));
    }
    public static int recursiveMultiply(int x, int y){

        if(y == 0)
            return  0;
        else
            return x + recursiveMultiply(x, y - 1 );
    }
}
