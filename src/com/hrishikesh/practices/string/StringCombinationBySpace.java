package com.hrishikesh.practices.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem:
 * Generate possible strings that can be made by placing spaces.
 *
 * @author hrishikesh.mishra
 */
public class StringCombinationBySpace {

    private static final String SEPARATOR = " ";


    public static List<String> generate(String str) {
        List<String> result = new ArrayList<>();
        result.add(str);
        stringGeneratorHelper(str, result, "");
        return result;
    }

    private static void stringGeneratorHelper(String str, List<String> result, String prefix) {
        /** Bounder Condition **/
        if (str.equals("")) {
            return;
        }

        for (int i = 1; i < str.length(); i++) {
            String newPrefix = prefix + str.substring(0, i) + SEPARATOR;
            result.add(newPrefix + str.substring(i));
            stringGeneratorHelper(str.substring(i), result, newPrefix);


        }
    }

}


class StringCombinationBySpaceTest {
    public static void main(String[] args) {
        String str1 = "ABCD";
        String str2 = "ABC";
        System.out.printf("String : %s, Space String : %s\n", str1, StringCombinationBySpace.generate(str1));
        System.out.printf("String : %s, Space String : %s\n", str2, StringCombinationBySpace.generate(str2));
    }
}