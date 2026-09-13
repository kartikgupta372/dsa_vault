class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;

        int[] rows1 = new int[n];
        int[] rows2 = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    rows1[i] |= 1 << j;
                }

                if (img2[i][j] == 1) {
                    rows2[i] |= 1 << j;
                }
            }
        }

        int maxOverlap = 0;

        for (int rowShift = -n + 1; rowShift < n; rowShift++) {
            for (int colShift = -n + 1; colShift < n; colShift++) {
                int overlap = 0;

                for (int row = 0; row < n; row++) {
                    int targetRow = row + rowShift;

                    if (targetRow < 0 || targetRow >= n) {
                        continue;
                    }

                    int shiftedRow;

                    if (colShift >= 0) {
                        shiftedRow = rows1[row] << colShift;
                    } else {
                        shiftedRow = rows1[row] >>> -colShift;
                    }

                    overlap += Integer.bitCount(
                        shiftedRow & rows2[targetRow]
                    );
                }

                maxOverlap = Math.max(maxOverlap, overlap);
            }
        }

        return maxOverlap;
    }
}