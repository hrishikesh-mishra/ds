package com.hrishikeshmishra.practices.mixed;

/**
 * Problem:
 * How to check if two given line segments intersect?
 *
 * @author hrishikesh.mishra
 * @video https://www.youtube.com/watch?v=R08OY6yDNy0
 * @link http://hrishikeshmishra.com/line-segments-intersect/
 */
public class LineSegmentsIntersect {

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

    public static Orientation orientation(Point p1,
                                          Point p2,
                                          Point p3) {
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


    /**
     * Given three colinear points a, b, c, the function checks
     * if point b lies on line segment 'ac'
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static boolean onSegment(Point a, Point b, Point c) {
        return (b.x <= Math.max(a.x, c.x) &&
                b.x >= Math.min(a.x, c.x) &&
                b.y <= Math.max(a.y, c.y) &&
                b.y >= Math.min(a.y, c.y));
    }

    public static boolean isIntersect(Point p1, Point q1, Point p2, Point q2) {
        Orientation o1 = orientation(p1, q1, p2);
        Orientation o2 = orientation(p1, q1, q2);
        Orientation o3 = orientation(p2, q2, p1);
        Orientation o4 = orientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4)
            return true;

        /** Special Cases **/
        /** p1, q1 and p2 are colinear and p2 lies on segment p1q1 **/
        if (o1.equals(Orientation.Colinear) && onSegment(p1, p2, q1)) return true;

        /** p1, q1 and p2 are colinear and q2 lies on segment p1q1 **/
        if (o2.equals(Orientation.Colinear) && onSegment(p1, q2, q1)) return true;

        /** p2, q2 and p1 are colinear and p1 lies on segment p2q2 **/
        if (o3.equals(Orientation.Colinear) && onSegment(p2, p1, q2)) return true;

        /** p2, q2 and q1 are colinear and q1 lies on segment p2q2 **/
        if (o4.equals(Orientation.Colinear) && onSegment(p2, q1, q2)) return true;

        return false;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1, 1),
                q1 = new Point(10, 1),
                p2 = new Point(1, 2),
                q2 = new Point(10, 2);

        System.out.println(isIntersect(p1, q1, p2, q2));

        p1 = new Point(-5, -5);
        q1 = new Point(0, 0);
        p2 = new Point(1, 1);
        q2 = new Point(10, 10);

        System.out.println(isIntersect(p1, q1, p2, q2));

        p1 = new Point(10, 0);
        q1 = new Point(0, 10);
        p2 = new Point(0, 0);
        q2 = new Point(10, 10);

        System.out.println(isIntersect(p1, q1, p2, q2));
    }
}


