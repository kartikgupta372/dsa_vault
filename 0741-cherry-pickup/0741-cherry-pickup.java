class Solution {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] dp = new int[n][n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1000000);
            }
        }

        dp[0][0][0] = grid[0][0];

        for (int r1 = 0; r1 < n; r1++) {
            for (int c1 = 0; c1 < n; c1++) {

                if (grid[r1][c1] == -1)
                    continue;

                for (int r2 = 0; r2 < n; r2++) {

                    int c2 = r1 + c1 - r2;

                    if (c2 < 0 || c2 >= n || grid[r2][c2] == -1)
                        continue;

                    if (r1 == 0 && c1 == 0)
                        continue;

                    int best = -1000000;

                    if (r1 > 0 && r2 > 0)
                        best = Math.max(best, dp[r1-1][c1][r2-1]);

                    if (r1 > 0 && c2 > 0)
                        best = Math.max(best, dp[r1-1][c1][r2]);

                    if (c1 > 0 && r2 > 0)
                        best = Math.max(best, dp[r1][c1-1][r2-1]);

                    if (c1 > 0 && c2 > 0)
                        best = Math.max(best, dp[r1][c1-1][r2]);

                    if (best == -1000000)
                        continue;

                    int cherries = grid[r1][c1];

                    if (r1 != r2 || c1 != c2)
                        cherries += grid[r2][c2];

                    dp[r1][c1][r2] = best + cherries;
                }
            }
        }

        return Math.max(0, dp[n-1][n-1][n-1]);
    }
}