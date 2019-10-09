package merge_sort.task_1;

import java.util.Arrays;

/*Merging with smaller auxiliary array. Suppose that the subarray a[0]to a[n−1] is sorted and the subarray a[n]to
a[2∗n−1] is sorted. How can you merge the two subarrays so that a[0]to a[2∗n−1] is sorted using an auxiliary array of
length n (instead of 2n)?*/
public class MergeArrays {

    public static void merge(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length/2];

        for(int i = 0; i < aux.length; i++) {
            aux[i] = a[i];
        }

        int k = 0;
        int j = aux.length;
        for(int i = 0; i < a.length; i++) {
            if(k > aux.length) {
                a[i] = a[j++];
            }
            else if(j > a.length) {
                a[i] = a[k++];
            }
            else if(k < aux.length && a[j].compareTo(aux[k]) < 0) {
                a[i] = a[j++];
            }
            else if(k < aux.length && a[j].compareTo(aux[k]) > 0) {
                a[i] = aux[k++];
            }
        }
    }

    public static void main(String[] args) {
        Integer[] integers = new Integer[]{1,2,5,7,3,8,9,10};
        merge(integers);
        System.out.println(Arrays.toString(integers));
    }
}
