package com.hrishikesh.practices.mixed;

/**
 * Problem:
 * Orientation of 3 ordered points
 *
 * @author hrishikesh.mishra
 * @stackoverflow http://stackoverflow.com/questions/17592800/how-to-find-the-orientation-of-three-points-in-a-two-dimensional-space-given-coo
 * @link http://hrishikeshmishra.com/orientation-3-ordered-points/
 */
public class PointOrientation {

    public static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public enum Orientation {
        Colinear,
        Clockwise,
        Counterclockwise
    }

    public static Orientation compute(Point p1, Point p2, Point p3) {
        int val = (p2.y - p1.y) * (p3.x - p2.x) -
                (p2.x - p1.x) * (p3.y - p2.y);

        if (val == 0) {
            return Orientation.Colinear;
        } else if (val > 0) {
            return Orientation.Clockwise;
        } else {
            return Orientation.Counterclockwise;
        }

    }

    public static void main(String[] args) {
        Point p1 = new Point(0, 0),
                p2 = new Point(4, 4),
                p3 = new Point(1, 2);
        System.out.println(compute(p1, p2, p3));
    }
}
