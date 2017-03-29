package com.hrishikesh.dsjava.recursion.exercises;

/**
 *
 * C-5.16
 * In the Towers of Hanoi puzzle, we are given a platform with three pegs, a, b, and c,
 * sticking out of it. On peg a is a stack of n disks, each larger than the next,
 * so that the smallest is on the top and the largest is on the bottom. The puzzle is to move all
 * the disks from peg a to peg c, moving one disk at a time, so that we never place a larger disk
 * on top of a smaller one. See Figure 5.15 for an example of the case n = 4. Describe a recursive
 * algorithm for solving the Towers of Hanoi puzzle for arbitrary n.
 * Created by hrishikesh.mishra on 16/01/16.
 */
public class TowersOfHanoi {

    public static void main(String[] args) {
        moveToTower(5, "A", "C", "B");
    }
    public static void moveToTower(int height, String from , String to, String temp){
        if(height >= 1){
            moveToTower(height - 1, from, temp, to);
            moveDisk(from, to);
            moveToTower(height -1 ,  temp, to, from);
        }
    }

    public static void moveDisk(String from, String to){
        System.out.println("Disk move from: " + from + ", to: " + to);
    }

}
