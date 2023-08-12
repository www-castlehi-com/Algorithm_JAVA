package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_2638 {

    static int n, m;
    static int[][] map;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static class Loc {
        int x, y;

        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1];
        map = new int[n][m];
        for (int i = 0; i < n; i++) map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int time = 0;
        while (true) {
            bfs();
            if (check()) break;
            time++;
        }

        bw.write(Integer.toString(time));
        bw.flush();
    }

    private static boolean check() {
        boolean noCheese = true;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    noCheese = false;
                    int cnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nextY = i + dy[k];
                        int nextX = j + dx[k];

                        if (map[nextY][nextX] == 3) cnt++;
                    }

                    if (cnt >= 2) map[i][j] = 4;
                }
            }
        }

        if (!noCheese) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 4) map[i][j] = 3;
                }
            }
        }

        return noCheese;
    }

    private static void bfs() {
        LinkedList<Loc> queue = new LinkedList<>();
        queue.add(new Loc(0, 0));
        map[0][0] = 3;
        boolean[][] visited = new boolean[n][m];

        while (!queue.isEmpty()) {
            Loc poll = queue.poll();

            if (!visited[poll.y][poll.x]) {
                visited[poll.y][poll.x] = true;

                for (int i = 0; i < 4; i++) {
                    int nextY = poll.y + dy[i];
                    int nextX = poll.x + dx[i];

                    if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && !visited[nextY][nextX] && map[nextY][nextX] != 1) {
                        queue.add(new Loc(nextX, nextY));
                        map[nextY][nextX] = 3;
                    }
                }
            }
        }
    }
}
