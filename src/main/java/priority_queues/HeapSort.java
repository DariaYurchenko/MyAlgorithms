package priority_queues;

import java.util.Arrays;

//in-place sorting algorithm, no extra space (like merge sort), N*log N in the worst case (not N^2 like in QuickSort)
//not stable
public class HeapSort {
    public static void sort(Comparable[] items) {
        int size = items.length;
        for (int k = size/2; k >= 1; k--) {
            sink(items, k, size);
        }
        while (size > 1) {
            exch(items, 1, size--);
            sink(items, 1, size);
        }
    }

    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/

    private static void sink(Comparable[] pq, int k, int n) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(pq, j, j+1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

    /***************************************************************************
     * Helper functions for comparisons and swaps.
     * Indices are "off-by-one" to support 1-based indexing.
     ***************************************************************************/
    private static boolean less(Comparable[] items, int i, int j) {
        return items[i-1].compareTo(items[j-1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }

    public static void main(String[] args) {
        Integer[] integers = new Integer[]{3,6,5,1,7,5,3,1};
        sort(integers);

        System.out.println(Arrays.toString(integers));
    }

}
