package stacks_queues.task_4;

import java.util.Objects;

public class Point implements Comparable<Point> {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(Point point) {
        if(x < point.x) {
            return -1;
        }
        if(x > point.x) {
            return 1;
        }
        else {
            if(y < point.y) {
                return -1;
            }
            if(y > point.y) {
                return 1;
            }
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
