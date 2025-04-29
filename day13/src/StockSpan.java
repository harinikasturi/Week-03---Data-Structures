import java.util.Stack;

class StockSpan {
    public static int[] calculateSpan(int[] prices) {
        int[] spans = new int[prices.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        spans[0] = 1;

        for (int i = 1; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) {
                stack.pop();
            }
            spans[i] = stack.isEmpty() ? (i + 1) : (i - stack.peek());
            stack.push(i);
        }
        return spans;
    }
}