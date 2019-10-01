package stacks_queues.task_5;


/*Permutation. Given two integer arrays of size n, design a subquadratic algorithm to determine whether one is a
permutation of the other. That is, do they contain exactly the same entries but, possibly, in a different order.*/

import java.util.Arrays;

public class Permutation {

    public static boolean isPermutation(int[] a1, int[] b1) {
        int[] a = new int[a1.length];
        int[] b = new int[b1.length];

        System.arraycopy(a1, 0, a, 0, a1.length);
        System.arraycopy(b1, 0, b, 0, b1.length);

        Arrays.sort(a);
        Arrays.sort(b);

        if(a1.length == b1.length) {
            for (int i = 0; i < a.length; i++) {
                if (a[i] != b[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int[] b = { 1, 3, 4, 6 };
        int[] c = { 1, 3, 5, 7, 9, 2, 4, 6, 8 };

        System.out.println(isPermutation(a, b));
        System.out.println(isPermutation(a, c));
        System.out.println(isPermutation(a, a));
        System.out.println(isPermutation(b, c));

    }
}
