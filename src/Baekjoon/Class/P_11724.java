package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class P_11724 {

    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        graph = new ArrayList[info[0] + 1];
        for (int i = 0; i <= info[0]; i++) graph[i] = new ArrayList<>();
        visited = new boolean[info[0] + 1];
        int count = 0;

        for (int i = 0; i < info[1]; i++) {
            int[] edges = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            graph[edges[0]].add(edges[1]);
            graph[edges[1]].add(edges[0]);
        }

        for (int i = 1; i <= info[0]; i++) {
            if (!visited[i]) {
                dfs(i);
                count++;
            }
        }

        bw.write(Integer.toString(count));
        bw.flush();
    }

    public static void dfs(int node) {
        visited[node] = true;
        for (int v : graph[node]) {
            if (!visited[v]) dfs(v);
        }
    }
}
