package stacks_queues;

public class LinkedListStack {
    private Node first;

    private class Node {
        private String s;
        private Node next;

        public Node(String s, Node next) {
            this.s = s;
            this.next = next;
        }
    }

    public void push(String s) {
        Node node = first;
        first = new Node(s, node);
    }

    public String pop() {
        String s = first.s;
        first = first.next;
        return s;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        int size = 0;
        for(Node node = first; node != null; node = node.next) {
            size++;
        }
        return size;
    }
}
