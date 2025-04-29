import java.util.Arrays;
import java.util.Random;

public class SortingComparison {
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {

                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }
    }

    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) return;

        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left);
        mergeSort(right);

        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {

        int mid = low + (high - low) / 2;
        sortThree(arr, low, mid, high);
        int pivot = arr[mid];


        swap(arr, mid, high - 1);
        int i = low, j = high - 1;

        while (true) {
            while (arr[++i] < pivot);
            while (arr[--j] > pivot);
            if (i >= j) break;
            swap(arr, i, j);
        }

        swap(arr, i, high - 1);
        return i;
    }

    private static void sortThree(int[] arr, int a, int b, int c) {
        if (arr[a] > arr[b]) swap(arr, a, b);
        if (arr[b] > arr[c]) swap(arr, b, c);
        if (arr[a] > arr[b]) swap(arr, a, b);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void benchmarkSort(String sortName, Runnable sortFunction, int[] originalArray) {

        int[] arrayToSort = Arrays.copyOf(originalArray, originalArray.length);

        long startTime = System.nanoTime();
        sortFunction.run();
        long endTime = System.nanoTime();

        double durationMs = (endTime - startTime) / 1_000_000.0;
        System.out.printf("%-12s: %.2f ms | Valid: %b%n",
                sortName,
                durationMs,
                isSorted(arrayToSort));
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        int[] sizes = {1_000, 10_000, 100_000}; // Reduced for demonstration

        for (int size : sizes) {
            System.out.println("\nDataset size: " + size);
            int[] originalArray = generateRandomArray(size);

            benchmarkSort("Bubble Sort", () -> bubbleSort(originalArray.clone()), originalArray);
            benchmarkSort("Merge Sort", () -> mergeSort(originalArray.clone()), originalArray);
            benchmarkSort("Quick Sort", () -> quickSort(originalArray.clone()), originalArray);
        }
    }

    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size * 10);
        }
        return arr;
    }
}