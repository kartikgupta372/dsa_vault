class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        char[] ans = new char[n];
        int i = 0;

        while (i < n) {
            int idx = target.charAt(i) - 'a';

            if (freq[idx] == 0) {
                break;
            }

            ans[i] = target.charAt(i);
            freq[idx]--;
            i++;
        }

        if (i < n) {
            char bigger = getNextGreater(target.charAt(i), freq);

            if (bigger != '#') {
                ans[i] = bigger;
                freq[bigger - 'a']--;
                fill(ans, i + 1, freq);
                return new String(ans);
            }
        }

        int j = i - 1;

        if (i == n) {
            j = n - 1;
        }

        while (j >= 0) {
            freq[ans[j] - 'a']++;

            char bigger = getNextGreater(target.charAt(j), freq);

            if (bigger != '#') {
                ans[j] = bigger;
                freq[bigger - 'a']--;
                fill(ans, j + 1, freq);
                return new String(ans);
            }

            j--;
        }

        return "";
    }

    private char getNextGreater(char c, int[] freq) {
        for (char ch = (char)(c + 1); ch <= 'z'; ch++) {
            if (freq[ch - 'a'] > 0) {
                return ch;
            }
        }
        return '#';
    }

    private void fill(char[] ans, int pos, int[] freq) {
        for (char c = 'a'; c <= 'z'; c++) {
            while (freq[c - 'a'] > 0) {
                ans[pos++] = c;
                freq[c - 'a']--;
            }
        }
    }
}