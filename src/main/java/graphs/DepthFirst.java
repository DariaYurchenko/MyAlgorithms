package graphs;

public class DepthFirst {

    private boolean[] marked;
    private Stack stack;
    private Graph graph;

    public DepthFirst(Graph graph) {
        this.marked = new boolean[graph.graph.length];
        this.stack = new Stack(graph.graph.length);
        this.graph = graph;
        stack.push(0);
    }

    public void depthFirst() {
        while (!stack.isEmpty()) {
            int current = stack.pop();
            System.out.println(current);
            for (int v : graph.getNeighbours(current)) {
                if (!marked[v]) {
                    //для циклов и ненаправленных графов
                    marked[v] = true;
                    stack.push(v);
                }
            }
        }
    }

    //Рекурсивный вариант (у поиска в ширину нету такого)
    public void depthFirst(int start) {
        System.out.println(start);
        for (int v : graph.getNeighbours(start)) {
            if (!marked[v]) {
                marked[v] = true;
                depthFirst(v);
            }
        }
    }


    public static void main(String[] args) {

        DepthFirst depthFirst = new DepthFirst(new Graph());
       // depthFirst.depthFirst();
        System.out.println("----------------------------");
        depthFirst.depthFirst(0);

    }

    private static class Graph {
        private final int[][] graph = new int[][]{
                new int[]{1, 2},
                new int[]{3},
                new int[]{4, 1},
                new int[]{5},
                new int[]{},
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
