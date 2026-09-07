class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;

        int total = 0;
        for (int x : nums) {
            total += x;
        }

        if (Math.abs(target) > total) {
            return 0;
        }

        int m = 2 * total + 1;
        int offset = total;

        int[][] dp = new int[n + 1][m];

        dp[0][offset] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {

                if (dp[i - 1][j] > 0) {

                    if (j + nums[i - 1] < m) {
                        dp[i][j + nums[i - 1]] += dp[i - 1][j];
                    }

                    if (j - nums[i - 1] >= 0) {
                        dp[i][j - nums[i - 1]] += dp[i - 1][j];
                    }
                }
            }
        }

        return dp[n][target + offset];
    }
}