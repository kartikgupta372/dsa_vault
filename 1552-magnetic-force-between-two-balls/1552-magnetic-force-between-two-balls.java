class Solution {

    public int maxDistance(int[] position, int m) {

        Arrays.sort(position);

        int left = 1;
        int right = position[position.length - 1] - position[0];
        int ans = 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (countBalls(position, mid) >= m) {
                ans = mid;
                left = mid + 1;  
            } else {
                right = mid - 1;  
            }
        }

        return ans;
    }

    private int countBalls(int[] position, int dist) {

        int balls = 1;
        int lastPos = position[0];

        for (int i = 1; i < position.length; i++) {

            if (position[i] - lastPos >= dist) {
                balls++;
                lastPos = position[i];
            }
        }

        return balls;
    }
}