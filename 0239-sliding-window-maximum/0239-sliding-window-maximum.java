class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];

        PriorityQueue<Integer> pq =
            new PriorityQueue<>((a, b) -> Integer.compare(nums[b], nums[a]));

        int index = 0;

        for (int i = 0; i < n; i++) {

            pq.offer(i);

            while (pq.peek() <= i - k) {
                pq.poll();
            }

            if (i >= k - 1) {
                ans[index++] = nums[pq.peek()];
            }
        }

        return ans;
        
    }
}