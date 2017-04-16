package com.hrishikeshmishra.practices.string;

/**
 * Problem:
 * Alphabet and Digit finder in String
 * Given a string S, print the number of digits and number of english alphabets in it.
 *
 * @author hrishikesh.mishra
 */
public class StringAlphabetAndDigitFinder {

    public static class Result {
        int numberOfDigit;
        int numberOfAlphabet;

        public Result(int numberOfDigit, int numberOfAlphabet) {
            this.numberOfDigit = numberOfDigit;
            this.numberOfAlphabet = numberOfAlphabet;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "numberOfDigit=" + numberOfDigit +
                    ", numberOfAlphabet=" + numberOfAlphabet +
                    '}';
        }
    }

    public static Result find(String s) {

        int totalDigit = 0, totalAlphabet = 0;

        for (char c : s.toCharArray()) {

            if (Character.isAlphabetic(c)) {
                totalAlphabet++;
            } else if (Character.isDigit(c)) {
                totalDigit++;
            }
        }

        return new Result(totalDigit, totalAlphabet);
    }
}


class StringAlphabetAndDigitFinderTest {
    public static void main(String[] args) {
        String str = "p6$&65&%NEf";

        System.out.printf("String : %s , %s", str, StringAlphabetAndDigitFinder.find(str));
    }
}