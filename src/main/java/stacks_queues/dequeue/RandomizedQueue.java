package stacks_queues.dequeue;

import library.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.items = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        validateArguments(item);
        if(items.length == size) {
            resize(items.length*2);
        }
        items[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        validateQueue();
        int index = StdRandom.uniform(size);
        Item item = items[index];
        items[index] = null;
        changeElement(items, index, size-1);
        size--;
        if(size > 0 && items.length/4 == size) {
            resize(items.length/2);
        }
        return item;
    }

    private void changeElement(Item[] items, int index1, int index2) {
        if(index1 == index2) {
            return;
        }
        Item item = items[index1];
        items[index1] = items[index2];
        items[index2] = item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        validateQueue();
        int index = StdRandom.uniform(size);
        return items[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private void resize(int capacity){
        Item[] copy = (Item[]) new Object[capacity*2];
        for(int i = 0; i < size; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    private void validateArguments(Item item) {
        if(item == null) {
            throw new IllegalArgumentException("Illegal argument!");
        }
    }

    private void validateQueue() {
        if(isEmpty()) {
            throw new NoSuchElementException("The queue is empty!");
        }
    }

    private class RandomizedIterator implements Iterator<Item> {
        private Item[] iteratorItems;
        private int i;

        public RandomizedIterator() {
            iteratorItems =  (Item[]) new Object[size];
            for(int i = 0; i < size; i++) {
                iteratorItems[i] = items[i];
            }
            shuffle(items);
        }

        private void shuffle(Item[] items) {
            for (int i = 1; i < items.length; ++i) {
                int index = StdRandom.uniform(i+1);
                changeElement(items, index, i);
            }
        }

        @Override
        public boolean hasNext() {
            return i < iteratorItems.length;
        }

        @Override
        public Item next() {
            if(!hasNext()) {
                throw new NoSuchElementException("There is no more elements in queue");
            }
            Item item = iteratorItems[i];
            i++;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        System.out.println(queue.size());

        queue.enqueue(2);
        queue.enqueue(34);
        queue.enqueue(4);
        queue.enqueue(56);

        queue.forEach(System.out::println);

        System.out.println(queue.sample());

        System.out.println(queue.dequeue());
        queue.forEach(System.out::println);
    }

}