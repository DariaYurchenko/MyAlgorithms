package stacks_queues.elementary_sorting;

import java.util.Arrays;

//worst case - N^3/2
public class ShellSort {

    public static void sort(Comparable[] items) {
        int size = items.length;
        int h = 1;

        while(h < size/3) {
            h = 3*h + 1;
        }

        while(h >= 1) {
            for(int i = h; i < size; i++) {
                for(int j = i; j >= h && items[j].compareTo(items[j-h]) < 0; j -= h) {
                    Comparable temp = items[j];
                    items[j] = items[j-h];
                    items[j-h] = temp;
                }
            }
            h = h/3;
        }
    }

    public static void main(String[] args) {
        Integer[] integers = new Integer[]{3,6,5,1,7,12,32,4,2,34};
        sort(integers);
        System.out.println(Arrays.toString(integers));
    }
}
