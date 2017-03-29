package com.hrishikesh.practices.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

/**
 * Problem:
 * Insert Remove And Random in O(1)
 * Design a data structure that will do insert, remove and random operation in O(1)
 * and duplicate number allowed.
 * ;
 * ;
 * Solution:
 * - Take two data structure
 * - - List - To all numbers one by one (including duplicates)
 * - - Map - To hold given number and their location of list DS
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/insert-remove-random-o1/
 */
public class InsertRemoveAndRandomInO1 {

    private Map<Integer, Set<Integer>> numberAndIndices;
    private List<Integer> numbers;
    private Random random;

    public InsertRemoveAndRandomInO1() {
        this.numberAndIndices = new HashMap<>();
        this.numbers = new ArrayList<>();
        this.random = new Random();
    }


    public boolean insert(int value) {
        int newIndex = size();
        numbers.add(value);

        /** If number already exists **/
        if (numberAndIndices.containsKey(value)) {
            numberAndIndices.get(value).add(newIndex);
            return false;
        }
        /** If number does not exists **/
        else {
            Set<Integer> indices = new HashSet<>();
            indices.add(newIndex);
            numberAndIndices.put(value, indices);
            return true;
        }
    }


    public boolean remove(int value) {
        /** If number present in DS **/
        if (numberAndIndices.containsKey(value)) {

            Set<Integer> indices = numberAndIndices.get(value);
            int removedItemPosition = indices.iterator().next();

            /** Removing from set **/
            indices.remove(removedItemPosition);

            /** In case of no duplicate element **/
            if (indices.isEmpty()) {
                numberAndIndices.remove(value);
            }

            /** Remove one element from all numbers list **/
            /** Remove last element **/
            int lastElement = numbers.remove(size() - 1);

            /** If currently removed item was not last item add in numbers then, update indiecs **/
            if (removedItemPosition != size()) {

                numbers.set(removedItemPosition, lastElement);

                Set<Integer> indicesToUpdate = numberAndIndices.get(lastElement);
                indicesToUpdate.remove(size());
                indicesToUpdate.add(removedItemPosition);

            }
            return true;
        }
        /** If number doesn't exist in DS **/
        else {
            return false;
        }

    }


    public int getRandom() {
        if (size() == 0) {
            throw new NoSuchElementException("DS is empty");
        }

        int size = size();
        int randIndex = random.nextInt(size);
        return numbers.get(randIndex);
    }

    private int size() {
        return numbers.size();
    }

}


class InsertRemoveAndRandomInO1Test {
    public static void main(String[] args) {
        InsertRemoveAndRandomInO1 newDS = new InsertRemoveAndRandomInO1();
        System.out.println("Insert 1 : " + newDS.insert(1));
        System.out.println("Insert 1 : " + newDS.insert(1));
        System.out.println("Insert 2 : " + newDS.insert(2));
        System.out.println("Random : " + newDS.getRandom());
        System.out.println("Remove 1 : " + newDS.remove(1));
        System.out.println("Random : " + newDS.getRandom());
    }
}