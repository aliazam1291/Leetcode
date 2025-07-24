import java.util.*;

class Solution {
    int[] subtreeXOR;
    List<Integer>[] tree;
    int[] parent;
    int totalXOR;

    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        subtreeXOR = new int[n];
        parent = new int[n];
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();

        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }

        dfs(0, -1, nums);

        int minScore = Integer.MAX_VALUE;


        for (int i = 0; i < edges.length; i++) {
            int a1 = edges[i][0], b1 = edges[i][1];
            int child1 = parent[a1] == b1 ? a1 : b1;

            for (int j = i + 1; j < edges.length; j++) {
                int a2 = edges[j][0], b2 = edges[j][1];
                int child2 = parent[a2] == b2 ? a2 : b2;

                int xor1, xor2, xor3;

                if (isAncestor(child1, child2)) {
                    xor2 = subtreeXOR[child2];
                    xor1 = subtreeXOR[child1] ^ xor2;
                    xor3 = totalXOR ^ subtreeXOR[child1];
                } else if (isAncestor(child2, child1)) {
                    xor1 = subtreeXOR[child1];
                    xor2 = subtreeXOR[child2] ^ xor1;
                    xor3 = totalXOR ^ subtreeXOR[child2];
                } else {
                    xor1 = subtreeXOR[child1];
                    xor2 = subtreeXOR[child2];
                    xor3 = totalXOR ^ xor1 ^ xor2;
                }

                int max = Math.max(xor1, Math.max(xor2, xor3));
                int min = Math.min(xor1, Math.min(xor2, xor3));
                minScore = Math.min(minScore, max - min);
            }
        }

        return minScore;
    }

  
    private int dfs(int node, int par, int[] nums) {
        parent[node] = par;
        int xor = nums[node];
        for (int nei : tree[node]) {
            if (nei != par) {
                xor ^= dfs(nei, node, nums);
            }
        }
        subtreeXOR[node] = xor;
        totalXOR = subtreeXOR[0];
        return xor;
    }

    private boolean isAncestor(int u, int v) {
        while (v != -1) {
            if (v == u) return true;
            v = parent[v];
        }
        return false;
    }
}
