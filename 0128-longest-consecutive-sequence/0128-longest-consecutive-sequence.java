import java.util.*;

class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }

        int maxLen = 0;

        for (int i : set) {
            // check if it's the start of a sequence
            if (!set.contains(i - 1)) {
                int current = i;
                int length = 1;

                while (set.contains(current + 1)) {
                    current++;
                    length++;
                }

                maxLen = Math.max(maxLen, length);
            }
        }

        return maxLen;
    }
}