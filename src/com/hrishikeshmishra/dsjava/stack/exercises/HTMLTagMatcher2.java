package com.hrishikeshmishra.dsjava.stack.exercises;

import com.hrishikeshmishra.dsjava.stack.core.LinkedStack;
import com.hrishikeshmishra.dsjava.stack.core.Stack;

/**
 * Problem:
 * In Code Fragment 6.8 we assume that opening tags in HTML have form <name>, as with <li>.
 * More generally, HTML allows optional attributes to be expressed as part of an opening tag.
 * The general form used for expressing an attribute is <name attribute1="value1" attribute2="value2">;
 * for example, a ta- ble can be given a border and additional padding by using an opening tag of
 * <table border="3" cellpadding="5">. Modify Code Fragment 6.8 so that it can properly match tags,
 * even when an opening tag may include one or more such attributes.
 * <p>
 * Created by hrishikesh.mishra on 11/02/16.
 */
public class HTMLTagMatcher2 {

    public static boolean isTagMatched(String html) {
        Stack<String> buffer = new LinkedStack<>();
        int j = html.indexOf('<');
        while (j != -1) {
            int k = html.indexOf('>', j + 1); /** check for closing tag index **/
            if (k == -1) return false; /** Tag is not properly closed **/

            String tag = html.substring(j + 1, k);

            /** tag attribute not present **/
            if (tag.indexOf(' ') != -1) tag = tag.substring(0, tag.indexOf(' '));

            if (!tag.startsWith("/")) { /** opening tag, pushed to stack **/
                buffer.push(tag);
            } else { /** closing tag **/
                if (buffer.isEmpty()) return false; /** tag was not opened properly **/
                if (!tag.substring(1).equals(buffer.pop())) return false;
            }
            j = html.indexOf('<', k + 1);
        }
        return buffer.isEmpty();
    }

    public static void main(String[] args) {

        String html = "<body>\n" +
                "<center>\n" +
                "<h1>The Little Boat </h1> </center>\n" +
                "<p border=\"3\" cellpadding=\"5\"> The storm tossed the little boat like a cheap sneaker in an " +
                "old washing machine. The three drunken fishermen were used to such treatment, of course, but\n" +
                "not the tree salesman, who even as a stowaway now felt that he\n" +
                "had overpaid for the voyage. " +
                "</p>" +
                "<ol>\n" +
                "<li> Will the salesman die? </li> " +
                "<li> What color is the boat? </li> " +
                "<li> And what about Naomi? </li> " +
                "</ol>\n" +
                "</body>";


        System.out.println("Is valid : " + isTagMatched(html));
    }
}
