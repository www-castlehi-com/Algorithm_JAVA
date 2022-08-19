package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class P_1389 {

    static ArrayList<Integer>[] graph;
    static int[][] kevinBacon;
    static boolean[][] visited;
    static int min = Integer.MAX_VALUE;
    static int popular = 0;

    public static class KevinBacon {
        int idx;
        int value;

        public KevinBacon(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0], m = arr[1];

        kevinBacon = new int[n + 1][n + 1];
        visited = new boolean[n + 1][n + 1];

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph[info[0]].add(info[1]);
            graph[info[1]].add(info[0]);
        }

        for (int i = 1; i <= n; i++) bfs(i);

        bw.write(Integer.toString(popular));
        bw.flush();
    }

    private static void bfs(int idx) {
        LinkedList<KevinBacon> queue = new LinkedList<>();
        queue.add(new KevinBacon(idx, 0));

        while (!queue.isEmpty()) {
            KevinBacon poll = queue.poll();

            for (int integer : graph[poll.idx]) {
                if (!visited[idx][integer]) {
                    kevinBacon[idx][integer] = poll.value + 1;
                    visited[idx][integer] = true;
                    queue.add(new KevinBacon(integer, poll.value + 1));
                }
            }
        }

        int result = 0;
        for (int i = 1; i < kevinBacon.length; i++) {
            if (i != idx) {
                result += kevinBacon[idx][i];
            }
        }

        if (min > result) {
            min = result;
            popular = idx;
        }
    }
}
