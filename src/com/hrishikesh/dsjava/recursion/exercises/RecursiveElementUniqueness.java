package com.hrishikesh.dsjava.recursion.exercises;

/**
 * Created by hrishikesh.mishra on 05/01/16.
 *
 * C-5.12
 * Describe an efficient recursive algorithm for solving the element uniqueness problem,
 * which runs in time that is at most O(n2) in the worst case without using sorting.

 */
public class RecursiveElementUniqueness {

    public static void main(String[] args) {
        int [] x = {1, 2, 3, 5, 6, 7 , 4, 2, 10, 17};
        System.out.println("isUnique: " + isUnique(x));
    }

    public static boolean isUnique(int [] list){
        return unique(list, 0, list.length);
    }
    public static boolean unique(int [] list, int start, int end){
        if(start == end) return true;
        int firstElement = list[start];
        for(int x = start + 1; x < end; x++ ) {
            if (firstElement == list[x]) return false;
        }
        return unique(list, start + 1, end);
    }
}
