import java.util.HashMap;

class Solution {
    public String minWindow(String s, String t) {
        int n = s.length();
        int m = t.length();
        if (m > n) return "";

        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < m; i++) {
            char c = t.charAt(i);
            hm.put(c, hm.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        int count = 0;
        HashMap<Character, Integer> window = new HashMap<>();

        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);

            window.put(c, window.getOrDefault(c, 0) + 1);

            if (hm.containsKey(c) &&
                window.get(c) <= hm.get(c)) {
                count++;
            }
            while (count == m) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                char ch = s.charAt(left);
                window.put(ch, window.get(ch) - 1);

                if (hm.containsKey(ch) &&
                    window.get(ch) < hm.get(ch)) {
                    count--;
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE
                ? ""
                : s.substring(start, start + minLen);
    }
}