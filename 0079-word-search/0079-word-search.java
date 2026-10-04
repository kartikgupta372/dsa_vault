class Solution {
    public boolean exist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (traverse(board, word, i, j, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean traverse(char[][] board, String word, int i, int j, int index) {

        if (index == word.length()) {
            return true;
        }

        int n = board.length;
        int m = board[0].length;

        if (i < 0 || i >= n || j < 0 || j >= m ||
            board[i][j] != word.charAt(index)) {
            return false;
        }

        char temp = board[i][j];
        board[i][j] = '#';

        boolean found =
            traverse(board, word, i + 1, j, index + 1) ||
            traverse(board, word, i - 1, j, index + 1) ||
            traverse(board, word, i, j + 1, index + 1) ||
            traverse(board, word, i, j - 1, index + 1);

        board[i][j] = temp;

        return found;
    }
}