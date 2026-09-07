class Solution {
    public int distinctSubseqII(String s) {

        int n = s.length();
        int MOD = 1000000007;
        int[][] dp = new int[n + 1][26];

        for (int i = 1; i <= n; i++) {
            int x = s.charAt(i - 1) - 'a';

            for (int j = 0; j < 26; j++) {
                dp[i][j] = dp[i - 1][j];
            }
            int sum = 1;
            for (int j = 0; j < 26; j++) {
                sum = (sum + dp[i - 1][j]) % MOD;
            }
            dp[i][x] = sum;
        }
        int ans = 0;
        for (int j = 0; j < 26; j++) {
            ans = (ans + dp[n][j]) % MOD;
        }
        return ans;
    }
}