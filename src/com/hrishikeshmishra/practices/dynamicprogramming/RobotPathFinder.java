package com.hrishikeshmishra.practices.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

import static com.hrishikeshmishra.practices.dynamicprogramming.RobotPathFinder.findPath;

/**
 * Problem:
 * Robot Path Finder
 * A robot is located at the top-left corner of a m x n grid.
 * The robot can only move either down or right at any point in time. But certain cells are
 * off limit such that the robot cannot step on them. Design an algorithm to find a path from
 * the top left to the bottom right.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/robot-path-finder/
 */
public class RobotPathFinder {

    public static void findPath(boolean[][] maze) {

        List<String> path = new ArrayList<>();
        boolean pathExists = findPath(maze, maze.length - 1, maze[0].length - 1, path);
        if (pathExists) {
            System.out.println("Path : " + path);
        } else {
            System.out.println("Path not found.");
        }


    }

    public static boolean findPath(boolean[][] maze, int row, int col, List<String> path) {
        if (col < 0 || row < 0 || !maze[row][col]) {
            return false;
        }

        //@// TODO: need to optimise with cache the previous result
        if ((row == 0 && col == 0) || findPath(maze, row, col - 1, path) || findPath(maze, row - 1, col, path)) {
            path.add("(" + row + "," + col + ")");
            return true;
        }

        return false;
    }

}


class RobotPathFinderTest {
    public static void main(String[] args) {
        boolean[][] maze = new boolean[4][4];

        maze[3][3] = true;
        maze[2][3] = true;
        maze[2][2] = true;
        maze[2][1] = true;
        maze[2][0] = true;
        maze[1][0] = true;
        maze[0][0] = true;

        findPath(maze);

    }
}