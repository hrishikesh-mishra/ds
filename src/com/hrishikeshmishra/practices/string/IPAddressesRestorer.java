package com.hrishikeshmishra.practices.string;

import java.util.ArrayList;
import java.util.List;

import static com.hrishikeshmishra.practices.string.IPAddressesRestorer.restore;

/**
 * Problem
 * Restore Valid Ip Address from String
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/restore-valid-ip-address-string/
 */
public class IPAddressesRestorer {

    public static List<String> restore(String s) {
        List<String> validIps = new ArrayList<>();
        restoreHelper(s, 0, "", 0, validIps);
        return validIps;
    }

    private static void restoreHelper(String originalStr, int start, String ip, int blockNumber, List<String> ips) {

        /** Base case: When start index reached end **/
        if (start >= originalStr.length()) {
            return;
        }

        /** Base case: When previous next block created and its block **/
        if (blockNumber == 3) {
            /** Get last block **/
            String block = originalStr.substring(start);

            /** After validation add ip to ips list **/
            if (!block.equals("") && isValidIpBlock(block)) {
                ips.add(ip.concat(".").concat(block));
            }
            return;
        }

        /** Consume all character one by one of original character **/
        for (int index = start; index < originalStr.length(); index++) {

            /** Current block **/
            String block = originalStr.substring(start, index + 1);

            /** Remaining blocks **/
            int remainingBlocks = 4 - blockNumber - 1;
            /** Remaining characters **/
            int remainingSize = originalStr.length() - index - 1;

            /**
             * Validate all constraints for a block like
             * - block value must be in 0 to 255
             * - block value must not prefix with zero like 010 is invalid ip block
             * - block value must not have more than one zero like 00 is invalid ip block
             */
            if (isValid(remainingBlocks, remainingSize) && isValidIpBlock(block)) {

                String newCurrent = ip.equals("") ? block : ip.concat(".").concat(block);
                restoreHelper(originalStr, index + 1, newCurrent, blockNumber + 1, ips);
            }
        }
    }

    private static boolean isValidIpBlockRange(String block) {
        try {
            int blockValue = Integer.parseInt(block);
            return blockValue >= 0 && blockValue <= 255;
        } catch (Exception e) {
            return false;
        }

    }

    private static boolean isValid(int remainingBlocks, int remainingSize) {
        int minSize = remainingBlocks * 1;
        int maxSize = remainingBlocks * 3;
        return remainingSize >= minSize && remainingSize <= maxSize;
    }

    private static boolean isPrefixWithZero(String block) {
        int blockValue = Integer.parseInt(block);
        return block.startsWith("0") && blockValue > 0;
    }

    private static boolean isMoreThanOneZero(String string) {
        return string.equals("00") || string.equals("000");
    }

    private static boolean isValidIpBlock(String block) {
        return isValidIpBlockRange(block) &&
                !isPrefixWithZero(block) &&
                !isMoreThanOneZero(block);
    }
}

class IPAddressesRestorerTest {
    public static void main(String[] args) {

        String str = "25525511135";
        String str2 = "010010";

        System.out.printf("String: %s, ips: %s\n", str, restore(str));
        System.out.printf("String: %s, ips: %s\n", str2, restore(str2));
    }
}
