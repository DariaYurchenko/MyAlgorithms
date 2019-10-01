package stacks_queues.task_2;

import java.util.Iterator;

public class StackWithMax implements Iterable<Integer> {
    private int[] items;
    private int size;

    public StackWithMax() {
        this.items =  new int[1];
    }

    public void push(int item) {
        if(size == items.length) {
            resize(items.length*2);
        }
        items[size++] = item;
    }

    public int pop() {
        int item = items[--size];
        items[size] = 0;
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

    public int max() {
        int max = 0;
        for(int i = 0; i < size; i++) {
            if(items[i] > max) {
                max = items[i];

            }
        }
        return max;
    }

    private void resize(int capacity){
        int[] copy = new int[items.length*2];
        for(int i = 0; i < size; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new ReverseArrayIterator();
    }

    //reverse because stack
    private class ReverseArrayIterator implements Iterator<Integer> {

        private int i = size;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Integer next() {
            return items[--i];
        }

        @Override
        public void remove() {

        }
    }

    public static void main(String[] args) {
        StackWithMax stack = new StackWithMax();
        stack.push(4);
        stack.push(7);
        stack.push(9);
        stack.push(2);

        System.out.println(stack.max());
        stack.forEach(System.out::println);
    }

}
