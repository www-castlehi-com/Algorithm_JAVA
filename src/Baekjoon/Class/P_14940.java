package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_14940 {

    static int n, m;
    static int[][] map;

    static class Location {
        int x, y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0];
        m = line[1];

        map = new int[n][m];
        Location start = null;

        int[][] result_map = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(result_map[i], -1);

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 2) {
                    start = new Location(j, i);
                    result_map[i][j] = 0;
                }
                else if (map[i][j] == 0) result_map[i][j] = 0;
            }
        }

        bfs(result_map, start);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) bw.write(result_map[i][j] + " ");
            bw.newLine();
        }
        bw.flush();
    }

    private static void bfs(int[][] result_map, Location start) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        boolean[][] visited = new boolean[n][m];
        visited[start.y][start.x] = true;

        LinkedList<Location> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Location poll = queue.poll();

            int y = poll.y;
            int x = poll.x;

            for (int i = 0; i < 4; i++) {
                int next_y = y + dy[i];
                int next_x = x + dx[i];

                if (next_y >= 0 && next_y < n && next_x >= 0 && next_x < m) {
                    if (map[next_y][next_x] != 0) result_map[y][x] = Math.max(result_map[next_y][next_x] + 1, result_map[y][x]);
                    else result_map[next_y][next_x] = 0;
                }
            }

            for (int i = 0; i < 4; i++) {
                int next_y = y + dy[i];
                int next_x = x + dx[i];

                if (next_y >= 0 && next_y < n && next_x >= 0 && next_x < m && !visited[next_y][next_x] && map[next_y][next_x] != 0) {
                    visited[next_y][next_x] = true;
                    queue.add(new Location(next_x, next_y));
                }
            }
        }
    }
}