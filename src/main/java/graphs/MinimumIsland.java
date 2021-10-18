package graphs;

import java.util.Objects;

public class MinimumIsland {

    private boolean[][] marked;
    private Stack stack;
    private Graph graph;

    public MinimumIsland(Graph graph) {
        this.marked = new boolean[graph.graph.length][graph.graph[0].length];
        this.stack = new Stack(graph.graph.length * graph.graph[0].length);
        this.graph = graph;
    }

    public int minimumIsland() {
        int minimumIsland = -1;
        for (int row = 0; row < graph.graph.length; row++) {
            for (int column = 0; column < graph.graph[0].length; column++) {
                int size = 0;
                if (Objects.equals("L", graph.getIsland(new Coordinate(row, column))) && !marked[row][column]) {
                    stack.push(new Coordinate(row, column));
                    marked[row][column] = true;
                    size += 1;
                    while (!stack.isEmpty()) {
                        Coordinate current = stack.pop();
                        for (Coordinate coordinate : graph.getNeighbours(current)) {
                            if (Objects.equals("L", graph.getIsland(coordinate)) && !marked[coordinate.getRow()][coordinate.getColumn()]) {
                                marked[coordinate.getRow()][coordinate.getColumn()] = true;
                                stack.push(coordinate);
                                size += 1;
                            }
                        }
                    }
                }
                if (minimumIsland <= 0 || (minimumIsland > size && size != 0)) {
                    minimumIsland = size;
                }
            }
        }
        return minimumIsland;
    }

    public static void main(String[] args) {
        MinimumIsland minimumIsland = new MinimumIsland(new Graph());
        System.out.println(minimumIsland.minimumIsland());
    }

    private static class Graph {
        private final String[][] graph = new String[][]{
                new String[]{"W", "L", "W", "W", "L"},
                new String[]{"W", "L", "W", "W", "W"},
                new String[]{"W", "W", "W", "L", "W"},
                new String[]{"W", "W", "L", "L", "W"},
                new String[]{"L", "W", "W", "L", "L"},
                new String[]{"L", "L", "W", "W", "W"},
        };

        String getIsland(Coordinate coordinate) {
            return graph[coordinate.getRow()][coordinate.getColumn()];
        }

        Coordinate[] getNeighbours(Coordinate coordinate) {
            return new Coordinate[]{
                    new Coordinate(coordinate.getRow() >= graph.length - 1 ? coordinate.getRow() : coordinate.getRow() + 1, coordinate.getColumn()),
                    new Coordinate(coordinate.getRow() <= 0 ? coordinate.getRow() : coordinate.getRow() - 1, coordinate.getColumn()),
                    new Coordinate(coordinate.getRow(), coordinate.getColumn() >= graph[0].length - 1 ? coordinate.getColumn() : coordinate.getColumn() + 1),
                    new Coordinate(coordinate.getRow(), coordinate.getColumn() <= 0 ? coordinate.getColumn() : coordinate.getColumn() - 1)
            };
        }

    }

    private static class Coordinate {
        private int row;
        private int column;

        public Coordinate(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

        @Override
        public String toString() {
            return "Coordinate{" +
                    "row=" + row +
                    ", column=" + column +
                    '}';
        }
    }

    private static class Stack {
        private Coordinate[] stack;
        private int top;

        public Stack(int elementsCount) {
            this.stack = new Coordinate[elementsCount];
            this.top = -1;
        }

        public void push(Coordinate v) {
            stack[++top] = v;
        }

        public Coordinate pop() {
            return stack[top--];
        }

        public boolean isEmpty() {
            return top == -1;
        }

    }
    
}
