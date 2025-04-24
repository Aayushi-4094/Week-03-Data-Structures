import java.util.Stack;

class SortStack {
    
    void sort(Stack<Integer> stack) {
        if (!stack.isEmpty()) {
            int temp = stack.pop();
            sort(stack);
            insertInSortedOrder(stack, temp);
        }
    }

    void insertInSortedOrder(Stack<Integer> stack, int element) {
        if (stack.isEmpty() || stack.peek() <= element) {
            stack.push(element);
        } else {
            int temp = stack.pop();
            insertInSortedOrder(stack, element);
            stack.push(temp);
        }
    }

    void printStack(Stack<Integer> stack) {
        for (int num : stack) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SortStack sortStack = new SortStack();
        Stack<Integer> stack = new Stack<>();
        
        stack.push(30);
        stack.push(10);
        stack.push(50);
        stack.push(20);
        stack.push(40);

        System.out.println("Original Stack:");
        sortStack.printStack(stack);

        sortStack.sort(stack);

        System.out.println("Sorted Stack:");
        sortStack.printStack(stack);
    }
}
