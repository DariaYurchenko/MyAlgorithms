package merge_sort.task_3;

import library.StdRandom;

import java.util.Arrays;
import java.util.LinkedList;

public class ShuffleLinkedList {

    public static void shuffle(LinkedList<Integer> list) {
        divide(list,0, list.size()-1);
    }

    private static void divide(LinkedList<Integer> list, int lo, int hi) {
        if(lo >= hi) {
            return;
        }
        int mid = (hi - lo)/2 + lo;
        divide(list, 0, mid);
        divide(list, mid+1, hi);
        merge(list, lo, mid, hi);
    }

    public static void merge(LinkedList<Integer> list, int lo, int mid, int hi) {
        int j = mid + 1;

        for(int k = lo; k <= mid; k++) {
            Integer random = StdRandom.uniform(j, hi+1);
            Integer temp = list.get(k);
            list.set(k, list.get(random));
            list.set(random, temp);
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(12,23,1,43,5,65,7));
        shuffle(list);
        System.out.println(list);

    }
}