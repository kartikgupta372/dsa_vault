import java.util.*;

class Solution {
    private static class Result {
        long score;
        List<Integer> indices;

        Result(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }

        order = Arrays.stream(order).sorted((a, b) -> {
            int startA = intervals.get(a).get(0);
            int startB = intervals.get(b).get(0);

            if (startA != startB) {
                return Integer.compare(startA, startB);
            }

            return Integer.compare(
                intervals.get(a).get(1),
                intervals.get(b).get(1)
            );
        }).toArray(Integer[]::new);

        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            int end = intervals.get(order[i]).get(1);
            int left = i + 1;
            int right = n;

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (intervals.get(order[mid]).get(0) > end) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            next[i] = left;
        }

        Result[][] dp = new Result[n + 1][5];

        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new Result(0, new ArrayList<>());
        }

        for (int i = n - 1; i >= 0; i--) {
            int originalIndex = order[i];
            int weight = intervals.get(originalIndex).get(2);

            for (int k = 0; k <= 4; k++) {
                Result skip = dp[i + 1][k];

                if (k == 0) {
                    dp[i][k] = skip;
                    continue;
                }

                Result afterTake = dp[next[i]][k - 1];
                List<Integer> indices = new ArrayList<>(afterTake.indices);
                indices.add(originalIndex);
                Collections.sort(indices);

                Result take = new Result(
                    afterTake.score + weight,
                    indices
                );

                dp[i][k] = better(skip, take);
            }
        }

        List<Integer> answer = dp[0][4].indices;
        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    private Result better(Result a, Result b) {
        if (a.score != b.score) {
            return a.score > b.score ? a : b;
        }

        int size = Math.min(a.indices.size(), b.indices.size());

        for (int i = 0; i < size; i++) {
            if (!a.indices.get(i).equals(b.indices.get(i))) {
                return a.indices.get(i) < b.indices.get(i) ? a : b;
            }
        }

        return a.indices.size() <= b.indices.size() ? a : b;
    }
}