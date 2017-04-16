package com.hrishikeshmishra.practices.dynamicprogramming;

import java.util.Arrays;
import java.util.List;

import static com.hrishikeshmishra.practices.dynamicprogramming.TextJustification.compute;

/**
 * Problem:
 * Text Justification
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/text-justification/
 */
public class TextJustification {

    public static class Result {
        private String text;
        private long cost;

        public Result(String text, long cost) {
            this.text = text;
            this.cost = cost;
        }

        public String getText() {
            return text;
        }

        public long getCost() {
            return cost;
        }
    }

    public static Result compute(List<String> words, int maxLength) {
        return compute(words, 0, maxLength, 0);
    }


    private static Result compute(List<String> words, int startIndex, int maxLength, int index) {

        /** Base case: when all words consumed **/
        if (index >= words.size()) {
            return new Result("", 0);
        }

        int endIndex = words.get(index).length() + startIndex;

        /** When word cannot fit in one line  **/
        if (endIndex > maxLength) {
            return new Result("", Long.MAX_VALUE);
        }

        /** Try to put current word in same line  **/
        Result r1 = compute(words, endIndex + 1, maxLength, index + 1);
        r1.text = words.get(index) + " " + r1.text;

        /** Try to put current word in different line  **/
        Result r2 = compute(words, 0, maxLength, index + 1);
        r2.text = words.get(index) + "\n" + r2.text;

        /** When line got changed then, compute cost **/
        r2.cost += getBadness(maxLength - endIndex);

        /** Return result that has least cost **/
        return (r1.cost < r2.cost) ? r1 : r2;

    }

    private static long getBadness(long value) {
        return value * value * value;
    }


}

class TestJustificationTest {
    public static void main(String[] args) {
        String sentence = "This may be a difficult example";
        List<String> words = Arrays.asList(sentence.split(" "));
        TextJustification.Result result = compute(words, 13);

        System.out.println(result.getText());
        System.out.println("Cost: " + result.getCost());

    }
}
