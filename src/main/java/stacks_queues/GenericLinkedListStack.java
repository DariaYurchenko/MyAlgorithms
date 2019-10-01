package stacks_queues;

import java.util.Iterator;

public class GenericLinkedListStack<Item> implements Iterable<Item> {
    private Node first;

    private class Node {
        private Item item;
        private Node next;

        public Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public void push(Item item) {
        Node node = first;
        first = new Node(item, node);
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        return item;
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

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {

        }

    }
}
