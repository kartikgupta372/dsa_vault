class Solution {
    public void helper(int[][] image, int sr, int sc, int color, boolean[][] vis, int orgcol) {
        // Base conditions
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length)
            return;
        if (vis[sr][sc] || image[sr][sc] != orgcol)
            return;

        // Mark visited and change color
        vis[sr][sc] = true;
        image[sr][sc] = color;

        // Explore 4 directions
        helper(image, sr, sc - 1, color, vis, orgcol); // left
        helper(image, sr, sc + 1, color, vis, orgcol); // right
        helper(image, sr - 1, sc, color, vis, orgcol); // up
        helper(image, sr + 1, sc, color, vis, orgcol); // down
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int orgcol = image[sr][sc];
        if (orgcol == color) return image; // No need to fill if same color

        boolean[][] vis = new boolean[image.length][image[0].length];
        helper(image, sr, sc, color, vis, orgcol);
        return image;
    }
}
