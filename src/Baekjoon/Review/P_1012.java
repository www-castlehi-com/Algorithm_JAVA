package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_1012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int m = line[0], n = line[1], k = line[2];

            int[][] map = new int[n][m];
            boolean[][] visited = new boolean[n][m];
            for(int i = 0; i < k; i++) {
                line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                int x = line[0], y = line[1];
                map[y][x] = 1;
            }

            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 1 && !visited[i][j]) {
                        cnt++;
                        bfs(map, visited, i, j);
                    }
                }
            }

            bw.write(cnt + "\n");
        }
        bw.flush();
    }

    private static void bfs(int[][] map, boolean[][] visited, int y, int x) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});
        visited[y][x] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = poll[1] + dx[i];
                int nextY = poll[0] + dy[i];

                if (nextX >= 0 && nextX < map[0].length && nextY >= 0 && nextY < map.length) {
                    if (map[nextY][nextX] == 1 && !visited[nextY][nextX]) bfs(map, visited, nextY, nextX);
                }
            }
        }
    }
}
