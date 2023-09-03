package Goorm.Challenge.Week4;

import java.io.*;
import java.util.*;

public class Day16 {

    static int n, m;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1];
        boolean[][] matrix = new boolean[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            matrix[line[0]][line[1]] = true;
        }

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (matrix[i][j] && matrix[j][i]) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }

        visited = new boolean[n + 1];
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                cnt++;
                bfs(i);
            }
        }

        bw.write(Integer.toString(cnt));
        bw.flush();
    }

    private static void bfs(int start) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            if (!visited[poll]) {
                visited[poll] = true;

                for (Integer integer : graph[poll]) {
                    if (!visited[integer]) queue.add(integer);
                }
            }
        }
    }
}
