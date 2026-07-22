class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] prefix = new int[n];
        prefix[0] = nums[0];

        // Build prefix sum array
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        int count = 0;

        // Check all subarrays
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = prefix[j] - (i > 0 ? prefix[i - 1] : 0);
                if (sum == k) {
                    count++;
                }
            }
        }

        return count;
    }
}
