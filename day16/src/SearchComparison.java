import java.util.Arrays;

public class SearchComparison {

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }


    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] largeArray = new int[1_000_000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i;
        }
        int target = 999_999;


        long start = System.nanoTime();
        int linearResult = linearSearch(largeArray, target);
        long end = System.nanoTime();
        System.out.println("Linear Search: " + (end - start) / 1_000_000.0 + " ms");


        start = System.nanoTime();
        int binaryResult = binarySearch(largeArray, target);
        end = System.nanoTime();
        System.out.println("Binary Search: " + (end - start) / 1_000_000.0 + " ms");
    }
}