package Baekjoon.Review;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class P_1260 {

    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = line[0], m = line[1], v = line[2];

        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int vertex1 = line[0], vertex2 = line[1];
            graph[vertex1].add(vertex2);
            graph[vertex2].add(vertex1);
        }
        for (int i = 1; i<= n; i++) Collections.sort(graph[i]);

        boolean[] visited = new boolean[n + 1];
        dfs(graph, visited, v);
        visited = new boolean[n + 1];
        bw.newLine();
        bfs(graph, visited, v);

        bw.flush();
    }

    private static void dfs(ArrayList<Integer>[] graph, boolean[] visited, int start) throws IOException {

        visited[start] = true;
        bw.write(start + " ");

        for (Integer integer : graph[start]) {
            if (!visited[integer]) {
                dfs(graph, visited, integer);
            }
        }
    }

    private static void bfs(ArrayList<Integer>[] graph, boolean[] visited, int start) throws IOException {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            bw.write(poll + " ");

            for (Integer integer : graph[poll]) {
                if (!visited[integer]) {
                    visited[integer] = true;
                    queue.add(integer);
                }
            }
        }
    }
}
