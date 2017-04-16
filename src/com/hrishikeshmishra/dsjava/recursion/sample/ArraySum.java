package com.hrishikeshmishra.dsjava.recursion.sample;

/**
 * Created by hrishikesh.mishra on 01/01/16.
 *
 * <p>
 *     Array Sum using linear recursion.
 * </p>
 */
public class ArraySum {


    public static void main(String[] args) {
        int [] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArraySum arraySum = new ArraySum();
        System.out.println(arraySum.sum(array, array.length));
    }
    public int sum(int array[], int n){
        if(n == 0 ){
            return 0;
        }
        return sum(array, n - 1) + array [n-1];
    }
}
