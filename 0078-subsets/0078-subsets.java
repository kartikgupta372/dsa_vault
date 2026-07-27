import java.util.*;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        solve(nums, 0, curr, ans);
        return ans;
    }

    public void solve(int[] nums, int index, List<Integer> curr, List<List<Integer>> ans) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        curr.add(nums[index]);
        solve(nums, index + 1, curr, ans);

        curr.remove(curr.size() - 1);
        solve(nums, index + 1, curr, ans);
    }
}