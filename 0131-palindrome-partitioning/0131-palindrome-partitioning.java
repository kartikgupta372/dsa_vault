class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        List<String> curr = new ArrayList<>();
        solve(s, 0, curr, ans);
        return ans;
    }

    private void solve(String s, int index, List<String> curr, List<List<String>> ans) {
        if (index == s.length()) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        for (int i = index; i < s.length(); i++) {
            if (palindrome(s, index, i)) {
                curr.add(s.substring(index, i + 1));
                solve(s, i + 1, curr, ans);
                curr.remove(curr.size() - 1);
            }
        }
    }

    private boolean palindrome(String s, int left, int right) {

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }
        return true;
    }
}