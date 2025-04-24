import java.util.HashMap;
import java.util.Map;

class TwoSum {

    int[] findTwoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            map.put(nums[i], i);
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        TwoSum solver = new TwoSum();
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] result = solver.findTwoSum(nums, target);
        System.out.println("Indices: " + result[0] + ", " + result[1]);
    }
}
