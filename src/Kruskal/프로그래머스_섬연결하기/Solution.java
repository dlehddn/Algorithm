package Kruskal.프로그래머스_섬연결하기;

import java.util.*;

class Solution {
    static int[] parent;

    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, Comparator.comparingInt(c -> c[2]));
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int result = 0;
        for (int[] cost: costs) {
            int groupA = find(cost[0]);
            int groupB = find(cost[1]);
            if (groupA != groupB) {
                result += cost[2];
                union(cost[0], cost[1]);
            }
        }
        return result;
    }

    static int find(int a) {
        if (parent[a] == a) return a;

        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA < parentB) {
            parent[parentB] = parentA;
        } else {
            parent[parentA] = parentB;
        }
    }
}