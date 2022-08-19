package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_1012 {

    static int[][] field;
    static boolean[][] visited;
    static int m, n;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            m = arr[0]; n = arr[1];
            int k = arr[2];
            field = new int[n][m];
            visited = new boolean[n][m];
            for (int i = 0; i < k; i++) {
                arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                field[arr[1]][arr[0]] = 1;
            }

            cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (field[i][j] == 1 && !visited[i][j]) {
                        dfs(j, i);
                        cnt++;
                    }
                }
            }

            bw.write(cnt + "\n");
        }
        bw.flush();
    }

    private static void dfs(int curX, int curY) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        for (int i = 0; i < 4; i++) {
            int nextX = curX + dx[i];
            int nextY = curY + dy[i];

            if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n) {
                if (field[nextY][nextX] == 1 && !visited[nextY][nextX]) {
                    visited[nextY][nextX] = true;
                    dfs(nextX, nextY);
                }
            }
            else continue;
        }
    }
}
