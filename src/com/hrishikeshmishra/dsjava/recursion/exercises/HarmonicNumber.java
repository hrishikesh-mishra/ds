package com.hrishikeshmishra.dsjava.recursion.exercises;

/**
 *
 *  Created by hrishikesh.mishra on 04/01/16.
 *
 * R-5.7
 *
 * Describe a recursive algorithm
 * for computing the nth Harmonic number,
 * defined as Hn = ∑ n k=1 1/k.
 *
 */
public class HarmonicNumber {


    public static void main(String[] args) {

        System.out.println("Sum up to 1: "  + sum(1));
        System.out.println("Sum up to 2: "  + sum(2));
        System.out.println("Sum up to 3: "  + sum(3));
        System.out.println("Sum up to 4: "  + sum(4));
    }


    /**
     * Summation with recursive method.
     * @param n
     * @return
     */
    public static double sum(int n){
        if(n <= 1)
            return 1;
        else
            return ((double) 1/n) + sum(n - 1);
    }
}
