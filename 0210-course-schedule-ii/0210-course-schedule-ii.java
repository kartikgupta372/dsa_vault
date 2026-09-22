import java.util.*;

class Solution {

    boolean isCycleDFS(int src, boolean[] vis,
                       boolean[] recPath, int[][] prerequisites) {

        vis[src] = true;
        recPath[src] = true;

        for (int i = 0; i < prerequisites.length; i++) {

            int v = prerequisites[i][0];
            int u = prerequisites[i][1];

            if (src == u) {

                if (!vis[v]) {

                    if (isCycleDFS(v, vis, recPath, prerequisites)) {
                        return true;
                    }

                } else if (recPath[v]) {
                    return true;
                }
            }
        }

        recPath[src] = false;

        return false;
    }

    void topoOrder(int src, boolean[] vis,
                   Stack<Integer> s, int[][] prerequisites) {

        vis[src] = true;

        for (int i = 0; i < prerequisites.length; i++) {

            int v = prerequisites[i][0];
            int u = prerequisites[i][1];

            if (src == u) {

                if (!vis[v]) {
                    topoOrder(v, vis, s, prerequisites);
                }
            }
        }

        s.push(src);
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        boolean[] vis = new boolean[numCourses];
        boolean[] recPath = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {

            if (!vis[i]) {

                if (isCycleDFS(i, vis, recPath, prerequisites)) {
                    return new int[0];
                }
            }
        }

        Stack<Integer> s = new Stack<>();

        Arrays.fill(vis, false);

        for (int i = 0; i < numCourses; i++) {

            if (!vis[i]) {
                topoOrder(i, vis, s, prerequisites);
            }
        }

        int[] ans = new int[numCourses];
        int index = 0;

        while (!s.isEmpty()) {
            ans[index++] = s.pop();
        }

        return ans;
    }
}