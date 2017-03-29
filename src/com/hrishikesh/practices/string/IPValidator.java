package com.hrishikesh.practices.string;

/**
 * Problem
 * IP Validator
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/ip-validator/
 */
public class IPValidator {

    private static final String IPV4_SEPERATOR = ".";
    private static final String IPV4_SEPERATOR_REGEX = "\\.";
    private static final String IPV6_SEPERATOR = ":";
    private static final String PLUS = "+";
    private static final String MINUS = "-";


    public boolean isValid(String ip) {

        /** Check whether is it IPv4 **/
        if (isIPv4(ip)) {
            return isValidIPV4(ip);
        }
        /** Check whether is it IPv6 **/
        else if (isIPv6(ip)) {
            return isValidIPV6(ip);
        }

        return false;

    }

    private boolean isValidIPV6(String ip) {

        /** Empty blocks at start or end  **/
        if (ip.startsWith(IPV6_SEPERATOR) || ip.endsWith(IPV6_SEPERATOR)) {
            return false;
        }

        String[] blocks = ip.split(IPV6_SEPERATOR);

        /** When number of block not equal to eight **/
        if (blocks.length != 8) {
            return false;
        }

        for (String block : blocks) {

            /** When block is not empty and not hexa number or length of block is greater than 4 **/
            if (block == "" || block.length() > 4 || !isHexaNumber(block)) {
                return false;
            }

            /** If block start with sign **/
            if (isStartWithSign(block)) {
                return false;
            }
        }

        /** Otherwise true **/
        return true;
    }

    private boolean isHexaNumber(String str) {
        try {
            Long.parseLong(str, 16);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;

    }

    private boolean isStartWithSign(String str) {
        return str.startsWith(PLUS) || str.startsWith(MINUS);
    }

    private boolean isValidIPV4(String ip) {

        /** Empty start or end block **/
        if (ip.startsWith(IPV4_SEPERATOR) || ip.endsWith(IPV4_SEPERATOR)) {
            return false;
        }

        String[] blocks = ip.split(IPV4_SEPERATOR_REGEX);

        /** When number of blocks not equal to 4 **/
        if (blocks.length != 4) {
            return false;
        }

        for (String block : blocks) {

            /** When block is null or start with zero **/
            if (block.equals("")) {
                return false;
            }

            Integer intValue = getIntegerValue(block);

            /** When integer value of block is null or less 0 or greater than 255 **/
            if (intValue == null || intValue < 0 || intValue > 255) {
                return false;
            }

            if (block.length() > 1 && block.startsWith("0")) {
                return false;
            }

            if (isStartWithSign(block)) {
                return false;
            }
        }

        /** Otherwise true **/
        return true;
    }

    public static Integer getIntegerValue(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private boolean isIPv4(String ip) {
        return (!ip.contains(IPV6_SEPERATOR) && ip.contains(IPV4_SEPERATOR));
    }

    private boolean isIPv6(String ip) {
        return (ip.contains(IPV6_SEPERATOR) && !ip.contains(IPV4_SEPERATOR));
    }
}


class IPValidatorTest {

    public static void main(String[] args) {
        IPValidator ipValidator = new IPValidator();
        System.out.println("172.16.254.1 ? " + ipValidator.isValid("172.16.254.1"));
        System.out.println("172.16.254.01 ? " + ipValidator.isValid("172.16.254.01"));

        System.out.println("2001:0db8:85a3:0000:0000:8a2e:0370:7334 ? " +
                ipValidator.isValid("2001:0db8:85a3:0000:0000:8a2e:0370:7334"));

        System.out.println("2001:db8:85a3:0:0:8A2E:0370:7334 ? " +
                ipValidator.isValid("2001:db8:85a3:0:0:8A2E:0370:7334"));

        System.out.println("2001:0db8:85a3::8A2E:0370:7334 ? " +
                ipValidator.isValid("2001:0db8:85a3::8A2E:0370:7334"));

        System.out.println("2001:0db8:85a3:0:0:8A2E:0370:7334: ? " +
                ipValidator.isValid("2001:0db8:85a3:0:0:8A2E:0370:7334:"));


    }
}