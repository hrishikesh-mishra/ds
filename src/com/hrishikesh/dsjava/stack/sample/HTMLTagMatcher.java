package com.hrishikesh.dsjava.stack.sample;

import com.hrishikesh.dsjava.stack.core.LinkedStack;
import com.hrishikesh.dsjava.stack.core.Stack;

/**
 * Created by hrishikesh.mishra on 11/02/16.
 */
public class HTMLTagMatcher {

    public static boolean isHTMLTagMatched(String html){
        Stack<String> buffer = new LinkedStack<>();
        int j = html.indexOf('<');
        while (j != -1){
            int k = html.indexOf('>' , j + 1);
            if(k == -1) return false;
            String tag = html.substring(j+1, k);
            if(!tag.startsWith("/")) buffer.push(tag);
            else {
                if(buffer.isEmpty()) return false;
                if(!tag.substring(1).equals(buffer.pop())) return false;
            }
            j = html.indexOf('<', k+1);
        }
        return buffer.isEmpty();
    }

    public static void main(String[] args) {
        String html =   "<body>\n" +
                        "<center>\n" +
                        "<h1> The Little Boat </h1> </center>\n" +
                        "<p> The storm tossed the little boat like a cheap sneaker in an old washing machine. The three drunken fishermen were used to such treatment, of course, but\n" +
                        "not the tree salesman, who even as a stowaway now felt that he\n" +
                        "had overpaid for the voyage. </p> <ol>\n" +
                        "<li> Will the salesman die? </li> <li> What color is the boat? </li> <li> And what about Naomi? </li> </ol>\n" +
                        "</body>";


        System.out.println("Matched ? : " + isHTMLTagMatched(html));
    }
}
