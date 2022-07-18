package Baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_2206 {

    static int n, m;
    static int[][] map;
    static boolean[][][] visited;

    public static class CurrentInfo {
        int y, x;
        int breakCount;
        int distance;

        public CurrentInfo(int y, int x, int breakCount, int distance) {
            this.y = y;
            this.x = x;
            this.breakCount = breakCount;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = s[0]; m = s[1];

        map = new int[n][m];
        for (int i = 0; i < n; i++) map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

        visited = new boolean[n][m][2];

        bw.write(Integer.toString(bfs()));
        bw.flush();
    }

    private static int bfs() {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        LinkedList<CurrentInfo> queue = new LinkedList<>();
        queue.add(new CurrentInfo(0, 0, 0, 1));
        visited[0][0][0] = visited[0][0][1] = true;

        while (!queue.isEmpty()) {
            CurrentInfo poll = queue.poll();

            if (poll.y == n - 1 && poll.x == m - 1) {
                return poll.distance;
            }

            for (int i = 0; i < 4; i++) {
                int x = poll.x + dx[i];
                int y = poll.y + dy[i];

                if (x >= 0 && x < m && y >= 0 && y < n) {
                    CurrentInfo nextInfo = new CurrentInfo(y, x, poll.breakCount, poll.distance + 1);
                    if (poll.breakCount == 0) {
                        if (!visited[y][x][0]) {
                            if (map[y][x] == 1) {
                                visited[y][x][1] = true;
                                nextInfo.breakCount = 1;
                                queue.add(nextInfo);
                            }
                            else {
                                visited[y][x][0] = true;
                                queue.add(nextInfo);
                            }
                        }
                    }
                    else {
                        if (!visited[y][x][1]) {
                            if (map[y][x] == 0) {
                                visited[y][x][1] = true;
                                queue.add(nextInfo);
                            }
                        }
                    }
                }
            }
        }

        return -1;
    }
}

