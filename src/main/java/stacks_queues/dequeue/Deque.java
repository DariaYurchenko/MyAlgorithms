package stacks_queues.dequeue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    // construct an empty deque
    public Deque() {

    }

    private class Node {
        private Node next;
        private Node prev;
        private Item item;

        public Node(Node next, Node prev, Item item) {
            this.next = next;
            this.prev = prev;
            this.item = item;
        }
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        validateArguments(item);
        Node node = first;
        first = new Node(node, null, item);
        if(node != null) {
            node.prev = first;
        }
        if(last == null) {
            last = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        validateArguments(item);
        Node node = last;
        last = new Node(null, node, item);
        if(node != null) {
            node.next = last;
        }
        if(first == null) {
            first = last;
        }
        size++;
    }

    private void validateArguments(Item item) {
        if(item == null) {
            throw new IllegalArgumentException("Illegal argument!");
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        validateQueue();
        Item item = first.item;
        first = first.next;
        if(first != null) {
            first.prev = null;
        }
        else {
            last = null;
        }
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        validateQueue();
        Item item = last.item;
        last = last.prev;
        if(last != null) {
            last.next = null;
        }
        else {
            first = null;
        }
        size--;
        return item;
    }

    private void validateQueue() {
        if(isEmpty()) {
            throw new NoSuchElementException("The queue is empty!");
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node node = first;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Item next() {
            if(!hasNext()) {
                throw new NoSuchElementException("There is no more elements in deque");
            }
            Item item = node.item;
            node = node.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Unsupported operation!");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();

        deque.addFirst(1);
        deque.addFirst(2);
        deque.addLast(5);
        deque.addFirst(3);

        deque.removeFirst();
        deque.removeFirst();
        deque.removeLast();

        System.out.println(deque.isEmpty());
        System.out.println(deque.size());

        deque.forEach(System.out::println);

    }

}