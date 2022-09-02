package Baekjoon.Review;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class P_11724 {

    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = line[0], m = line[1];

        visited = new boolean[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int v1 = line[0], v2 = line[1];
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        int cycle = 0;
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfs(i);
                cycle++;
            }
        }

        bw.write(Integer.toString(cycle));
        bw.flush();
    }

    private static void dfs(int idx) {
        visited[idx] = true;
        for (Integer integer : graph[idx]) {
            if (!visited[integer]) dfs(integer);
        }
    }
}
