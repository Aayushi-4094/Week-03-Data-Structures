import java.util.Stack;

class StockSpan {
    
    void calculateSpan(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        int[] span = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) {
                stack.pop();
            }
            span[i] = (stack.isEmpty()) ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        for (int s : span) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        StockSpan stockSpan = new StockSpan();
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        
        System.out.println("Stock Span:");
        stockSpan.calculateSpan(prices);
    }
}
