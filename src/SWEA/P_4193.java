package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class P_4193 {

    static public class Location {
        int y, x;
        int time;

        public Location(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int test = 1; test <= t; test++) {
            int n = Integer.parseInt(br.readLine());
            int[][] map = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine(), " ");
            int c = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken());

            System.out.println("#" + test + " " + move(n, a, b, c, d, map));
        }
    }

    private static int move(int n, int a, int b, int c, int d, int[][] map) {
        LinkedList<Location> queue = new LinkedList<>();
        queue.add(new Location(a, b, 0));
        boolean[][] visited = new boolean[n][n];

        while (!queue.isEmpty()) {
            Location poll = queue.poll();
            int y = poll.y;
            int x = poll.x;
            int time = poll.time;

            if (y == c && x == d) return time;

            for (int i = 0; i < 4; i++) {
                int nextY = y + dy[i];
                int nextX = x + dx[i];

                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && !visited[nextY][nextX]) {
                    if (map[nextY][nextX] == 1) continue;
                    else if (map[nextY][nextX] == 2) {
                        if (time % 3 == 2) {
                            visited[nextY][nextX] = true;
                            queue.add(new Location(nextY, nextX, time + 1));
                        }
                        else {
                            visited[y][x] = true;
                            queue.add(new Location(y, x, time + 1));
                        }
                    }
                    else {
                        visited[nextY][nextX] = true;
                        queue.add(new Location(nextY, nextX, time + 1));
                    }
                }
            }
        }
        return -1;
    }
}
