import java.util.*;

class Solution {
    public int splitArray(int[] nums, int k) {
        int n = nums.length;
        int maxVal = nums[0];
        int sum = 0;

        // Find max element and total sum
        for (int i = 0; i < n; i++) {
            maxVal = Math.max(nums[i], maxVal);
            sum += nums[i];
        }

        int low = maxVal;
        int high = sum;
        int ans = sum;

        // Binary Search for minimum largest subarray sum
        while (low <= high) {
            int mid = (low + high) / 2;
            if (helper(nums, k, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    public static boolean helper(int[] nums, int k, int target) {
        int count = 1;
        int val = 0;

        for (int i = 0; i < nums.length; i++) {
            val += nums[i];
            if (val > target) {
                count++;
                val = nums[i];
            }
        }

        return count <= k;
    }
}
