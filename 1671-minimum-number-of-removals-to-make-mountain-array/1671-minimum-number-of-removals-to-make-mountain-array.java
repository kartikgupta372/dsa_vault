class Solution {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;

        int[] left = new int[n];
        int[] right = new int[n];

        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    left[i] = Math.max(left[i], left[j] + 1);
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (nums[j] < nums[i]) {
                    right[i] = Math.max(right[i], right[j] + 1);
                }
            }
        }

        int maxMountain = 0;

        for (int i = 0; i < n; i++) {
            if (left[i] > 1 && right[i] > 1) {
                maxMountain = Math.max(
                    maxMountain,
                    left[i] + right[i] - 1
                );
            }
        }

        return n - maxMountain;
    }
}