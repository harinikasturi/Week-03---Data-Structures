import java.util.Arrays;

public class FibonacciComparison {

    public static int fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }


    public static int fibonacciIterative(int n) {
        if (n <= 1) return n;

        int a = 0, b = 1, sum;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }


    private static int[] memo;
    public static int fibonacciMemoization(int n) {
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return fibMemoHelper(n);
    }

    private static int fibMemoHelper(int n) {
        if (n <= 1) return n;
        if (memo[n] != -1) return memo[n];
        memo[n] = fibMemoHelper(n - 1) + fibMemoHelper(n - 2);
        return memo[n];
    }

    public static void main(String[] args) {
        int n = 40;
        long start = System.nanoTime();
        int recursiveResult = fibonacciRecursive(n);
        long end = System.nanoTime();
        System.out.println("Recursive: " + (end - start) / 1_000_000.0 + " ms");


        start = System.nanoTime();
        int iterativeResult = fibonacciIterative(n);
        end = System.nanoTime();
        System.out.println("Iterative: " + (end - start) / 1_000_000.0 + " ms");

        start = System.nanoTime();
        int memoResult = fibonacciMemoization(n);
        end = System.nanoTime();
        System.out.println("Memoization: " + (end - start) / 1_000_000.0 + " ms");
    }
}