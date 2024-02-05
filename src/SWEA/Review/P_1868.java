package SWEA.Review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class P_1868 {

    static int n;
    static char[][] map = new char[300][300];

    static LinkedList<int[]> queue = new LinkedList<>();
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int test = 1; test <= t; test++) {
            n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                String line = br.readLine();
                for (int j = 0; j < n; j++) {
                    map[i][j] = line.charAt(j);
                }
            }

            boolean[][] visited = new boolean[n][n];
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == '.') {
                        char landMine = findLandMine(i, j);
                        if (landMine == '0') {
                            bfs(visited, new int[]{i, j});
                            cnt++;
                        }
                        else {
                            queue.add(new int[]{i, j});
                        }
                    }
                }
            }

            System.out.println("queue size : " + queue.size());

            while (!queue.isEmpty()) {
                int[] ints = queue.poll();
                if (!visited[ints[0]][ints[1]]) {
                    cnt++;
                }
            }

            sb.append("#").append(test).append(" ").append(cnt).append("\n");
        }
        System.out.print(sb);
    }

    private static void bfs(boolean[][] visited, int[] ints) {
        LinkedList<int[]> subQueue = new LinkedList<>();
        subQueue.add(ints);
        visited[ints[0]][ints[1]] = true;

        while (!subQueue.isEmpty()) {
            int[] poll = subQueue.poll();

            int curY = poll[0];
            int curX = poll[1];

            if (map[curY][curX] != '0') {
                continue;
            }

            for (int i = 0; i < 8; i++) {
                int nextY = curY + dy[i];
                int nextX = curX + dx[i];

                if (nextY >= 0 && nextY < n && nextX >= 0 && nextX < n && !visited[nextY][nextX]) {
                    char landMine = findLandMine(nextY, nextX);
                    if (landMine == '0') {
                        subQueue.add(new int[]{nextY, nextX});
                    }
                    visited[nextY][nextX] = true;
                }
            }
        }
    }

    private static char findLandMine(int y, int x) {
        int cnt = 0;
        for (int i = 0; i < 8; i++) {
            int nextY = y + dy[i];
            int nextX = x + dx[i];

            if (nextY >= 0 && nextY < n && nextX >= 0 && nextX < n) {
                if (map[nextY][nextX] == '*') {
                    cnt++;
                }
            }
        }

        map[y][x] = (char) (cnt + '0');
        return map[y][x];
    }
}
