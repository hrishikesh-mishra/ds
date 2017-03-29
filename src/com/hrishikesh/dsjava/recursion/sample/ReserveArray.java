package com.hrishikesh.dsjava.recursion.sample;

/**
 * Created by hrishikesh.mishra on 01/01/16.
 *
 * Reserve Array using linear recursion
 */
public class ReserveArray {


    public static void main(String[] args) {
        int [] a = {1, 2, 3, 4, 5};
        for(int d: a){
            System.out.print(d + " ");
        }
        reverse(a, 0, a.length -1);
        System.out.println("\nAfter reversing :;");

        for(int d: a){
            System.out.print(d + " ");
        }
    }
    public static void reverse(int [] data, int low, int high ){

        if(low < high){
            int temp  = data[low];
            data[low] = data[high];
            data[high] = temp;
            reverse(data , low + 1, high - 1 );
        }

    }
}
