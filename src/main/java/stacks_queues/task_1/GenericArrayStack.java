package stacks_queues.task_1;

import java.util.Iterator;

public class GenericArrayStack<Item> implements Iterable<Item> {
    private Item[] items;
    private int size;

    public GenericArrayStack() {
        this.items = (Item[]) new Object[1];
    }

    public void push(Item item) {
        if(size == items.length) {
            resize(items.length*2);
        }
        items[size++] = item;
    }

    public Item pop() {
        Item item = items[--size];
        items[size] = null;
        if(size > 0 && size == items.length/4) {
            resize(items.length/2);
        }
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int capacity){
        Item[] copy = (Item[]) new Object[items.length*2];
        for(int i = 0; i < size; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    //reverse because stack
    private class ReverseArrayIterator implements Iterator<Item> {

        private int i = size;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return items[i--];
        }

        @Override
        public void remove() {

        }

    }

}
