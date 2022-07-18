package Baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_1261 {

    static int[][] map;
    static int[][] cnt;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        cnt = new int[info[1]][info[0]];
        map = new int[info[1]][info[0]];
        for (int i = 0; i < info[1]; i++) map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        visited = new boolean[info[1]][info[0]];

        bfs();

        bw.write(Integer.toString(cnt[info[1] - 1][info[0] - 1]));
        bw.flush();
    }

    public static void bfs() {
        int[] x_set = {-1, 1, 0, 0};
        int[] y_set = {0, 0, 1, -1};
        LinkedList<int[]> queue = new LinkedList<>();

        queue.add(new int[] {0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] coordinate = queue.remove();

            for (int i = 0; i < 4; i++) {
                int x_idx = coordinate[0] + x_set[i];
                int y_idx = coordinate[1] + y_set[i];

                if (x_idx < 0 || y_idx < 0 || x_idx >= map.length || y_idx >= map[x_idx].length) continue;

                if (!visited[x_idx][y_idx]) {
                    if (map[x_idx][y_idx] == 0) {
                        queue.addFirst(new int[] {x_idx, y_idx});
                        cnt[x_idx][y_idx] = cnt[coordinate[0]][coordinate[1]];
                    }
                    else {
                        queue.add(new int[] {x_idx, y_idx});
                        cnt[x_idx][y_idx] = cnt[coordinate[0]][coordinate[1]] + 1;
                    }

                    visited[x_idx][y_idx] = true;
                }
            }
        }
    }
}
