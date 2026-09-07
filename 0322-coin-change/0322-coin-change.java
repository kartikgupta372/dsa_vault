class Solution {
    public int coinChange(int[] coins, int amount) {

        int n = coins.length;
        int m = amount;

        int[][] dp = new int[n + 1][m + 1];

        for(int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for(int j = 1; j <= m; j++) {
            dp[0][j] = 1000000;
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {

                if(coins[i - 1] <= j) {
                    dp[i][j] = Math.min(
                        1 + dp[i][j - coins[i - 1]],
                        dp[i - 1][j]
                    );
                } 
                else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        if(dp[n][m] == 1000000) {
            return -1;
        }

        return dp[n][m];
    }
}