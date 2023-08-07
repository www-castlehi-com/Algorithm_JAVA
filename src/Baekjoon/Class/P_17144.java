package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class P_17144 {

    static int r, c, t;
    static int[][] map;
    static ArrayList<Loc> airCleaner;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static class Loc {
        int x, y;
        int dust;

        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Loc(int x, int y, int dust) {
            this.x = x;
            this.y = y;
            this.dust = dust;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        r = line[0]; c = line[1]; t = line[2];
        map = new int[r][c];
        airCleaner = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (map[i][0] == -1) airCleaner.add(new Loc(0, i));
        }

        clean();

        int ans = 0;
        for (int[] ints : map) {
            for (int anInt : ints) {
                if (anInt > 0) ans += anInt;
            }
        }
        bw.write(Integer.toString(ans));
        bw.flush();
    }

    private static void clean() {
        for (int i = 1; i <= t; i++) {
            bfs();
            wind();
        }
    }

    private static void wind() {
        Loc upper = airCleaner.get(0);

        for (int i = upper.y - 1; i > 0; i--) map[i][upper.x] = map[i - 1][upper.x];
        for (int i = 0; i < c - 1; i++) map[0][i] = map[0][i + 1];
        for (int i = 0; i <= upper.y - 1; i++) map[i][c - 1] = map[i + 1][c - 1];
        for (int i = c - 1; i > upper.x + 1; i--) map[upper.y][i] = map[upper.y][i - 1];
        map[upper.y][upper.x + 1] = 0;

        Loc bottom = airCleaner.get(1);

        for (int i = bottom.y + 1; i < r - 1; i++) map[i][bottom.x] = map[i + 1][bottom.x];
        for (int i = 0; i < c - 1; i++) map[r - 1][i] = map[r - 1][i + 1];
        for (int i = r - 1; i >= bottom.y + 1; i--) map[i][c - 1] = map[i - 1][c - 1];
        for (int i = c - 1; i > bottom.x + 1; i--) map[bottom.y][i] = map[bottom.y][i - 1];
        map[bottom.y][bottom.x + 1] = 0;
    }

    private static void bfs() {
        LinkedList<Loc> queue = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 0) queue.add(new Loc(j, i, map[i][j]));
            }
        }

        boolean[][] visited = new boolean[r][c];

        while (!queue.isEmpty()) {
            Loc poll = queue.poll();

            int curX = poll.x;
            int curY = poll.y;
            int diffusion = poll.dust / 5;

            if (!visited[curY][curX]) {
                visited[curY][curX] = true;

                for (int i = 0; i < 4; i++) {
                    int nextX = curX + dx[i];
                    int nextY = curY + dy[i];

                    if (nextX >= 0 && nextX < c && nextY >= 0 && nextY < r && !isAirCleaner(nextY, nextX)) {
                        map[nextY][nextX] += diffusion;
                        map[curY][curX] -= diffusion;
                    }
                }
            }
        }
    }

    private static boolean isAirCleaner(int nextY, int nextX) {
        for (Loc loc : airCleaner) {
            if (loc.x == nextX && loc.y == nextY) return true;
        }
        return false;
    }
}
