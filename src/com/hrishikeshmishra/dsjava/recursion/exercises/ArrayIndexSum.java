package com.hrishikeshmishra.dsjava.recursion.exercises;

/**
 *
 * C-5.23
 *
 * Describe a recursive algorithm that will check if an array A of integers
 * contains an integer A[i] that is the sum of two integers that appear earlier in A,
 * that is, such that A[i] = A[j]+A[k] for j,k < i.
 *
 * Created by hrishikesh.mishra on 17/01/16.
 */
public class ArrayIndexSum {

    public static void main(String[] args) {
        int [] a = {1, 2, 5, 8, 21, 3, 4};
        check(a);
    }

    public static boolean check(int [] a){
        if(a.length <=2) return false;
        return check(a, 2, 1, 0);
    }

    private static boolean check (int []a, int i, int j, int k){
        if(a[k] + a[j] == a[i]) {
            System.out.println("Number found, and the number are : " + a[i] + " + " + a[j] + " = " + a[k]);
            return true;
        }
        else {
            if(k + 1 < j) return check(a, i, j, k + 1 );
            else if(j + 1 < i) return check(a, i, j + 1, 0 );
            else if(i + 1 < a.length -1) return check(a, i + 1, 1, 0 );
            else return false;
        }
    }
}
