package SWEA.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class P_1868 {

    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for (int test = 1; test <= t; test++) {
            int n = Integer.parseInt(br.readLine());
            char[][] map = new char[n][n];
            for (int i = 0; i < n; i++) {
                String line = br.readLine();
                for (int j = 0; j < n; j++) {
                    map[i][j] = line.charAt(j);
                }
            }

            int[][] mines = setMine(n, map);
            boolean[][] visited = new boolean[n][n];
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (mines[i][j] == 0 && !visited[i][j]) {
                        cnt++;
                        bfs(n, mines, i, j, visited);
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (mines[i][j] != -1 && !visited[i][j]) cnt++;
                }
            }

            System.out.println("#" + test + " " + cnt);
        }
    }

    private static void bfs(int n, int[][] mines, int y, int x, boolean[][] visited) {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});
        visited[y][x] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int curY = poll[0];
            int curX = poll[1];

            for (int i = 0; i < 8; i++) {
                int nextY = curY + dy[i];
                int nextX = curX + dx[i];

                if (nextY >= 0 && nextY < n && nextX >= 0 && nextX < n && !visited[nextY][nextX]) {
                    visited[nextY][nextX] = true;
                    if (mines[nextY][nextX] == 0) {
                        queue.add(new int[]{nextY, nextX});
                    }
                }
            }
        }
    }

    private static int[][] setMine(int n, char[][] map) {
        int[][] mines = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == '.') {
                    mines[i][j] = getMineCnt(n, map, i, j);
                }
                else mines[i][j] = -1;
            }
        }
        return mines;
    }

    private static int getMineCnt(int n, char[][] map, int y, int x) {
        int cnt = 0;
        for (int i = 0; i < 8; i++) {
            int nextY = y + dy[i];
            int nextX = x + dx[i];

            if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) {
                if (map[nextY][nextX] == '*') cnt++;
            }
        }

        return cnt;
    }
}
