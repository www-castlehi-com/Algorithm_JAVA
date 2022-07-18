package Baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P_7576 {

    static int[][] map;
    static boolean[][] visited;
    static int max = 1;
    static Queue<int []> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        queue = new LinkedList<>();
        map = new int[info[1]][info[0]];
        visited = new boolean[info[1]][info[0]];
        for (int i = 0; i < info[1]; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < info[0]; j++) {
                if (map[i][j] == 1) {
                    visited[i][j] = true;
                    queue.add(new int[] {i, j});
                }
            }
        }

        bfs();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length;j ++) {
                if (map[i][j] == 0) max = 0;
            }
        }

        bw.write(Integer.toString(max - 1));
        bw.flush();
    }

    public static void bfs() {
        int[] x_set = {-1, 0, 1, 0};
        int[] y_set = {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            int[] coordinate = queue.remove();

            for (int i = 0; i < 4; i++) {
                int x_idx = coordinate[0] + x_set[i];
                int y_idx = coordinate[1] + y_set[i];

                if (x_idx < 0 || y_idx < 0 || x_idx >= map.length || y_idx >= map[x_idx].length) continue;

                if (map[x_idx][y_idx] == 0 && !visited[x_idx][y_idx]) {
                    visited[x_idx][y_idx] = true;
                    map[x_idx][y_idx] = max = map[coordinate[0]][coordinate[1]] + 1;
                    queue.add(new int[]{x_idx, y_idx});
                }
            }
        }
    }
}
