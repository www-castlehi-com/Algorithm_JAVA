package Baekjoon.Review;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class P_11403 {

    static int n;
    static ArrayList<Integer>[] graphList;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        graphList = new ArrayList[n];
        for (int i = 0; i < n; i++) graphList[i] = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 1) graphList[i].add(j);
            }
        }

        for (int i = 0; i < n; i++) bfs(i);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) bw.write(graph[i][j] + " ");
            bw.newLine();
        }
        bw.flush();
    }

    private static void bfs(int idx) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(idx);
        boolean[][] visited = new boolean[n][n];

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            for (Integer integer : graphList[poll]) {
                if (!visited[idx][integer]) {
                    graph[idx][integer] = 1;
                    queue.add(integer);
                    visited[idx][integer] = true;
                }
            }
        }
    }
}
