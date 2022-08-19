package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_16927 {

    static int N, M, R;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int n = N;
        int m = M;
        for (int i = 0; i < Math.min(N, M) / 2; i++) {
            turn_the_array(i, 2 * n + 2 * m - 4);
            n -= 2;
            m -= 2;
        }

        for (int[] ints : map) {
            for (int anInt : ints) {
                sb.append(anInt).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    public static void turn_the_array(int start, int len) {
        int[] x_set = {1, 0, -1, 0};
        int[] y_set = {0, 1, 0, -1};
        int r = R % len;

        for (int i = 0; i < r; i++) {
            int x = start, y = start;
            int start_value = map[y][x];

            int j = 0;
            while (j < 4) {
                int x_idx = x + x_set[j];
                int y_idx = y + y_set[j];

                if (x_idx < start || x_idx > M - start - 1 || y_idx < start || y_idx > N - start - 1) {
                    j++;
                    continue;
                }

                map[y][x] = map[y_idx][x_idx];
                x = x_idx;
                y = y_idx;
            }

            map[y + 1][x] = start_value;
        }
    }
}
