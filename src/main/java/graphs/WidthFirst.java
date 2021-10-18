package graphs;

public class WidthFirst {

    private boolean[] marked;
    private Queue queue;
    private Graph graph;

    public WidthFirst(Graph graph) {
        this.marked = new boolean[graph.graph.length];
        this.queue = new Queue(graph.graph.length);
        this.graph = graph;
        queue.enque(0);
    }

    public void widthFirst() {
        while (!queue.isEmpty()) {
            int current = queue.deque();
            System.out.println(current);
            for (int v : graph.getNeighbours(current)) {
                if (!marked[v]) {
                    marked[v] = true;
                    queue.enque(v);
                }
            }
        }
    }

    public static void main(String[] args) {

        WidthFirst widthFirst = new WidthFirst(new Graph());
        widthFirst.widthFirst();

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

    private static class Queue {
        private int[] queue;
        private int tail;
        private int head;
        private int count;

        public Queue(int elementsCount) {
            this.queue = new int[elementsCount];
            this.head = 0;
            this.tail = -1;
            this.count = 0;
        }

        public void enque(int v) {
            queue[++tail] = v;
            count++;
        }

        public int deque() {
            count--;
            return queue[head++];
        }

        public boolean isEmpty() {
            return count == 0;
        }

    }

}
