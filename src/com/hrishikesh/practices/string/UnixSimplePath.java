package com.hrishikesh.practices.string;

import java.util.Stack;

/**
 * Problem:
 * Simplify Unix Path
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/simplify-unix-path/
 */
public class UnixSimplePath {

    private static final String CURRENT_DIRECTORY = ".";
    private static final String PARENT_DIRECTORY = "..";

    public static String get(String path) {

        /** Base case: When path is null or empty **/
        if (path == null || path.length() == 0) {
            return path;
        }

        /** Extract all directories from path **/
        String[] directories = path.split("/");

        /** Stack to hold directory **/
        Stack<String> stack = new Stack<>();

        /** If absolute path **/
        if (path.startsWith("/")) {
            stack.push("/");
        }

        /** Iterating directories one by one **/
        for (String directory : directories) {

            /** When directory is empty or its a current directory (.) then, don't do anything **/
            if (isEmpty(directory) || isCurrentDirectory(directory)) {
                continue;
            }
            /** When directory is parent directory **/
            else if (isParentDirectory(directory)) {

                /** Hack to handle these cases : /../../../ **/
                if (!stack.peek().equals("/")) {

                    /** Pop directory **/
                    stack.pop();
                }

            }
            /** Else push current directory to stack **/
            else {
                stack.push(directory);
            }
        }

        return createPathFromStack(stack);
    }

    private static boolean isCurrentDirectory(String directory) {
        return directory.equals(CURRENT_DIRECTORY);
    }

    private static boolean isParentDirectory(String directory) {
        return directory.equals(PARENT_DIRECTORY);
    }

    private static boolean isEmpty(String directory) {
        return directory.equals("");
    }

    private static String createPathFromStack(Stack<String> stack) {
        /** Build path from stack directory **/
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            if (sb.length() > 0 && !stack.peek().equals("/")) {
                sb.insert(0, "/");

            }
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }
}

class UnixSimplePathTest {
    public static void main(String[] args) {
        String path1 = "/home/";
        String path2 = "/a/./b/../../c/";
        String path3 = "/../";
        String path4 = "/home//foo/";

        System.out.printf("Path: %s, Simplified Path: %s\n", path1, UnixSimplePath.get(path1));
        System.out.printf("Path: %s, Simplified Path: %s\n", path2, UnixSimplePath.get(path2));
        System.out.printf("Path: %s, Simplified Path: %s\n", path3, UnixSimplePath.get(path3));
        System.out.printf("Path: %s, Simplified Path: %s\n", path4, UnixSimplePath.get(path4));
    }
}


