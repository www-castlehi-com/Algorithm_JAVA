package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_7569 {

    static LinkedList<Tomato> queue = new LinkedList<>();
    static int[][][] boxes;
    static boolean[][][] visited;
    static int m, n, h;
    static int max = Integer.MIN_VALUE;

    public static class Tomato {
        int x, y, z;
        int day;

        public Tomato(int x, int y, int z, int day) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.day = day;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        m = arr[0]; n = arr[1]; h = arr[2];
        boxes = new int[h][n][m];
        visited = new boolean[h][n][m];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                boxes[i][j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                for (int k = 0; k < m; k++) {
                    if (boxes[i][j][k] == 1) {
                        queue.add(new Tomato(k, j, i, 0));
                    }
                }
            }
        }

        bfs();

        Loop:
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (boxes[i][j][k] == 0) {
                        max = -1;
                        break Loop;
                    }
                }
            }
        }

        bw.write(Integer.toString(max));
        bw.flush();
    }

    private static void bfs() {
        int[] dx = {-1, 0, 1, 0, 0, 0};
        int[] dy = {0, -1, 0, 1, 0, 0};
        int[] dz = {0, 0, 0, 0, 1, -1};

        while (!queue.isEmpty()) {
            Tomato poll = queue.poll();

            if (max < poll.day) max = poll.day;

            for (int i = 0; i < 6; i++) {
                int x = poll.x + dx[i];
                int y = poll.y + dy[i];
                int z = poll.z + dz[i];

                if (x >= 0 && x < m && y >= 0 && y < n && z >= 0 && z < h) {
                    if (!visited[z][y][x] && boxes[z][y][x] == 0) {
                        visited[z][y][x] = true;
                        boxes[z][y][x] = 1;
                        queue.add(new Tomato(x, y, z, poll.day + 1));
                    }
                }
            }
        }
    }
}
