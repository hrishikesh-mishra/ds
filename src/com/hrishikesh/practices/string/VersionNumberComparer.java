package com.hrishikesh.practices.string;

import static com.hrishikesh.practices.string.VersionNumberComparer.compare;

/**
 * Problem
 * Compare Version Numbers
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/compare-version-numbers/
 */
public class VersionNumberComparer {

    public static int compare(String version1, String version2) {

        String version1Array[] = version1.split("\\.");
        String version2Array[] = version2.split("\\.");

        int i = 0, j = 0;

        for (; i < version1Array.length && j < version2Array.length; j++, i++) {
            int first = getInt(version1Array[i]);
            int second = getInt(version2Array[i]);
            if (first != second) {
                return first > second ? 1 : -1;
            }
        }

        if (i == version1Array.length && j == version2Array.length) {
            return 0;
        } else if (i == version1Array.length && j < version2Array.length) {
            return isAllZero(version2Array, j) ? 0 : -1;
        } else {
            return isAllZero(version1Array, i) ? 0 : 1;
        }

    }

    private static boolean isAllZero(String versionArray[], int i) {

        while (i < versionArray.length) {

            if (getInt(versionArray[i++]) > 0) {
                return false;
            }
        }

        return true;
    }

    private static int getInt(String s) {
        return Integer.parseInt(s);
    }
}


class VersionNumberComparerTest {

    public static void main(String[] args) {
        String s1V1 = "1";
        String s1V2 = "0";

        String s2V1 = "1.0";
        String s2V2 = "1";

        String s3V1 = "1.1";
        String s3V2 = "1";


        System.out.printf("Version1 : %s, Version2: %s, Status: %d\n", s1V1, s1V2, compare(s1V1, s1V2));
        System.out.printf("Version1 : %s, Version2: %s, Status: %d\n", s2V1, s2V2, compare(s2V1, s2V2));
        System.out.printf("Version1 : %s, Version2: %s, Status: %d\n", s3V1, s3V2, compare(s3V1, s3V2));

    }


}