package com.hrishikesh.dsjava.recursion.exercises;

import java.util.Arrays;

/**
 *
 * C-5.22
 *
 * Suppose you are given an array, A, containing n distinct integers that are listed in increasing order.
 * Given a number k, describe a recursive algorithm to find two integers in A that sum to k,
 * if such a pair exists. What is the running time of your algorithm?
 *
 * Created by hrishikesh.mishra on 17/01/16.
 */
public class NumberFinder {

    public static void main(String[] args) {
        int [] a = {1, 2, 3, 5, 4, 19, 21, 13 };
        Arrays.sort(a);
        System.out.println("Find k = 4 ?  " + find(a, 4));
        System.out.println("Find k = 20 ?  " + find(a, 20));
        System.out.println("Find k = 53 ?  " + find(a, 53));
     }

    public static boolean find(int a[], int k){
        return find(a, 0, a.length - 1, k);
    }

    private static boolean find(int a[], int start, int end , int k){
        /** base case **/
        if(start >= end ) return false;

        if(a[start] + a[end] < k) {
            return find(a, start + 1, end, k);
        } else {
            if (a[start] + a[end] > k) {
                return find(a, start, end - 1, k);
            }
            else {
                return true;
            }
        }
    }
}
