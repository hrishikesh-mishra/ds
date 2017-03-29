package com.hrishikesh.practices.recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.hrishikesh.practices.recursion.LongestTower.createLongestTower;

/**
 * Problem:
 * Longest tower
 * ;
 * In human tower, people standing atop of one another's shoulders, with constraints, a person on top of another must
 * be lighter and and shorter of below person. Now you have weight and height of each person, compute largest possible
 * tower from given persons details.
 * ;
 * ;
 * Solution:
 * - A simple solution is based on longest contiguous subsequence
 * ;
 * ;
 * Algorithm:
 * - First sort the given persons based on height and then weight
 * - Iterate each one by one and create list of longest contiguous subsequence list till this index
 * - Find longest list from all longest contiguous subsequence list
 *
 * @author hrishikesh.mishra
 * @stackoverflow http://stackoverflow.com/questions/4424586/find-the-largest-possible-number-of-people-in-such-a-tower
 * @link http://hrishikeshmishra.com/longest-tower/
 */
public class LongestTower {

    public static class Person implements Comparable<Person> {
        private int height;
        private int weight;

        public Person(int height, int weight) {
            this.height = height;
            this.weight = weight;
        }

        @Override
        public int compareTo(Person o) {
            /** Compare first height and weight **/
            if (this.height != o.height) {
                return Integer.compare(this.height, o.height);
            } else {
                return Integer.compare(this.weight, o.weight);
            }
        }

        public boolean canAppend(Person o) {
            return (this.height < o.height && this.weight < o.weight);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "h=" + height +
                    ", w=" + weight +
                    '}';
        }
    }

    public static List<Person> createLongestTower(List<Person> persons) {
        Collections.sort(persons);

        /** To hold longest sub-sequence for each index **/
        List<List<Person>> solutions = new ArrayList<>();
        List<Person> longestTower = null;

        for (int i = 0; i < persons.size(); i++) {
            List<Person> longestTowerAtIndex = longestTowerAtIndex(persons, solutions, i);
            solutions.add(i, longestTowerAtIndex);
            longestTower = max(longestTowerAtIndex, longestTower);
        }

        return longestTower;
    }

    private static List<Person> longestTowerAtIndex(List<Person> persons, List<List<Person>> solutions, int index) {
        Person person = persons.get(index);
        List<Person> longestSequence = new ArrayList<>();

        for (int i = 0; i < index; i++) {
            List<Person> solution = solutions.get(i);
            if (canAppend(solution, person)) {
                longestSequence = max(solution, longestSequence);
            }
        }


        List<Person> longest = (List<Person>) ((ArrayList<Person>) longestSequence).clone();
        longest.add(person);

        return longest;
    }

    private static List<Person> max(List<Person> sequence1, List<Person> sequence2) {
        if (sequence1 == null) {
            return sequence2;
        }

        if (sequence2 == null) {
            return sequence1;
        }

        return sequence1.size() > sequence2.size() ? sequence1 : sequence2;
    }

    private static boolean canAppend(List<Person> solution, Person person) {
        if (Objects.isNull(solution)) {
            return false;
        }

        if (solution.size() == 0) {
            return false;
        }

        Person lastPerson = solution.get(solution.size() - 1);
        return lastPerson.canAppend(person);
    }

}


class CircusTowerTest {
    public static void main(String[] args) {
        List<LongestTower.Person> persons = new ArrayList<LongestTower.Person>() {
            {
                add(new LongestTower.Person(65, 100));
                add(new LongestTower.Person(70, 150));
                add(new LongestTower.Person(56, 90));
                add(new LongestTower.Person(75, 190));
                add(new LongestTower.Person(60, 95));
                add(new LongestTower.Person(68, 110));
            }
        };

        System.out.println("Persons: " + persons);
        System.out.println("Longest Tower: " + createLongestTower(persons));
    }
}