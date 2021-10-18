package graphs;

public class HasPath {

    private boolean[] marked;
    private Stack stack;
    private Graph graph;

    public HasPath(Graph graph) {
        this.graph = graph;
        this.marked = new boolean[graph.graph.length];
        this.stack = new Stack(graph.graph.length);
        this.graph = graph;
    }

    public boolean hasPath(int src, int dest) {
        if (src == dest) {
            return true;
        }
        stack.push(src);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            System.out.println(current);
            for (int v : graph.getNeighbours(current)) {
                if (v == dest) {
                    return true;
                }
                if (!marked[v]) {
                    //для циклов и ненаправленных графов
                    marked[v] = true;
                    stack.push(v);
                }
            }
        }
        return false;
    }

    //Рекурсивный вариант (у поиска в ширину нету такого)
    public boolean hasPathRecursive(int src, int dest) {
        if (src == dest) {
            return true;
        }
        System.out.println(src);
        for (int v : graph.getNeighbours(src)) {
            if (!marked[v]) {
                marked[v] = true;
                if (v == dest) {
                    return true;
                }
                hasPathRecursive(v, dest);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        HasPath hasPath = new HasPath(new Graph());
        System.out.println(hasPath.hasPath(3, 2));;
    }

    private static class Graph {
        private final int[][] graph = new int[][]{
                new int[]{1, 3},
                new int[]{2},
                new int[]{},
                new int[]{1, 5},
                new int[]{3},
                new int[]{}
        };

        int[] getNeighbours(int v) {
            return graph[v];
        }

    }

    private static class Stack {
        private int[] stack;
        private int top;

        public Stack(int elementsCount) {
            this.stack = new int[elementsCount];
            this.top = -1;
        }

        public void push(int v) {
            stack[++top] = v;
        }

        public int pop() {
            return stack[top--];
        }

        public boolean isEmpty() {
            return top == -1;
        }

    }

}
