import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int maxEnergy) {
        int rows = classroom.length;
        int cols = classroom[0].length();

        int startRow = 0;
        int startCol = 0;
        int litterCount = 0;

        int[][] litterId = new int[rows][cols];

        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                char cell = classroom[row].charAt(col);

                if (cell == 'S') {
                    startRow = row;
                    startCol = col;
                }

                if (cell == 'L') {
                    litterId[row][col] = litterCount++;
                }
            }
        }

        int allLitterMask = (1 << litterCount) - 1;

        boolean[][][][] visited =
            new boolean[rows][cols][maxEnergy + 1][1 << litterCount];

        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{
            startRow,
            startCol,
            maxEnergy,
            0
        });

        visited[startRow][startCol][maxEnergy][0] = true;

        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        int moves = 0;

        while (!queue.isEmpty()) {
            int statesAtCurrentMove = queue.size();

            while (statesAtCurrentMove-- > 0) {
                int[] current = queue.poll();

                int row = current[0];
                int col = current[1];
                int remainingEnergy = current[2];
                int collectedLitterMask = current[3];

                if (collectedLitterMask == allLitterMask) {
                    return moves;
                }

                for (int[] direction : directions) {
                    int newRow = row + direction[0];
                    int newCol = col + direction[1];

                    if (newRow < 0 || newRow >= rows ||
                        newCol < 0 || newCol >= cols) {
                        continue;
                    }

                    if (classroom[newRow].charAt(newCol) == 'X') {
                        continue;
                    }

                    if (remainingEnergy == 0) {
                        continue;
                    }

                    int newEnergy = remainingEnergy - 1;
                    int newLitterMask = collectedLitterMask;

                    if (classroom[newRow].charAt(newCol) == 'L') {
                        int litterNumber = litterId[newRow][newCol];
                        newLitterMask |= (1 << litterNumber);
                    }

                    if (classroom[newRow].charAt(newCol) == 'R') {
                        newEnergy = maxEnergy;
                    }

                    if (!visited[newRow][newCol][newEnergy][newLitterMask]) {
                        visited[newRow][newCol][newEnergy][newLitterMask] = true;

                        queue.offer(new int[]{
                            newRow,
                            newCol,
                            newEnergy,
                            newLitterMask
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}