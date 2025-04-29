import java.util.*;

public class DataStructureSearch {
    public static void main(String[] args) {
        int N = 1_000_000;
        int target = N - 1;

        int[] array = new int[N];
        for (int i = 0; i < N; i++) array[i] = i;

        long start = System.nanoTime();
        boolean found = false;
        for (int num : array) {
            if (num == target) {
                found = true;
                break;
            }
        }
        long end = System.nanoTime();
        System.out.println("Array search: " + (end - start) / 1_000_000.0 + " ms");


        Set<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < N; i++) hashSet.add(i);

        start = System.nanoTime();
        found = hashSet.contains(target);
        end = System.nanoTime();
        System.out.println("HashSet search: " + (end - start) / 1_000_000.0 + " ms");

        Set<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < N; i++) treeSet.add(i);

        start = System.nanoTime();
        found = treeSet.contains(target);
        end = System.nanoTime();
        System.out.println("TreeSet search: " + (end - start) / 1_000_000.0 + " ms");
    }
}