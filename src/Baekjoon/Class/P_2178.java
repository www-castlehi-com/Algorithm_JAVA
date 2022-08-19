package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P_2178 {

    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        map = new int[info[0]][info[1]];
        for (int i = 0; i < info[0]; i++) map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        visited = new boolean[info[0]][info[1]];

        bfs(0, 0);

        bw.write(Integer.toString(map[info[0] - 1][info[1] - 1]));
        bw.flush();
    }

    public static void bfs(int x, int y) {
        int[] dx = {0, 1, -1, 0};
        int[] dy = {1, 0, 0, -1};

        Queue<int []> queue = new LinkedList<int []>();
        queue.add(new int[] {x, y});

        while (!queue.isEmpty()) {
            int[] info = queue.remove();

            for (int i = 0; i < 4; i++) {
                int x_idx = info[0] + dx[i];
                int y_idx = info[1] + dy[i];

                if (x_idx < 0 || y_idx < 0 || x_idx >= map.length || y_idx >= map[x_idx].length) continue;

                if (!visited[x_idx][y_idx] && map[x_idx][y_idx] == 1) {
                    visited[x_idx][y_idx] = true;
                    queue.add(new int[] {x_idx, y_idx});
                    map[x_idx][y_idx] = map[info[0]][info[1]] + 1;
                }
            }
        }
    }
}
