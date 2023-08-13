package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_2206 {

    static int n, m;
    static int[][] map;

    static class Road {
        int x, y;
        int dist;
        boolean broken;

        public Road(int x, int y, int dist, boolean broken) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.broken = broken;
        }
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

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
        LinkedList<Road> queue = new LinkedList<>();
        queue.add(new Road(0, 0, 1, false));
        boolean[][][] visited = new boolean[n][m][2];
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Road poll = queue.poll();

            if (poll.y == n - 1 && poll.x == m - 1) return poll.dist;

            for (int i = 0; i < 4; i++) {
                int nextX = poll.x + dx[i];
                int nextY = poll.y + dy[i];

                if (nextY >= 0 && nextY < n && nextX >= 0 && nextX < m) {
                    Road road = new Road(nextX, nextY, poll.dist + 1, poll.broken);
                    if (poll.broken) {
                        if (map[nextY][nextX] == 0 && !visited[nextY][nextX][1]) {
                            visited[nextY][nextX][1] = true;
                            queue.add(road);
                        }
                    }
                    else {
                        if (map[nextY][nextX] == 0 && !visited[nextY][nextX][0]) {
                            visited[nextY][nextX][0] = true;
                            queue.add(road);
                        }
                        else if (map[nextY][nextX] == 1 && !visited[nextY][nextX][1]) {
                            visited[nextY][nextX][1] = true;
                            road.broken = true;
                            queue.add(road);
                        }
                    }
                }
            }
        }

        return -1;
    }
}
