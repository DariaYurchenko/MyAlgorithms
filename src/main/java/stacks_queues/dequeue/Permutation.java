package stacks_queues.dequeue;

import library.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> strings = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            strings.enqueue(StdIn.readString());
        }
        for (int i = 0; i < k; ++i) {
            System.out.println(strings.dequeue());
        }
    }
}
