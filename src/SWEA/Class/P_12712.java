package SWEA.Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P_12712 {

    static int n, m;
    static int[][] map;

    static int[] plusDx = {-1, 1, 0, 0};
    static int[] plusDy = {0, 0, 1, -1};
    static int[] xDx = {-1, -1, 1, 1};
    static int[] xDy = {-1, 1, -1, 1};

    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int test = 1; test <= t; test++) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            res = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    res = Math.max(res, catchFly(i, j));
                }
            }

            System.out.println("#" + test + " " + res);
        }
    }

    private static int catchFly(int y, int x) {
        int catchPlus = catching(y, x, true);
        int catchX = catching(y, x, false);

        return Math.max(catchPlus, catchX);
    }

    private static int catching(int y, int x, boolean isPlus) {
        int ans = map[y][x];
        int[] dx = plusDx, dy = plusDy;
        if (!isPlus) {
            dx = xDx;
            dy = xDy;
        }

        for (int i = 0; i < 4; i++) {
            int nextY, nextX;
            for (int j = 1; j < m; j++) {
                nextY = y + dy[i] * j;
                nextX = x + dx[i] * j;
                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) ans += map[nextY][nextX];
            }
        }

        return ans;
    }
}
