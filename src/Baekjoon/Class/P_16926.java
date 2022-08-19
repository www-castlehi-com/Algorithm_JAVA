package Baekjoon.Class;

import java.io.*;
import java.util.StringTokenizer;

public class P_16926 {

    static int n, m, r;
    static int[][] before_map;
    static int[][] after_map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        before_map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                before_map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        after_map = new int[n][m];

        for (int i = 0 ; i < r; i++) {
            after_map = new int[n][m];
            turn_the_array();
        }

        for (int[] ints : after_map) {
            for (int anInt : ints) {
                sb.append(anInt).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    public static void turn_the_array() {
        int box = Math.min(n, m) / 2;

        for (int i = 0; i < box; i++) {
            int n_max = n - i - 1;
            int m_max = m - i - 1;

            //왼쪽 이동
            for (int j = i; j < m_max; j++) {
                after_map[i][j] = before_map[i][j + 1];
            }
            //아래로 이동
            for (int j = i; j < n_max; j++) {
                after_map[j + 1][i] = before_map[j][i];
            }
            //오른쪽으로 이동
            for (int j = i; j < m_max; j++) {
                after_map[n_max][j + 1] = before_map[n_max][j];
            }
            //위로 이동
            for (int j = i; j < n_max; j++) {
                after_map[j][m_max] = before_map[j + 1][m_max];
            }
        }

        before_map = after_map;
    }
}
