package Baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P_7562 {

    static int l;
    static int[] cur_info;
    static int[] target_info;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < n; i++) {
            l = Integer.parseInt(br.readLine());
            cur_info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            target_info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            map = new int[l][l];
            visited = new boolean[l][l];

            bfs();

            bw.write(map[target_info[0]][target_info[1]] + "\n");
        }

        bw.flush();
    }

    public static void bfs() {
        int[] x_set = {2, 2, -2, -2, 1, 1, -1, -1};
        int[] y_set = {1, -1, 1, -1, 2, -2, 2, -2};

        Queue<int[]> queue = new LinkedList<>();

        queue.add(cur_info);
        visited[cur_info[0]][cur_info[1]] = true;

        while (!queue.isEmpty()) {
            int[] info = queue.remove();

            for (int i = 0; i < 8; i++) {
                int x_idx = info[0] + x_set[i];
                int y_idx = info[1] + y_set[i];

                if (x_idx < 0 || y_idx < 0 || x_idx >= l || y_idx >= l) continue;

                if (!visited[x_idx][y_idx]) {
                    queue.add(new int[] {x_idx, y_idx});
                    visited[x_idx][y_idx] = true;
                    map[x_idx][y_idx] = map[info[0]][info[1]] + 1;
                }
            }
        }
    }
}
