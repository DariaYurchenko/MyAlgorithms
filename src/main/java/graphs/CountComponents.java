package graphs;

public class CountComponents {

    private boolean[] marked;
    int counter;
    private Stack stack;
    private Graph graph;

    public CountComponents(Graph graph) {
        this.marked = new boolean[graph.graph.length];
        this.stack = new Stack(graph.graph.length);
        this.graph = graph;
    }

    public int countComponents() {
        for (int i = 0; i < graph.graph.length; i++) {
            if (!marked[i]) {
                counter++;
                stack.push(i);
                //countComponents(i);
                countComponentsRecursive(i);
            }
        }
        return counter;
    }

    public void countComponents(int start) {
        stack.push(start);
        while (!stack.isEmpty()) {
            int current = stack.pop();
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
    public void countComponentsRecursive(int start) {
        for (int v : graph.getNeighbours(start)) {
            if (!marked[v]) {
                marked[v] = true;
                countComponentsRecursive(v);
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();

        CountComponents countComponents = new CountComponents(graph);
        System.out.println(countComponents.countComponents());

    }


    private static class Graph {
        private final int[][] graph = new int[][]{
                new int[]{1},
                new int[]{0},
                new int[]{},
                new int[]{5},
                new int[]{5},
                new int[]{3, 4, 6, 7},
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
