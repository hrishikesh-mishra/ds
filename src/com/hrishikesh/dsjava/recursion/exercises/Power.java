package com.hrishikesh.dsjava.recursion.exercises;

/**
 * Created by hrishikesh.mishra
 *
 * R 5.8
 *
 * Develop a nonrecursive implementation of the version of the power method from
 * Code Fragment 5.9 that uses repeated squaring.
 *
 */
public class Power {


    public static void main(String[] args) {
        System.out.println(" 2 ^ 4 = " + calculate(2, 4));
        System.out.println(" 2 ^ 5 = " + calculate(2, 5));
    }

    public static double calculate(double v , int x){
        double value = 1;

        for (int i =1; i<= x/2; i++){
            value *= v;
        }

        if(isEven(x)){
            value *= value;
        }else {
            value = value * value * v;
        }

        return value;
    }

    public static boolean isEven(int number){
        return ((number % 2 == 0 )? true : false);
    }
}
