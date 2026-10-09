class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        int m = 0;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                m++;
            } 
            else if (i + 1 < n && s.charAt(i + 1) == ')') {
                if (m > 0) {
                    m--;
                } else {
                    ans++;
                }
                i++;
            } 
            else {
                ans++;
                if (m > 0) {
                    m--;
                } else {
                    ans++;
                }
            }
        }

        return ans + 2 * m;
    }
}