package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_7569 {

    static int h, m, n;
    static int[][][] storage;
    static LinkedList<Ripe_tomato> queue;
    static boolean[][][] visited;
    static int min_days = 0;

    public static class Ripe_tomato {
        int x, y, z;
        int day;

        public Ripe_tomato(int x, int y, int z, int day) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.day = day;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        m = line[0]; n = line[1]; h = line[2];

        storage = new int[h][n][m];
        visited = new boolean[h][n][m];
        queue = new LinkedList<>();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                storage[i][j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                for (int k = 0; k < m; k++) if (storage[i][j][k] == 1) {
                    queue.add(new Ripe_tomato(k, j, i, 0));
                    visited[i][j][k] = true;
                }
            }
        }

        bfs();

        boolean all_ripe = true;
        Loop :
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (storage[i][j][k] == 0) {
                        all_ripe = false;
                        break Loop;
                    }
                }
            }
        }

        if (all_ripe) bw.write(Integer.toString(min_days));
        else bw.write("-1");

        bw.flush();
    }

    private static void bfs() {
        int[] dx = {-1, 0, 1, 0, 0, 0};
        int[] dy = {0, -1, 0, 1, 0, 0};
        int[] dz = {0, 0, 0, 0, 1, -1};

        while (!queue.isEmpty()) {
            Ripe_tomato poll = queue.poll();

            if (poll.day > min_days) min_days = poll.day;

            for (int i = 0; i < 6; i++) {
                int x = poll.x + dx[i];
                int y = poll.y + dy[i];
                int z = poll.z + dz[i];

                if (x >= 0 && x < m && y >= 0 && y < n && z >= 0 && z < h) {
                    if (!visited[z][y][x] && storage[z][y][x] == 0) {
                        storage[z][y][x] = 1;
                        visited[z][y][x] = true;
                        queue.add(new Ripe_tomato(x, y, z, poll.day + 1));
                    }
                }
            }
        }
    }
}
