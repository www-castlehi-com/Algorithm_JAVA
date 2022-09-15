package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_2178 {

    static int n, m;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1];

        map = new int[n][m];
        for (int i = 0; i < n; i++) map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

        bw.write(Integer.toString(bfs()));
        bw.flush();
    }

    private static int bfs() {
        LinkedList<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        queue.add(new int[]{0, 0, 1});
        visited[0][0] = true;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            if (poll[0] == m - 1 && poll[1] == n - 1) return poll[2];

            for (int i = 0; i < 4; i++) {
                int x = dx[i] + poll[0];
                int y = dy[i] + poll[1];

                if (x >= 0 && x < m && y >= 0 && y < n && map[y][x] == 1) {
                    if (!visited[y][x]) {
                        visited[y][x] = true;
                        queue.add(new int[]{x, y, poll[2] + 1});
                    }
                }
            }
        }

        return -1;
    }
}
