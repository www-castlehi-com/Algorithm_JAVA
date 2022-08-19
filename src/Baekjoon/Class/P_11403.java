package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class P_11403 {

    static int[][] matrix;
    static boolean[][] visited;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        matrix = new int[n][n];
        visited = new boolean[n][n];
        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            matrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) graph[i].add(j);
            }
        }

        for (int i = 0; i < n; i++) bfs(i);

        for (int[] ints : matrix) {
            for (int anInt : ints) {
                bw.write(anInt + " ");
            }
            bw.newLine();
        }
        bw.flush();
    }

    private static void bfs(int idx) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(idx);

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            for (Integer integer : graph[poll]) {
                if (!visited[idx][integer]) {
                    visited[idx][integer] = true;
                    matrix[idx][integer] = 1;
                    queue.add(integer);
                }
            }
        }
    }
}
