package com.hrishikeshmishra.practices.recursion;

import static com.hrishikeshmishra.practices.recursion.TowersOfHanoi.move;

/**
 * Problem:
 * Towers of Hanoi
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/towers-of-hanoi/
 */
public class TowersOfHanoi {

    public static void move(int n, String source, String buffer, String destination) {
        /** Base case when n <= 0 then don't do anything  **/
        if (n <= 0) {
            return;
        }

        move(n - 1, source, destination, buffer);

        System.out.println("Moving disk from " + source + " to " + destination);

        move(n - 1, buffer, source, destination);
    }

}

class TowersOfHanoiTest {
    public static void main(String[] args) {
        move(3, "A", "B", "C");
    }
}