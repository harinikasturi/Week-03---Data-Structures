public class InsertionSort {
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] employeeIDs = {1024, 1001, 1050, 1010, 1033};
        System.out.println("Original array: " + args.toString());
        insertionSort(employeeIDs);
        System.out.println("Sorted array: " + args.toString());
    }
}