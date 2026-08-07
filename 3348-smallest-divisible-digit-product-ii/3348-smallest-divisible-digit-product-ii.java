class Solution {
    private static final int[] PRIMES = {2, 3, 5, 7};

    public String smallestNumber(String num, long t) {
        int[] e = new int[4];
        long rest = t;
        for (int j = 0; j < 4; j++)
            while (rest % PRIMES[j] == 0) { rest /= PRIMES[j]; e[j]++; }
        if (rest != 1) return "-1";                 
        int n = num.length();

        int[][] df = new int[10][4];
        for (int d = 2; d <= 9; d++) {
            int x = d;
            for (int j = 0; j < 4; j++)
                while (x % PRIMES[j] == 0) { x /= PRIMES[j]; df[d][j]++; }
        }

        byte[][] cnt = new byte[4][n + 1];
        for (int j = 0; j < 4; j++) cnt[j][0] = (byte) e[j];

        int firstZero = n;
        for (int i = 0; i < n; i++) {
            int g = num.charAt(i) - '0';
            if (g == 0) { firstZero = i; break; }
            for (int j = 0; j < 4; j++)
                cnt[j][i + 1] = (byte) Math.max(0, cnt[j][i] - df[g][j]);
        }

        if (firstZero == n && cnt[0][n] == 0 && cnt[1][n] == 0
                           && cnt[2][n] == 0 && cnt[3][n] == 0)
            return num;

        int[] r = new int[4];
        for (int i = Math.min(firstZero, n - 1); i >= 0; i--) {
            int cur = num.charAt(i) - '0';
            int avail = n - 1 - i;
            for (int d = cur + 1; d <= 9; d++) {
                for (int j = 0; j < 4; j++)
                    r[j] = Math.max(0, cnt[j][i] - df[d][j]);
                if (minLen(r) <= avail) {
                    String suf = build(r);
                    StringBuilder sb = new StringBuilder(n);
                    sb.append(num, 0, i).append((char) ('0' + d));
                    for (int k = suf.length(); k < avail; k++) sb.append('1');
                    return sb.append(suf).toString();
                }
            }
        }

        String suf = build(e);
        int L = Math.max(n + 1, suf.length());
        StringBuilder sb = new StringBuilder(L);
        for (int k = suf.length(); k < L; k++) sb.append('1');
        return sb.append(suf).toString();
    }

    private int minLen(int[] r) {
        int a = r[0], b = r[1];
        int len = r[2] + r[3];              
        len += b / 2; b %= 2;              
        len += a / 3; a %= 3;               
        if (b == 1 && a >= 1) { len++; b = 0; a--; }  
        if (a == 2) { len++; a = 0; }      
        if (b == 1) len++;                  
        if (a == 1) len++;                 
        return len;
    }

    private String build(int[] r) {
        int a = r[0], b = r[1], c = r[2], d7 = r[3];
        int nine = b / 2; b %= 2;
        int eight = a / 3; a %= 3;
        int six = 0, four = 0, three = 0, two = 0;
        if (b == 1 && a >= 1) { six = 1; b = 0; a--; }
        if (a == 2) { four = 1; a = 0; }
        if (b == 1) three = 1;
        if (a == 1) two = 1;

        StringBuilder sb = new StringBuilder();
        rep(sb, '2', two); rep(sb, '3', three); rep(sb, '4', four);
        rep(sb, '5', c);   rep(sb, '6', six);   rep(sb, '7', d7);
        rep(sb, '8', eight); rep(sb, '9', nine);
        return sb.toString();
    }

    private void rep(StringBuilder sb, char ch, int k) {
        for (int i = 0; i < k; i++) sb.append(ch);
    }
}