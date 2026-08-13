class Solution {
    int[] left, right, best;
    char[] s;

    public int[] longestRepeating(String str, String queryCharacters, int[] queryIndices) {
        s = str.toCharArray();

        int n = s.length;

        left = new int[4 * n];
        right = new int[4 * n];
        best = new int[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {
            int index = queryIndices[i];
            s[index] = queryCharacters.charAt(i);

            update(1, 0, n - 1, index);

            ans[i] = best[1];
        }

        return ans;
    }

    private void build(int node, int l, int r) {
        if (l == r) {
            left[node] = right[node] = best[node] = 1;
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        merge(node, l, r);
    }

    private void update(int node, int l, int r, int index) {
        if (l == r) {
            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index);
        } else {
            update(node * 2 + 1, mid + 1, r, index);
        }

        merge(node, l, r);
    }

    private void merge(int node, int l, int r) {
        int mid = (l + r) / 2;

        int leftNode = node * 2;
        int rightNode = node * 2 + 1;

        left[node] = left[leftNode];
        right[node] = right[rightNode];

        best[node] = Math.max(best[leftNode], best[rightNode]);

        if (s[mid] == s[mid + 1]) {
            best[node] = Math.max(best[node],
                    right[leftNode] + left[rightNode]);
        }

        if (left[leftNode] == mid - l + 1 && s[mid] == s[mid + 1]) {
            left[node] += left[rightNode];
        }

        if (right[rightNode] == r - mid && s[mid] == s[mid + 1]) {
            right[node] += right[leftNode];
        }
    }
}