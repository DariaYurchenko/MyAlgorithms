package stacks_queues.elementary_sorting;

import java.util.Arrays;

//is always N^2 even if initial array is sorted
public class SelectionSort {

    public static void sort(Comparable[] items) {
        int size = items.length;

        for(int i = 0; i < size; i++) {

            int min = i;
            for(int j = i + 1; j < size; j++) {
                if(items[j].compareTo(items[i]) < 0) {
                    min = j;
                }
            }

            Comparable temp = items[i];
            items[i] = items[min];
            items[min] = temp;
        }
    }

    public static void main(String[] args) {
        Integer[] integers = new Integer[]{3,6,5,1,7};
        sort(integers);
        System.out.println(Arrays.toString(integers));
    }
}
