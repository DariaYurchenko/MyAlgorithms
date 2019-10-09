package merge_sort.task_2;

/*Counting inversions. An inversion in an array a[] is a pair of entries a[i] and a[j] such that i<j but
a[i]>a[j]. Given an array, design a linearithmic algorithm to count the number of inversions.*/

import java.util.Arrays;

public class InversionsCounter {
    private static int count;

    public static int countInsertions(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        divide(a,0, a.length-1);
        return count;
    }

    private static void divide(Comparable[] a, int lo, int hi) {
        if(hi <= lo) {
            return;
        }
        int mid = (hi - lo)/2 + lo;
        divide(a, lo, mid);
        divide(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;

        while (i <= mid) {
            if(j <= hi) {
                if(a[i].compareTo(a[j]) > 0) {
                    System.out.println("i = " + a[i] + "j = " + a[j]);
                    count++;
                }
                j++;
            }
            else {
                j = mid + 1;
                i++;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] integers = new Integer[]{3,6,5,1,7,9,8,10,8};
        System.out.println(countInsertions(integers));
        System.out.println(Arrays.toString(integers));

    }

}