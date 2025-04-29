public class CountingSort {
    public static void countingSort(int[] arr) {
        int max = 18, min = 10;
        int range = max - min + 1;
        int[] count = new int[range];
        int[] output = new int[arr.length];

        for (int value : arr) {
            count[value - min]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    public static void main(String[] args) {
        int[] studentAges = {12, 15, 11, 16, 14, 12, 15, 17, 10};
        System.out.println("Original array: " + args.toString());
        countingSort(studentAges);
        System.out.println("Sorted array: " + args.toString());
    }
}