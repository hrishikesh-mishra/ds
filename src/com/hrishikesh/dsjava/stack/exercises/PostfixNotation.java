package com.hrishikesh.dsjava.stack.exercises;

import com.hrishikesh.dsjava.stack.core.LinkedStack;
import com.hrishikesh.dsjava.stack.core.Stack;

/**
 *
 * C-6.19
 * Postfix notation is an unambiguous way of writing an arithmetic expression with- out parentheses.
 * It is defined so that if “(exp1) (exp2)” is a normal fully paren- thesized expression whose operation is ,
 * the postfix version of this is “pexp1 pexp2 ”, where pexp1 is the postfix version of exp1 and
 * pexp2 is the postfix ver- sion of exp2. The postfix version of a single number or variable is just that number
 * or variable. So, for example, the postfix version of
 * “((5 + 2) ∗ (8 − 3))/4” is “5 2 + 8 3 − ∗ 4 /”.
 * Describe a nonrecursive way of evaluating an expression in postfix notation.
 *
 * Created by hrishikesh.mishra
 */
public class PostfixNotation {

    private static final char OPEN_BRACKET = '(',
                CLOSE_BRACKET = ')', PLUS_OP = '+',
                MINUS_OP = '-', MULTI_OP = '*', DIVIDE_OP = '/',
                CHAR_A = 'a', CHAR_Z = 'z', NUM_NINE = '9', NUM_ZERO = '0';




    public String InfixToPostfix(String infix){

        Stack<Character> stack = new LinkedStack<>();
        char [] output = new char[infix.length()];
        char ch;
        int  p =0;
        for(int i=0; i < infix.length(); i++){
            ch = infix.charAt(i);
            if(ch == OPEN_BRACKET) stack.push(ch);
            else if(isAlphaNumeric(ch)) output[p++] = ch;
            else if(isOperator(ch));
        }
        return null;
    }

    public static int precedence(char ch) {
         switch (ch){
             case PLUS_OP: case MINUS_OP: return 1;
             case MULTI_OP: case DIVIDE_OP: return 2;
             default: return 0;
         }
    }

    public static boolean isOperator(char ch){
        if(ch == DIVIDE_OP || ch == MULTI_OP || ch == PLUS_OP || ch == MINUS_OP) return true;
        return false;
    }

    public static boolean isAlphaNumeric(char ch){
        if(ch >= CHAR_A && ch <= CHAR_Z || ch >=NUM_ZERO && ch <= NUM_NINE) return true ;
        return false;
    }
}
