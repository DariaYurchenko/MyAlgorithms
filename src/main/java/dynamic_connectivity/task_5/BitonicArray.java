package dynamic_connectivity.task_5;

/*Search in a bitonic array. An array is bitonic if it is comprised of an increasing sequence of integers followed
immediately by a decreasing sequence of integers. Write a program that, given a bitonic array of n distinct integer
values, determines whether a given integer is in the array.
Standard version: Use ∼3lgn compares in the worst case.
Signing bonus: Use ∼2lgn compares in the worst case (and prove that no algorithm can guarantee to perform fewer than
∼2lgn compares in the worst case).*/



public class BitonicArray {

    public static int findPeak(int[] arr, int start, int end) {
        int mid = (start + end)/2;
        while(start <= end) {
            if (arr[mid + 1] > arr[mid] && arr[mid - 1] < arr[mid]) {
                return findPeak(arr, mid + 1, end);
            }
            if (arr[mid + 1] < arr[mid] && arr[mid - 1] > arr[mid]) {
                return findPeak(arr, start, mid - 1);
            }
            if (arr[mid + 1] < arr[mid] && arr[mid - 1] < arr[mid]) {
                return mid;
            }
        }
        throw new RuntimeException("It's not a bitonic array!");
    }

    public static boolean findItem(int[] arr, int item, int peak) {
        return bitonicSearch(arr, 0, peak, item, false) || bitonicSearch(arr, peak, arr.length-1, item, true);
    }

    public static boolean bitonicSearch(int arr[], int start, int end, int item, boolean desc) {
        int mid = (end + start)/2;
        if(arr[mid] == item) {
            return true;
        }
        if(start > end) {
            return false;
        }
        if((desc && arr[mid] < item) || (!desc && arr[mid] > item)) {
            return bitonicSearch(arr, start, mid -1, item, desc);
        }
        else {
            return bitonicSearch(arr, mid + 1, end, item, desc);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1,2,3,4,9,7,8,6};
        int peak = findPeak(arr, 0, arr.length);
        System.out.println(findItem(arr, 3, peak));
    }

}