package merge_sort.collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private final LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {

        validateConstructor(points);
        //better to sort once now then a huge account of times later
        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);

        this.segments = makeSegmentsList(sortedPoints).toArray(new LineSegment[0]);
    }

    private List<LineSegment> makeSegmentsList(Point[] sortedPoints) {
        List<LineSegment> lineSegments = new ArrayList<>();

        for(int i = 0; i < sortedPoints.length-3; i++) {
            Point a = sortedPoints[i];
            for(int j = i + 1; j < sortedPoints.length - 2; j++) {
                Point b = sortedPoints[j];
                double slopeAB = a.slopeTo(b);
                for(int k = j + 1; k < sortedPoints.length - 1; k++) {
                    Point c = sortedPoints[k];
                    double slopeAC = a.slopeTo(c);
                    if(slopeAB == slopeAC) {
                        for(int l = k + 1; l < sortedPoints.length; l++) {
                            Point d = sortedPoints[l];
                            double slopeAD = a.slopeTo(d);
                            if(slopeAB == slopeAD) {
                                lineSegments.add(new LineSegment(a, d));
                            }
                        }
                    }
                }
            }
        }
        return lineSegments;
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
