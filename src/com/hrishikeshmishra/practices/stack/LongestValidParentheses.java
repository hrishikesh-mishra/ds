package com.hrishikeshmishra.practices.stack;

/**
 * <p>
 * Problem:
 * Longest valid Parentheses
 * Given a string consisting of opening and closing parenthesis,
 * find length of the longest valid parenthesis substring.
 * ;
 * Examples:
 * ;
 * Input : ((()
 * Output : 2
 * Explanation : ()
 * ;
 * Input: )()())
 * Output : 4
 * Explanation: ()()
 * ;
 * Input:  ()(()))))
 * Output: 6
 * Explanation:  ()(()))
 * ;
 * ;
 * Solution:
 * - We will stack to maintain index of all opening brackets "(".
 * - When we encounter "(" we'll push index of this in stack
 * - When we encounter ")" then there could be there possible cases
 * - - Case 1. Stack is already empty
 * - - - means all valid parenthesis already consumed and after this new start
 * - - Case 2. After pop element from stack, it becomes empty
 * - - Case 3. And even after poping from stack is not empty.
 * ;
 * ;
 * Algorithm:
 * - Create empty stack
 * - Set maxLen = 0, last = -1
 * - Iterate parenthesis from start to end
 * - - If currentCharacter is "(" then
 * - - - stack.push(i)
 * - - Else
 * - - - If stack is empty then,
 * - - - - last = i
 * - - - Else
 * - - - - stack.pop
 * - - - - If stack is empty then,
 * - - - - - maxLen = Math.max(maxLen, i - last);
 * - - - - Else
 * - - - - - maxLen = Math.max(maxLen, i - stack.peek());
 * - Return maxLen
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/longest-valid-parentheses/
 */
public class LongestValidParentheses {

    public static int find(String parenthesis) {

        /** Stack to hold opening characters **/
        Stack<Integer> stack = new Stack<>();
        int maxLen = 0, last = -1;

        /** Iterate all character one by one **/
        for (int i = 0; i < parenthesis.length(); i++) {

            /** Get current character from string of parenthesis **/
            char currentCharacter = parenthesis.charAt(i);

            /** If current character is opening bracket ( push to stack **/
            if (currentCharacter == '(') {
                stack.push(i);
            } else {
                /**
                 * When we currentCharacter == ')' then
                 * there could be there 3 possible scenarios
                 *   - 1. Stack is already empty
                 *   - 2. After pop element from stack, it becomes empty
                 *   - 3. And even after poping from stack is not empty.
                 */

                /** **/
                /**
                 * When stack was already empty, means all valid parenthesis already consumed and
                 * after this new start
                 */
                if (stack.isEmpty()) {
                    last = i;
                } else {
                    stack.pop();

                    /** After popping element stack become empty **/
                    if (stack.isEmpty()) {
                        maxLen = Math.max(maxLen, i - last);
                    } else {

                        /** After popping element stack still not empty **/
                        maxLen = Math.max(maxLen, i - stack.peek());
                    }
                }
            }
        }

        return maxLen;

    }
}

class LongestValidParenthesesTest {
    public static void main(String[] args) {
        String p1 = "((()",
                p2 = ")()())()()()()",
                p3 = "()(()))))",
                p4 = ")()())",
                p5 = "()()()",
                p6 = "",
                p7 = "((((",
                p8 = "(((()",
                p9 = "(((())(",
                p10 = "()(()",
                p11 = "()(())";

        System.out.println("Longest Valid Parentheses of " + p1 + " :" + LongestValidParentheses.find(p1));
        System.out.println("Longest Valid Parentheses of " + p2 + " :" + LongestValidParentheses.find(p2));
        System.out.println("Longest Valid Parentheses of " + p3 + " :" + LongestValidParentheses.find(p3));
        System.out.println("Longest Valid Parentheses of " + p4 + " :" + LongestValidParentheses.find(p4));
        System.out.println("Longest Valid Parentheses of " + p5 + " :" + LongestValidParentheses.find(p5));
        System.out.println("Longest Valid Parentheses of " + p6 + " :" + LongestValidParentheses.find(p6));
        System.out.println("Longest Valid Parentheses of " + p7 + " :" + LongestValidParentheses.find(p7));
        System.out.println("Longest Valid Parentheses of " + p8 + " :" + LongestValidParentheses.find(p8));
        System.out.println("Longest Valid Parentheses of " + p9 + " :" + LongestValidParentheses.find(p9));
        System.out.println("Longest Valid Parentheses of " + p10 + " :" + LongestValidParentheses.find(p10));
        System.out.println("Longest Valid Parentheses of " + p11 + " :" + LongestValidParentheses.find(p11));


    }
}
