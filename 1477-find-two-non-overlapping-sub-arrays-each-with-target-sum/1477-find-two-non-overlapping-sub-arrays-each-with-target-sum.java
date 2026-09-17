
import java.util.*;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = n + 1;

        int[] best = new int[n + 1];
        Arrays.fill(best, INF);
        best[0] = INF;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);

        int prefix = 0;
        int ans = INF;

        for (int i = 0; i < n; i++) {
            prefix += arr[i];
            int required = prefix - target;

            if (map.containsKey(required)) {
                int start = map.get(required);
                int len = i + 1 - start;

                if (best[start] != INF) {
                    ans = Math.min(ans, best[start] + len);
                }

                best[i + 1] = Math.min(best[i], len);
            } else {
                best[i + 1] = best[i];
            }
            map.put(prefix, i + 1);
        }
        return ans == INF ? -1 : ans;
    }
}