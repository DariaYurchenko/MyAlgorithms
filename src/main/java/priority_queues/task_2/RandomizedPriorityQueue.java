package priority_queues.task_2;



/*Randomized priority queue. Describe how to add the methods sample() and delRandom() to our binary heap implementation.
The two methods return a key that is chosen uniformly at random among the remaining keys, with the latter method also
removing that key. The sample() method should take constant time; the delRandom() method should take logarithmic time.
Do not worry about resizing the underlying array.*/

import library.StdRandom;

import java.util.Arrays;

public class RandomizedPriorityQueue<Key extends Comparable<Key>> {
    private Key[] items;
    private int size;

    public RandomizedPriorityQueue(int capacity) {
        this.items = (Key[]) new Comparable[capacity+1];
    }

    public void insert(Key key) {
        items[++size] = key;
        swim(size);
    }

    public Key deleteMax() {
        Key max = items[1];

        Key temp = items[1];
        items[1] = items[size];
        items[size] = temp;
        size--;

        sink(1);

        items[size + 1] = null;
        return max;
    }

    public Key removeRandom() {
        int random = sample();
        Key removed = items[random];

        items[random] = items[size];
        items[size] = removed;

        size--;
        swim(random);
        return removed;
    }

    private int sample() {
        return StdRandom.uniform(0, size);
    }

    private void swim(int k) {
        while(k > 1 && items[k/2].compareTo(items[k]) < 0) {
            Key temp = items[k/2];
            items[k/2] = items[k];
            items[k] = temp;
            k = k/2;
        }
    }

    //chooses the largest child of 2 in order to exchange
    private void sink(int k) {
        while (2*k <= size) {
            int j = 2*k;
            if(j < size && items[j].compareTo(items[j+1]) < 0) {
                j++;
            }
            if(items[k].compareTo(items[j]) > 0) {
                break;
            }
            Key temp = items[k];
            items[k] = items[j];
            items[j] = temp;
            k = j;
        }
    }

    public static void main(String[] args) {
        RandomizedPriorityQueue<Integer> queue = new RandomizedPriorityQueue<>(7);
        queue.insert(76);
        queue.insert(56);
        queue.insert(742);
        queue.insert(12);
        queue.insert(54);
        queue.insert(156);
        queue.insert(765);

        System.out.println(Arrays.toString(queue.items));

        System.out.println(queue.removeRandom());
        System.out.println(Arrays.toString(queue.items));
        System.out.println(queue.size);

    }


}
