package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class P_14502 {

    static int n, m;
    static int[][] map;
    static ArrayList<Virus> viruses;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int ans = 0;

    static class Virus {
        int x, y;

        public Virus(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1];
        viruses = new ArrayList<>();
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 2) viruses.add(new Virus(j, i));
            }
        }

        solve(0);

        bw.write(Integer.toString(ans));
        bw.flush();
    }

    private static void solve(int cnt) {
        if (cnt == 3) bfs();
        else {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 0) {
                        map[i][j] = 1;
                        solve(cnt + 1);
                        map[i][j] = 0;
                    }
                }
            }
        }
    }

    private static void bfs() {
        LinkedList<Virus> queue = new LinkedList<>(viruses);
        boolean[][] visited = new boolean[n][m];
        int[][] copyMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) copyMap[i][j] = map[i][j];
        }

        while (!queue.isEmpty()) {
            Virus poll = queue.poll();

            if (!visited[poll.y][poll.x]) {
                visited[poll.y][poll.x] = true;

                for (int i = 0; i < 4; i++) {
                    int nextX = poll.x + dx[i];
                    int nextY = poll.y + dy[i];

                    if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && !visited[nextY][nextX] && copyMap[nextY][nextX] == 0) {
                        copyMap[nextY][nextX] = 2;
                        queue.add(new Virus(nextX, nextY));
                    }
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copyMap[i][j] == 0) cnt++;
            }
        }

        ans = Math.max(ans, cnt);
    }
}
