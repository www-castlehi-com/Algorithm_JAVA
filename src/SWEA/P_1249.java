package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P_1249 {

    static int n;
    static int[][] map;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static class Restore {
        int y, x;
        int money;

        public Restore(int y, int x, int money) {
            this.y = y;
            this.x = x;
            this.money = money;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int test = 1; test <= t; test++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                String line = st.nextToken();
                for (int j = 0; j < n; j++) {
                    map[i][j] = line.charAt(j) - '0';
                }
            }

            System.out.println("#" + test + " " + bfs());
        }
    }

    private static int bfs() {
        PriorityQueue<Restore> queue = new PriorityQueue<>(Comparator.comparing(o -> o.money));
        queue.add(new Restore(0, 0, 0));
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        while (!queue.isEmpty()) {
            Restore poll = queue.poll();
            int y = poll.y;
            int x = poll.x;
            int money = poll.money;

            if (money > dist[y][x]) continue;

            for (int i = 0; i < 4; i++) {
                int nextY = y + dy[i];
                int nextX = x + dx[i];

                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) {
                    int nextMoney = money + map[nextY][nextX];
                    if (nextMoney < dist[nextY][nextX]) {
                        dist[nextY][nextX] = nextMoney;
                        queue.add(new Restore(nextY, nextX, nextMoney));
                    }
                }
            }
        }
        return dist[n - 1][n - 1];
    }
}
