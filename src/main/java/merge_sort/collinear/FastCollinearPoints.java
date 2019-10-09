package merge_sort.collinear;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints {
    private final LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {

        validateConstructor(points);
        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);

        segments = makeSegmentList(sortedPoints).toArray(new LineSegment[0]);
    }

    private List<LineSegment> makeSegmentList(Point[] sortedPoints) {
        final int N = sortedPoints.length;
        final List<LineSegment> maxLineSegments = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            Point p = sortedPoints[i];
            Point[] pointsBySlope = sortedPoints.clone();
            Arrays.sort(pointsBySlope, p.slopeOrder());

            int x = 1;
            while (x < N) {
                LinkedList<Point> candidates = new LinkedList<>();
                final double SLOPE_REF = p.slopeTo(pointsBySlope[x]);
                do {
                    candidates.add(pointsBySlope[x++]);
                } while (x < N && p.slopeTo(pointsBySlope[x]) == SLOPE_REF);

                if (candidates.size() >= 3 && p.compareTo(candidates.peek()) < 0) {
                    Point min = p;
                    Point max = candidates.removeLast();
                    maxLineSegments.add(new LineSegment(min, max));
                }
            }
        }
        return maxLineSegments;
    }

    private void validateConstructor(Point[] points) {
        if(points == null || containsInvalidParameters(points)) {
            throw new IllegalArgumentException("Invalid input.");
        }
    }

    private boolean containsInvalidParameters(Point[] points) {
        for(int i = 0; i < points.length; i++) {
            if(points[i] == null) {
                return true;
            }
        }
        for(int i = 0; i < points.length - 1; i++) {
            for(int j = i + 1; j < points.length; j++) {
                if(points[i].compareTo(points[j]) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public int numberOfSegments() {
        return segments.length;

    }

    public LineSegment[] segments() {
        return segments.clone();

    }

    public static void main(String[] args) {
        //Point[] pointsP = new Point[]{new Point(2,4), new Point(5,3), null};
        Point[] pointsP = new Point[]{new Point(2,4), null, new Point(5,3)};
        BruteCollinearPoints points = new BruteCollinearPoints(pointsP);
    }
}
