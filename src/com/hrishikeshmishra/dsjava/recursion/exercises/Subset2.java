package com.hrishikeshmishra.dsjava.recursion.exercises;

import java.util.LinkedList;
import java.util.List;

/**
 * C-5.15
 *
 *  Write a recursive method that will output all the
 *  subsets of a set of n elements
 *  (without repeating any subsets).
 *
 * Created by hrishikesh.mishra on 16/01/16.
 */
public class Subset2 {

    public static void main(String[] args) {
        List<Integer> set = new LinkedList<Integer>(){
            {
                add(1);
                add(2);
                add(3);

            }
        };
        System.out.println("power set of {1, 2, 3} : "  + powerset(set, 0));
    }

    public static List<List<Integer>> powerset(List<Integer> set, int index){

        List<List<Integer>> powerset;
        if(index == set.size()){
            powerset = new LinkedList<>();
            powerset.add(new LinkedList<>());
        } else {
            powerset = powerset(set, index + 1);
            int item =  set.get(index);
            List<List<Integer>> moreSet = new LinkedList<>();
            for(List<Integer> subset : powerset){
                List<Integer> newset = new LinkedList<>();
                newset.addAll(subset);
                newset.add(item);
                moreSet.add(newset);
            }
             powerset.addAll(moreSet);
        }

        return powerset;
    }
}
