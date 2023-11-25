package KAKAO.winterIntern2023;

import java.util.*;

class Problem2 {

    private final static int SIZE = 1_000_000;

    static ArrayList<Integer>[] graph;
    static int[] visited;

    public int[] solution(int[][] edges) {
        int[] answer = new int[4];

        int length = edges.length;
        int maxV = 0;
        int[] degree = new int[SIZE + 1];
        int maxDegree = 0;
        int maxDegreeEdge = 0;
        graph = new ArrayList[SIZE + 1];
        for (int i = 0; i <= SIZE; i++)
            graph[i] = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            int[] edge = edges[i];

            graph[edge[0]].add(edge[1]);
            degree[edge[0]]++;
            if (maxDegree < degree[edge[0]]) {
                maxDegree = degree[edge[0]];
                maxDegreeEdge = edge[0];
            }

            maxV = Math.max(maxV, edge[0]);
            maxV = Math.max(maxV, edge[1]);
        }
        answer[0] = maxDegreeEdge;

        visited = new int[maxV + 1];
        for (int e : graph[maxDegreeEdge]) {
            if (visited[e] == 0) {
                int cycle = checkCycle(e);
                if (cycle == 1) answer[1]++;
                else if (cycle == 2) answer[3]++;
                else answer[2]++;
            }
        }

        return answer;
    }

    private int checkCycle(int cur) {
        if (visited[cur] == 1) return 1;
        else if (visited[cur] == 2) return 0;

        visited[cur] = 1;

        int cycle = 0;
        for (int next : graph[cur]) {
            cycle += checkCycle(next);
        }

        visited[cur] = 2;

        return cycle;
    }
}