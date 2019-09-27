package my_algorithms.binary_search;

public class BinarySearch {

    public static boolean binarySearch(int[] arr, int start, int end, int item) {
        int mid = (start + end)/2;
        while(start <= end) {
            if(mid == item) {
                return true;
            }
            if(item < mid) {
                return binarySearch(arr, start, mid-1, item);
            }
            else {
                return binarySearch(arr, mid + 1, end, item);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1,2,3,4,5,6,7,8};
        System.out.println(binarySearch(arr, 0, arr.length, 67));
    }

}
