package com.hrishikeshmishra.dsjava.recursion.exercises;

/**
 * C-5.15
 *
 *  Write a recursive method that will output all the
 *  subsets of a set of n elements
 *  (without repeating any subsets).
 *
 * Created by hrishikesh.mishra on 15/01/16.
 */
public class Subset {

    public static void main(String[] args) {
        createSubset(4);
    }
    public static void createSubset(int n){
        boolean [] subset = new boolean[n];
        recursiveSubsetCreation(n, subset);
    }

    private static void recursiveSubsetCreation(int n, boolean[] subset) {
        /** Base case **/
        if(n == 0){
            printSubset(subset);
            return;
        }

        /** First generate all subset containing n **/
        subset[n - 1] = true;
        recursiveSubsetCreation(n - 1, subset);

        /** Then, generate all subset not containing n **/
        subset[n - 1] = false;
        recursiveSubsetCreation(n - 1, subset);
    }

    private static void printSubset(boolean[] subset) {
        if(isEmptySet(subset)){
            System.out.println("{}");
            return;
        }

        System.out.print("{ ");
        for(int i=0; i< subset.length ; i++)
            if(subset[i])
                System.out.print(i + 1 + ", ");

        System.out.println("}");

    }

    private static boolean isEmptySet(boolean[] subset) {
        for(int i=0; i< subset.length; i++) if(subset[i]) return false;
        return true;
    }
}

