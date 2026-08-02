import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates); // sort to handle duplicates
        backtrack(0, target, candidates, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int start, int target, int[] candidates,
                           List<Integer> curr, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // Skip duplicates
            if (i > start && candidates[i] == candidates[i - 1]) continue;

            if (candidates[i] > target) break; // no need to continue if too big

            // choose candidates[i]
            curr.add(candidates[i]);
            backtrack(i + 1, target - candidates[i], candidates, curr, res);
            curr.remove(curr.size() - 1); // backtrack
        }
    }
}
