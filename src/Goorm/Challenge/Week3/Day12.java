package Goorm.Challenge.Week3;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class Day12 {

    static int n;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        visited = new boolean[n][n];

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    cnt++;
                    bfs(i, j);
                }
            }
        }

        bw.write(Integer.toString(cnt));
        bw.flush();
    }

    private static void bfs(int y, int x) {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[] {y, x});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int curY = poll[0];
            int curX = poll[1];

            if (!visited[curY][curX]) {
                visited[curY][curX] = true;

                for (int i = 0; i < 4; i++) {
                    int nextY = curY + dy[i];
                    int nextX = curX + dx[i];

                    if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && map[nextY][nextX] == 1) {
                        queue.add(new int[]{nextY, nextX});
                    }
                }
            }
        }
    }
}
