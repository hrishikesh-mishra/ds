package com.hrishikesh.dsjava.recursion.exercises;

/**
 *
 * C-5.20
 *
 * Write a short recursive Java method that rearranges
 * an array of integer values so that all the even values appear before
 * all the odd values.
 *
 * Created by hrishikesh.mishra on 16/01/16.
 */
public class OldEvenArrangement {


    public static void main(String[] args) {
        int [] array = {1, 2, 3, 4, 5, 7, 8, 9, 10};

        array = sortEvenBeforeOdd(array);
        for (int a : array){
            System.out.print(a + " ");
        }
    }

    public static int [] sortEvenBeforeOdd(int [] array){
        return sortEvenBeforeOdd(array, 0);
    }

    public static int [] sortEvenBeforeOdd(int [] array, int pos){
        if(pos >= array.length ) return array;
        int currentItem = array[pos];

        if(isOdd(currentItem)) {
            for (int lastIndex = array.length -1; lastIndex > pos; lastIndex --){
                if(isEven(array[lastIndex])){
                    int temp = array [pos];
                    array [pos] = array [lastIndex];
                    array [lastIndex] = temp;
                    break;
                }
            }
        }
        return sortEvenBeforeOdd(array, pos + 1);
    }

    private static boolean isOdd(int val){
        return !isEven(val);
    }

    private static boolean isEven(int val){
        return val % 2 == 0 ? true : false;
    }
}
