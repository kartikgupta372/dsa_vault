import java.util.Arrays;

class Solution {

    int[][] dp;
    int[] nums;

    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        this.nums = nums;

        dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }

        return solve(0, n - 1) >= 0;
    }

    private int solve(int i, int j) {

        if (i > j)
            return 0;

        if (dp[i][j] != Integer.MIN_VALUE)
            return dp[i][j];

        int left = nums[i] - solve(i + 1, j);
        int right = nums[j] - solve(i, j - 1);

        return dp[i][j] = Math.max(left, right);
    }
}