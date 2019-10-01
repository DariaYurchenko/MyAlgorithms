package stacks_queues.elementary_sorting;

import java.util.Arrays;

//N-1 compares if an array is already sorted! in the worst case is N^2, average - N
public class InsertionSort {

    public static void sort(Comparable[] items) {
        int size = items.length;
        for(int i = 0; i < size; i++) {
            for(int j = i; j > 0; j--) {
                if(items[j].compareTo(items[j-1]) < 0) {
                    Comparable temp = items[j];
                    items[j] = items[j-1];
                    items[j-1] = temp;
                }
                else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] integers = new Integer[]{3,6,5,1,7};
        sort(integers);
        System.out.println(Arrays.toString(integers));
    }
}
