package stacks_queues;

public class LinkedListQueue {
    private Node first;
    private Node last;

    private class Node {
        private String s;
        private Node next;

        public Node(String s, Node next) {
            this.s = s;
            this.next = next;
        }
    }

    public void enqueue(String item) {
        Node node = last;
        last = new Node(item, null);
        if(isEmpty()) {
            first = last;
        }
        node.next = last;
    }

    public String dequeue() {
        String s = first.s;
        first = first.next;
        if(isEmpty()) {
            last = null;
        }
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
