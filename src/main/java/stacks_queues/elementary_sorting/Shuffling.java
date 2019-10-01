package stacks_queues.elementary_sorting;

import library.StdRandom;

import java.util.Arrays;

//Knuth shuffle
public class Shuffling {

    public static void shuffle(Object[] items) {
        int size = items.length;

        for(int i = 0; i < size; i++) {
            int r = StdRandom.uniform(i+1);
            Object temp = items[i];
            items[i] = items[r];
            items[r] = temp;
        }
    }

    public static void main(String[] args) {
        Integer[] integers = new Integer[]{3,6,5,1,7,12,32,4,2,34};
        shuffle(integers);
        System.out.println(Arrays.toString(integers));
    }
}
