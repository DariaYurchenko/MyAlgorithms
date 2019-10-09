package priority_queues;

import java.util.Arrays;

//N
public class PriorityQueue<Key extends Comparable<Key>> {
    private Key[] items;
    private int size;

    public PriorityQueue(int capacity) {
        this.items = (Key[]) new Comparable[capacity+1];
    }

    public void insert(Key key) {
        items[size++] = key;

    }

    public Key deleteMax() {
        int max = 0;
        for(int i = 1; i < items.length; i++) {
            if(items[i].compareTo(items[max]) > 0) {
                max = i;
            }
        }
        Key temp = items[max];
        items[max] = items[size - 1];
        items[size - 1] = temp;
        return items[--size];
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(3);
        queue.insert(76);
        queue.insert(56);
        queue.insert(742);
        queue.insert(12);

        System.out.println(Arrays.toString(queue.items));
        System.out.println(queue.deleteMax());
        System.out.println(Arrays.toString(queue.items));
        System.out.println(queue.size);
    }

    public Key[] getItems() {
        return items;
    }
}
