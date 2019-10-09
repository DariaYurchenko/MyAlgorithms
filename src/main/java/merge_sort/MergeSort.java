package merge_sort;

import java.util.Arrays;

//N*logN
//needs extra space
public class MergeSort {

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);
    }


    //10% slower
    public static void nonRecursiveSort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz+sz) {
            for (int lo = 0; lo < N-sz; lo += sz+sz) {
                merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
            }
        }
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if(hi <= lo) {
            return;
        }
        int mid = (hi - lo)/2 + lo;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        //stop if is already sorted
        if(a[mid + 1].compareTo(a[mid]) > 0) {
            return;
        }
        merge(a, aux, lo, mid, hi);

    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        for(int k = 0; k < a.length; k++) {
            aux[k] = a[k];
        }

        int i = lo;
        int j = mid + 1;
        for(int k = lo; k <= hi; k++) {
            if(i > mid) {
                a[k] = aux[j++];
            }
            else if(j > hi) {
                a[k] = aux[i++];
            }
            else if(aux[j].compareTo(aux[i]) < 0) {
                a[k] = aux[j++];
            }
            else {
                a[k] = aux[i++];
            }
        }
        assert isSorted(a, lo, hi);
    }

    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    public static void main(String[] args) {
        Integer[] integers = new Integer[]{3,6,5,1,7,5,3,1};
        sort(integers);
        System.out.println(Arrays.toString(integers));
    }
}
