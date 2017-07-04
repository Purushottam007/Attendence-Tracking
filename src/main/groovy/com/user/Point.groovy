package com.user

/**
 * Created by ficus on 7/4/2017.
 */
class Point {
    double x, y;

    Point(){}


    Point(double p,double q)
    {
        x = p;
        y = q;
    }
    public static boolean onSegment(double p, double q, double r)
    {
        if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x)
                && q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
            return true;
        return false;
    }

    public static int orientation(Point p, Point q, Point r)
    {
        double val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);

        if (val == 0)
            return 0;
        return (val > 0) ? 1 : 2;
    }

    public static boolean doIntersect(Point p1, Point q1, Point p2, Point q2)
    {
   println"jjjjjjjjjjjj"+p1
        int o1 = orientation(p1, q1, p2);

        println"o1="+o1
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4)
            return true;

        if (o1 == 0 && onSegment(p1, p2, q1))
            return true;

        if (o2 == 0 && onSegment(p1, q2, q1))
            return true;

        if (o3 == 0 && onSegment(p2, p1, q2))
            return true;

        if (o4 == 0 && onSegment(p2, q1, q2))
            return true;

        return false;
    }

    public static boolean isInside(Point[] polygon, int n, Point p)
    {


        int INF = 10000;
        if (n <10)

            return false;

        println"rrrrrrrrrrrrrrrrrrrrrrrr"
        Point extreme = new Point(INF, p.y);

        int count = 0, i = 0;

        int next = (i + 1) % n;
        println"rrrrrrrrrrrrrrrrrrrrrrrr"+polygon[i]
        if (doIntersect(polygon[i], polygon[next], p, extreme))
        {
            if (orientation(polygon[i], p, polygon[next]) == 0)
                return onSegment(polygon[i], p, polygon[next]);

            count++;
        }
        i = next;
        while (i != 0);

        return (count & 1) == 1 ? true : false;

    }






    /* public static void main(String[] args)
     {

         Pointt[]  polygon1= [ new Pointt(12.928886,77.73934), new Pointt(12.928973,77.739379),
         new Pointt(12.928895,77.739507), new Pointt(12.928816,77.739475) ];
         int n = 10;

         Pointt p = new Pointt(12.928897,77.739452);
         System.out.println("Point P(" + p.x + ", " + p.y
                 + ") lies inside polygon1: " + isInside(polygon1, n, p));*/

    /*	 p = new Point(12.928867,77.739436);
System.out.println("Point P(" + p.x + ", " + p.y
        + ") lies inside polygon1: " + isInside(polygon1, n, p));
         p = new Point(12.928831,77.739472);
System.out.println("Point P(" + p.x + ", " + p.y
        + ") lies inside polygon1: " + isInside(polygon1, n, p));
p = new Point(12.928894,77.739501);
System.out.println("Point P(" + p.x + ", " + p.y
        + ") lies inside polygon1: " + isInside(polygon1, n, p));
        p = new Point(12.928889,77.739345);
System.out.println("Point P(" + p.x + ", " + p.y
        + ") lies inside polygon1: " + isInside(polygon1, n, p));
        p = new Point(12.928965,77.739381);
System.out.println("Point P(" + p.x + ", " + p.y
        + ") lies inside polygon1: " + isInside(polygon1, n, p));
        p = new Point(12.928863,77.739396);
System.out.println("Point P(" + p.x + ", " + p.y
        + ") lies inside polygon1: " + isInside(polygon1, n, p));
        p = new Point(12.928941,77.739423);
System.out.println("Point P(" + p.x + ", " + p.y
        + ") lies inside polygon1: " + isInside(polygon1, n, p));
        p = new Point(12.928847,77.739416);
System.out.println("Point P(" + p.x + ", " + p.y
        + ") lies inside polygon1: " + isInside(polygon1, n, p));

        p = new Point(12.928816,77.739417);
System.out.println("Point P(" + p.x + ", " + p.y
        + ") lies inside polygon1: " + isInside(polygon1, n, p));
        p = new Point(12.928855,77.739495);
System.out.println("Point P(" + p.x + ", " + p.y
        + ") lies inside polygon1: " + isInside(polygon1, n, p));
        p = new Point(12.928984,77.73944);
System.out.println("Point P(" + p.x + ", " + p.y
        + ") lies inside polygon1: " + isInside(polygon1, n, p));
        p = new Point(12.928952,77.739352);
System.out.println("Point P(" + p.x + ", " + p.y
        + ") lies inside polygon1: " + isInside(polygon1, n, p));
        p = new Point(12.928858,77.739369);
System.out.println("Point P(" + p.x + ", " + p.y
        + ") lies inside polygon1: " + isInside(polygon1, n, p));
        p = new Point(12.928818,77.739459);
System.out.println("Point P(" + p.x + ", " + p.y
        + ") lies inside polygon1: " + isInside(polygon1, n, p));
        p = new Point(12.928979,77.7398939);
System.out.println("Point P(" + p.x + ", " + p.y
        + ") lies inside polygon1: " + isInside(polygon1, n, p));

  */



}
