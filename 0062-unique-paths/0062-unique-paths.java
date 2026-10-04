class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp =new int[m][n];

        for(int i=0;i<=m-1;i++){
            dp[i][n-1]=1;
        }
        for(int j=0;j<=n-1;j++){
            dp[m-1][j]=1;
        }

        for(int i=m-2;i>=0;i--){
            for(int j=n-2;j>=0;j--){
                int dr=dp[i+1][j];
                int dd= dp[i][j+1];
                dp[i][j]=dr+dd;
            }
        }
        return dp[0][0];
        
    }
}