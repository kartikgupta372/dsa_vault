class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];

        Arrays.fill(dp, -1);

        return solve(cost, n, dp);
    }

    public int solve(int[] cost, int n, int[] dp) {
        if (n == 0 || n == 1) {
            return 0;
        }

        if (dp[n] != -1) {
            return dp[n];
        }

        int take = cost[n - 1] + solve(cost, n - 1, dp);
        int skip = cost[n - 2] + solve(cost, n - 2, dp);

        dp[n] = Math.min(take, skip);

        return dp[n];
    }
}