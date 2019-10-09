package merge_sort;

import library.StdRandom;

import java.util.Arrays;

//doesn't need extra space
//N log N in worst case but a little bit faster then merge sort
//not stable
public class QuickSort {

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if(lo >= hi) {
            return;
        }
        int j = partitioning(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partitioning(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            while(a[++i].compareTo(a[lo]) < 0) {
                if(i == hi) {
                    break;
                }
            }
            while(a[--j].compareTo(a[lo]) > 0) {
                if(j == lo) {
                    break;
                }
            }
            if(i >= j) {
                break;
            }
            Comparable temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        Comparable temp = a[lo];
        a[lo] = a[j];
        a[j] = temp;
        return j;

    }

    public static void main(String[] args) {
        Integer[] integers = new Integer[]{3,6,5,1,7,5,3,1};
        sort(integers);
        System.out.println(Arrays.toString(integers));
    }
}
