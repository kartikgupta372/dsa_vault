import java.util.*;

class Solution {
    public int minimumCost(int[] cost) {
        
        int spent = 0;
        int n = cost.length;
        
        boolean[] ans = new boolean[n];
        
        Arrays.sort(cost);
        
        int count = 0;

        for (int i = n - 1; i >= 0; i--) {
            
            if (count < 2) {
                spent += cost[i];
                ans[i] = true;
                count++;
            }
            else {
                ans[i] = true;
                count = 0;
            }
        }

        return spent;
    }
}