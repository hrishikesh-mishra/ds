package com.hrishikesh.dsjava.recursion.exercises;

/**
 *
 * C-5.21
 *
 * Given an unsorted array, A, of integers and an integer k, describe a recursive algorithm
 * for rearranging the elements in A so that all elements less than or equal to k come before
 * any elements larger than k. What is the running time of your algorithm on an array of n values?
 *
 * Created by hrishikesh.mishra on 16/01/16.
 */
public class SortNumber {

    public static void main(String[] args) {
        int[] array = {12, 2, 45, 6, 7, 8, 19, 2, 5, 19};
        array = sort(array, 8);

        System.out.print(" K = 8 : [");
        for(int i: array){
            System.out.print(" " + i + ",");
        }
        System.out.println("]");
    }

    private static int [] sort(int [] array,  int k){
        return sort(array, k, 0);
    }

    private static int [] sort(int [] array,  int k, int pos) {
        if(pos >= array.length) return array;

        if(array [pos] > k) {
            for (int lastIndex = array.length -1; lastIndex > pos; lastIndex --){
                if(array[lastIndex] <= k ) {
                    int temp = array[lastIndex];
                    array [lastIndex] = array [pos];
                    array[pos] = temp;
                    break;
                }
            }
        }
        return sort(array, k, pos + 1);
    }
}
