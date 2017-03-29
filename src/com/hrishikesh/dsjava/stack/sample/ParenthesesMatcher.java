package com.hrishikesh.dsjava.stack.sample;

import com.hrishikesh.dsjava.stack.core.LinkedStack;
import com.hrishikesh.dsjava.stack.core.Stack;

/**
 * Created by hrishikesh.mishra on 11/02/16.
 */
public class ParenthesesMatcher {

    public static boolean isMatched(String expression){
        final String opening = "({[", closing = ")}]";
        Stack<Character> buffer = new LinkedStack<>();

        for(char c: expression.toCharArray()){
            if(opening.indexOf(c) != -1) {
                buffer.push(c);
            } else if (closing.indexOf(c) != -1){
                if(buffer.isEmpty()) return false;
                if(closing.indexOf(c) != opening.indexOf(buffer.pop())) return false;
            }
        }
        return buffer.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("is valid : [(5+x)−(y+z)]. = " + isMatched("[(5+x)−(y+z)]."));
        System.out.println("is valid : [(5+x)−{(y+z)]. = " + isMatched("[(5+x)−{(y+z)]."));
    }
}
