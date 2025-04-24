import java.util.HashSet;
import java.util.Set;

class PairWithGivenSum {

    boolean hasPairWithSum(int[] arr, int target) {
        Set<Integer> seen = new HashSet<>();

        for (int num : arr) {
            if (seen.contains(target - num)) {
                System.out.println("Pair found: " + num + " and " + (target - num));
                return true;
            }
            seen.add(num);
        }

        System.out.println("No pair found with sum " + target);
        return false;
    }

    public static void main(String[] args) {
        PairWithGivenSum finder = new PairWithGivenSum();
        int[] arr = {10, 15, 3, 7};
        int target = 17;

        finder.hasPairWithSum(arr, target);
    }
}
