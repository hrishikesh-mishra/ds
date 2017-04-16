package com.hrishikeshmishra.practices.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * Problem
 * Mini Parser
 * Given a nested list of integers represented as a string, implement a parser to deserialize it.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/mini-parser/
 */
public class NestedMiniParser {

    private static final char LIST_START_CHAR = '[';
    private static final char LIST_END_CHAR = ']';
    private static final char LIST_SEPERATOR_CHAR = ',';

    public NestedInteger parase(String s) {

        /** When object is null **/
        if (Objects.isNull(s)) {
            return null;
        }

        /** When object has single value **/
        if (!isList(s)) {
            return new NestedInteger(getIntValue(s));
        }

        /** Stack to hold current processing list **/
        Stack<NestedInteger> stack = new Stack<>();

        NestedInteger nestedInteger = new NestedInteger();
        stack.push(nestedInteger);

        int numberStartIndex = 1;

        /** Iterating all character one by one from 1 **/
        for (int i = 1; i < s.length(); i++) {

            /** Current character **/
            char currentChar = s.charAt(i);

            /** If current current is starting character of list **/
            if (currentChar == LIST_START_CHAR) {

                /** New list, so creating new object to old list **/
                NestedInteger newList = new NestedInteger();

                /** Add current new to previous list **/
                stack.peek().add(newList);

                /** Pushing list to stack  **/
                stack.push(newList);

                /** Updating index for next integer **/
                numberStartIndex = i + 1;

            }
            /** When current character is last list character or list seperator **/
            else if (currentChar == LIST_SEPERATOR_CHAR || currentChar == LIST_END_CHAR) {

                /** When previous character was not list closing character **/
                if (i > numberStartIndex) {
                    String number = s.substring(numberStartIndex, i);
                    stack.peek().add(new NestedInteger(getIntValue(number)));
                }

                /** Updating index for next integer **/
                numberStartIndex = i + 1;

                /** When current character is list closing character **/
                if (currentChar == LIST_END_CHAR) {
                    stack.pop();
                }
            }
        }


        return nestedInteger;
    }


    private boolean isList(String s) {
        return s.charAt(0) == LIST_START_CHAR;
    }

    private int getIntValue(String s) {
        return Integer.parseInt(s);
    }


    public static class NestedInteger {
        private int value;
        private boolean integer;
        private List<NestedInteger> list;


        public NestedInteger() {
            list = new ArrayList<>();
        }

        public NestedInteger(int value) {
            this.value = value;
            list = new ArrayList<>();
        }

        public int getInteger() {
            return value;
        }

        public void setInteger(int value) {
            this.value = value;
        }

        public boolean isInteger() {
            return integer;
        }

        public void setInteger(boolean integer) {
            this.integer = integer;
        }

        public void add(NestedInteger ni) {
            list.add(ni);
        }

        public List<NestedInteger> getList() {
            return list;
        }

        @Override
        public String toString() {
            return "NestedInteger{" +
                    "value=" + value +
                    ", list=" + list +
                    '}';
        }
    }
}


class NestedMiniParserTest {
    public static void main(String[] args) {
        NestedMiniParser parser = new NestedMiniParser();

        String str = "[123,[456,[789]]]";
        System.out.println("String : " + str);
        System.out.println("Parsed: " + parser.parase(str));

    }
}