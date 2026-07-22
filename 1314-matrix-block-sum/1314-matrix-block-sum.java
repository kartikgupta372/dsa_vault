class Solution {
    public int[][] matrixBlockSum(int[][] mat, int k) {
         int m = mat.length;
        int n = mat[0].length;

        int[][] prefix = new int[m][n];

        // Build prefix sum matrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int top = (i > 0) ? prefix[i - 1][j] : 0;
                int left = (j > 0) ? prefix[i][j - 1] : 0;
                int topLeft = (i > 0 && j > 0) ? prefix[i - 1][j - 1] : 0;

                prefix[i][j] = mat[i][j] + top + left - topLeft;
            }
        }

        int[][] ans = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int r1 = Math.max(0, i - k);
                int c1 = Math.max(0, j - k);

                int r2 = Math.min(m - 1, i + k);
                int c2 = Math.min(n - 1, j + k);

                int sum = prefix[r2][c2];

                if (r1 > 0)
                    sum -= prefix[r1 - 1][c2];

                if (c1 > 0)
                    sum -= prefix[r2][c1 - 1];

                if (r1 > 0 && c1 > 0)
                    sum += prefix[r1 - 1][c1 - 1];

                ans[i][j] = sum;
            }
        }

        return ans;
        
    }
}