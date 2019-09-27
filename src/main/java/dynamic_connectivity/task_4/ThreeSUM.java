package dynamic_connectivity.task_4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*3-SUM in quadratic time. Design an algorithm for the 3-SUM problem that takes time proportional to n^2
in the worst case. You may assume that you can sort the n integers in time proportional to n^2 or better.*/
public class ThreeSUM {

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            integers.add(i);
        }

        Collections.sort(integers);

        for(int i = 0; i < integers.size()-2; i++) {
            int j = i + 1;
            int k = integers.size() - 1;
            while(j < k) {
                int sum = integers.get(i) + integers.get(j) + integers.get(k);
                if(sum == 0) {
                    System.out.println("i: " + i + " j: " + j + " k: " + k);
                }
                if(sum < 0) {
                    j++;
                }
                if(sum >= 0) {
                    k--;
                }
            }
        }


    }


}
