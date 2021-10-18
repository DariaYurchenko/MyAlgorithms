package graphs;

//Подходит только поиск в ширину, потому что в глубину, будет обходить всех соседей соседей
//и доберется до пункта назначения намного позже, в то время как искомая нода может быть соседней
//уже для стартовой
//Обязательно надо помечать ноды как посещенные!
public class ShortestPath {

    private boolean[] marked;
    private Queue queue;
    private Graph graph;

    public ShortestPath(Graph graph) {
        this.marked = new boolean[graph.graph.length];
        this.queue = new Queue(graph.graph.length);
        this.graph = graph;
    }

    public int shortestPath(int start, int dest) {
        int distance = 0;
        queue.enque(new Node(start, distance));
        while (!queue.isEmpty()) {
            Node node = queue.deque();
            marked[node.getNode()] = true;
            for (int v : graph.getNeighbours(node.getNode())) {
                if (v == dest) {
                    return node.getDistance() + 1;
                }
                if (!marked[v]) {
                    queue.enque(new Node(v, node.getDistance() + 1));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ShortestPath shortestPath = new ShortestPath(new Graph());
        System.out.println(shortestPath.shortestPath(0, 2));;
    }

    private static class Node {
        private int node;
        private int distance;

        public Node(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        public int getNode() {
            return node;
        }

        public int getDistance() {
            return distance;
        }

    }

    private static class Graph {
        private final int[][] graph = new int[][]{
                new int[]{1, 4},
                new int[]{0, 2},
                new int[]{1, 3},
                new int[]{2, 4},
                new int[]{3, 0}
        };

        int[] getNeighbours(int v) {
            return graph[v];
        }

    }

    private static class Queue {
        private Node[] queue;
        private int tail;
        private int head;
        private int count;

        public Queue(int elementsCount) {
            this.queue = new Node[elementsCount];
            this.head = 0;
            this.tail = -1;
            this.count = 0;
        }

        public void enque(Node node) {
            queue[++tail] = node;
            count++;
        }

        public Node deque() {
            count--;
            return queue[head++];
        }

        public boolean isEmpty() {
            return count == 0;
        }

    }

}
