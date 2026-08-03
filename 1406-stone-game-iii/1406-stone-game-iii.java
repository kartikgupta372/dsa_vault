class Solution {

    Integer[] dp;

    public String stoneGameIII(int[] stoneValue) {

        dp = new Integer[stoneValue.length];

        int diff = solve(0, stoneValue);

        if (diff > 0)
            return "Alice";
        else if (diff < 0)
            return "Bob";

        return "Tie";
    }

    private int solve(int index, int[] stoneValue) {

        if (index >= stoneValue.length)
            return 0;

        if (dp[index] != null)
            return dp[index];

        int take = 0;
        int best = Integer.MIN_VALUE;

        for (int i = index; i < Math.min(index + 3, stoneValue.length); i++) {

            take += stoneValue[i];

            best = Math.max(best, take - solve(i + 1, stoneValue));
        }

        return dp[index] = best;
    }
}