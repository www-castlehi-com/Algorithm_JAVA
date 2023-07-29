package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class P_1987 {

    static int r, c;
    static String[][] map;
    static int ans = 1;
    static boolean[] alpha;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        r = line[0]; c = line[1];
        alpha = new boolean[26];
        map = new String[r][c];
        for (int i = 0; i < r; i++) map[i] = br.readLine().split("");

        alpha[map[0][0].charAt(0) - 'A'] = true;
        dfs(0, 0, 1);

        bw.write(Integer.toString(ans));
        bw.flush();
    }

    private static void dfs(int y, int x, int cnt) {
        if (ans < cnt)
            ans = cnt;

        for (int i = 0; i < 4; i++) {
            int nextY = y + dy[i];
            int nextX = x + dx[i];

            if (nextX >= 0 && nextX < c && nextY >= 0 && nextY < r && !alpha[map[nextY][nextX].charAt(0) - 'A']) {
                alpha[map[nextY][nextX].charAt(0) - 'A'] = true;
                dfs(nextY, nextX, cnt + 1);
                alpha[map[nextY][nextX].charAt(0) - 'A'] = false;
            }
        }
    }
}
