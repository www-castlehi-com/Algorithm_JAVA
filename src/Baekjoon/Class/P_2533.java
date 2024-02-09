package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P_2533 {

    static int n;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        dp = new int[n + 1][2];
        visited = new boolean[n + 1];
        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void dfs(int idx) {
        visited[idx] = true;
        dp[idx][0] = 1;
        dp[idx][1] = 0;

        for (Integer vertex : graph[idx]) {
            if (!visited[vertex]) {
                dfs(vertex);
                dp[idx][0] += Math.min(dp[vertex][0], dp[vertex][1]);
                dp[idx][1] += dp[vertex][0];
            }
        }
    }
}
