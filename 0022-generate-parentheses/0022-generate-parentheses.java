class Solution {
    public List<String> generateParenthesis(int n) {
        List<List<String>> dp = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            dp.add(new ArrayList<>());
        }

        dp.get(0).add("");

        for (int nodes = 1; nodes <= n; nodes++) {
            for (int left = 0; left < nodes; left++) {

                int right = nodes - 1 - left;

                for (String l : dp.get(left)) {
                    for (String r : dp.get(right)) {
                        dp.get(nodes).add("(" + l + ")" + r);
                    }
                }
            }
        }

        return dp.get(n);
    }
}