package com.hrishikeshmishra.practices.mixed;

import static com.hrishikeshmishra.practices.mixed.MasterMindGame.compute;

/**
 * Problem:
 * Master Mind Game
 * ;
 * The Game of Master Mind is played as follows:
 * The computer has four slots containing balls that are red (R), yellow (Y), green (G) or blue (B).
 * For example, the computer might have RGGB (e g , Slot #1 is red, Slots #2 and #3 are green, Slot #4 is blue)
 * You, the user, are trying to guess the solution You might, for example, guess YRGB When you guess
 * the correct color for the correct slot, you get a “hit” If you guess a color that exists but
 * is in the wrong slot, you get a “pseudo-hit” for example, the guess YRGB has 2 hits and
 * one pseudo hit For each guess, you are told the number of hits and pseudo-hits
 * ;
 * Write a method that, given a guess and a solution, returns the number of hits and pseudo hits.
 * ;
 * ;
 * ;
 * Algorithm:
 * - If both guess and solution are not same length then throw exception.
 * - Iterate both and find hits and create array of that guesses that are not hit.
 * - Now iterate guesses again and count pseudo-hits.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/master-mind-game/
 */
public class MasterMindGame {

    public enum Ball {
        /**
         * Red ball
         **/
        R,

        /**
         * Yellow ball
         **/
        Y,

        /**
         * Green ball
         **/
        G,

        /**
         * Blude
         **/
        B

    }

    public static class Result {

        private int hitCount;
        private int pseudoHitCount;

        public Result(int hitCount, int pseudoHitCount) {
            this.hitCount = hitCount;
            this.pseudoHitCount = pseudoHitCount;
        }

        public Result() {

        }

        public void incrementHit() {
            hitCount++;
        }

        public void incrementPseudoHit() {
            pseudoHitCount++;
        }


        @Override
        public String toString() {
            return "Result {" +
                    "Hit: " + hitCount +
                    ", Pseudo-hit: " + pseudoHitCount +
                    '}';
        }
    }

    public static Result compute(String solution, String guess) {
        if (solution.length() != guess.length()) {
            throw new RuntimeException("Invalid guess or solution.");
        }

        Ball[] solutionBalls = convertToBalls(solution);
        Ball[] guessBalls = convertToBalls(guess);
        Result result = new Result();

        int[] nonHitBalls = new int[Ball.values().length];

        /** Counting hits **/
        for (int index = 0; index < solutionBalls.length; index++) {
            if (guessBalls[index] == solutionBalls[index]) {
                result.incrementHit();
            } else {
                nonHitBalls[solutionBalls[index].ordinal()]++;
            }
        }


        /** Counting Pseudo hits **/
        for (int index = 0; index < guessBalls.length; index++) {

            /** Don't do any thing with hits **/
            if (guessBalls[index] == solutionBalls[index]) {
                continue;
            }

            if (nonHitBalls[guessBalls[index].ordinal()] > 0) {
                result.incrementPseudoHit();
                nonHitBalls[guessBalls[index].ordinal()]--;
            }

        }

        return result;

    }

    private static Ball[] convertToBalls(String str) {
        Ball[] balls = new Ball[str.length()];

        for (int index = 0; index < str.length(); index++) {
            balls[index] = Ball.valueOf(String.valueOf(str.charAt(index)));
        }

        return balls;
    }

}


class MasterMindGameTest {
    public static void main(String[] args) {
        String solution = "RGGB";
        String guess = "YRGB";
        System.out.println("Solution: " + solution);
        System.out.println("Guess: " + guess);
        System.out.println("Result: " + compute(solution, guess));
    }
}