package graphs;

public class LargestComponent {

    private boolean[] marked;
    int counter;
    private Stack stack;
    private Graph graph;

    public LargestComponent(Graph graph) {
        this.marked = new boolean[graph.graph.length];
        this.stack = new Stack(graph.graph.length);
        this.graph = graph;
    }

    public int largestComponent() {
        int largestSize = 0;
        for (int i = 0; i < graph.graph.length; i++) {
            if (!marked[i]) {
                counter++;
                stack.push(i);
                int size = largestComponent(i);
                if (size > largestSize) {
                    largestSize = size;
                }
            }
        }
        return largestSize;
    }

    public int largestComponent(int start) {
        int size = 0;
        stack.push(start);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            for (int v : graph.getNeighbours(current)) {
                if (!marked[v]) {
                    //для циклов и ненаправленных графов
                    marked[v] = true;
                    size++;
                    stack.push(v);
                }
            }
        }
        return size;
    }

    public static void main(String[] args) {
        Graph graph = new Graph();

        LargestComponent largestComponent = new LargestComponent(graph);
        System.out.println(largestComponent.largestComponent());

    }


    private static class Graph {
        private final int[][] graph = new int[][]{
                new int[]{1},
                new int[]{0},
                new int[]{},
                new int[]{5},
                new int[]{5},
                new int[]{3, 4, 6, 7, 8},
                new int[]{5},
                new int[]{5},
                new int[]{5}
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
