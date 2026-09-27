class Solution {
    public int rob(int[] nums) {
        int n = nums.length;

        if (n == 1) {
            return nums[0];
        }

        return Math.max(
            solve(nums, 0, n - 2),
            solve(nums, 1, n - 1)
        );
    }

    public int solve(int[] nums, int start, int end) {
        int[] dp = new int[end - start + 2];

        dp[0] = 0;
        dp[1] = nums[start];

        for (int i = 2; i < dp.length; i++) {
            int take = nums[start + i - 1] + dp[i - 2];
            int skip = dp[i - 1];

            dp[i] = Math.max(take, skip);
        }

        return dp[dp.length - 1];
    }
}