import java.util.Deque;
import java.util.LinkedList;

class SlidingWindowMax {
    
    void findMaxInWindows(int[] nums, int k) {
        if (nums.length == 0 || k <= 0) return;

        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            deque.offerLast(i);

            if (i >= k - 1) {
                System.out.print(nums[deque.peekFirst()] + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SlidingWindowMax swm = new SlidingWindowMax();
        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        System.out.println("Sliding Window Maximum:");
        swm.findMaxInWindows(arr, k);
    }
}
