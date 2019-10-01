package stacks_queues.task_4;

/*Given two arrays a[] and b[], each containing N distinct 2D points in the plane, design a subquadratic algorithm
to count the number of points that are contained both in array a[] and array b[].*/

import java.util.Arrays;

public class InsertionSet {

    public static int countDublicates(Point[] a, Point[] b) {
        Point[] c = new Point[a.length + b.length];

        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);

        Arrays.sort(c);

        int count = 0;
        for(int i = 1; i < c.length; i++) {
            System.out.println(c[i]);
            if(c[i] == c[i-1]) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Point[] a = new Point[]{new Point(2,4), new Point(6,3), new Point(6,7), new Point(1,8)};
        Point[] b = new Point[]{new Point(5,4), new Point(6,3), new Point(6,7), new Point(8,8)};

        System.out.println(countDublicates(a,b));

    }

}
