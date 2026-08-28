class Solution {

    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();
        int[] cnt = new int[26];
        for (char ch : s.toCharArray()) {
            cnt[ch - 'a']++;
        }
        int odd = 0;
        int middle = -1;

        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 == 1) {
                odd++;
                middle = i;
            }
        }

        if (odd > 1) {
            return "";
        }
        int[] half = new int[26];
        for (int i = 0; i < 26; i++) {
            half[i] = cnt[i] / 2;
        }
        int leftLen = n / 2;
        StringBuilder left = new StringBuilder();
        for (int pos = 0; pos < leftLen; pos++) {
            boolean found = false;
            for (int c = 0; c < 26; c++) {

                if (half[c] == 0) {
                    continue;
                }

                half[c]--;
                left.append((char) ('a' + c));

                if (canMakeGreater(left, half, middle, target)) {
                    found = true;
                    break;
                }
                left.deleteCharAt(left.length() - 1);
                half[c]++;
            }

            if (!found) {
                return "";
            }
        }
        StringBuilder ans = new StringBuilder(left);
        if (middle != -1) {
            ans.append((char) ('a' + middle));
        }
        ans.append(new StringBuilder(left).reverse());

        return ans.toString().compareTo(target) > 0
                ? ans.toString()
                : "";
    }
    private boolean canMakeGreater(
            StringBuilder prefix,
            int[] half,
            int middle,
            String target) {
        StringBuilder remaining = new StringBuilder();
        for (int c = 25; c >= 0; c--) {
            for (int k = 0; k < half[c]; k++) {
                remaining.append((char) ('a' + c));
            }
        }
        StringBuilder fullLeft = new StringBuilder(prefix);
        fullLeft.append(remaining);
        StringBuilder candidate = new StringBuilder();
        candidate.append(fullLeft);
        if (middle != -1) {
            candidate.append((char) ('a' + middle));
        }
        candidate.append(
                new StringBuilder(fullLeft).reverse()
        );
        return candidate.toString().compareTo(target) > 0;
    }
}