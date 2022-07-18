package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class P_16935 {

    static int n, m;
    static int[][] before_map;
    static int[][] after_map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = info[0];
        m = info[1];
        before_map = new int[n][m];
        after_map = new int[n][m];
        for (int i = 0; i < n; i++) before_map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] operation = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int op : operation) {
            before_map = turn_the_array(op);
        }

        for (int[] ints : after_map) {
            if (ints[0] == 0) break;
            for (int anInt : ints) {
                if (anInt == 0) break;
                bw.write(anInt + " ");
            }
            bw.newLine();
        }
        bw.flush();
    }

    public static int[][] turn_the_array(int op) {
        if (op == 3 || op == 4) {
            int tmp;
            tmp = n;
            n = m;
            m = tmp;
        }
        after_map = new int[n][m];


        if (op == 1) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    after_map[j][i] = before_map[n - j - 1][i];
                }
            }
        }
        else if (op == 2) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    after_map[i][j] = before_map[i][m - j - 1];
                }
            }
        }
        else if (op == 3) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    after_map[i][m - j - 1] = before_map[j][i];
                }
            }
        }
        else if (op == 4) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    after_map[n - i - 1][j] = before_map[j][i];
                }
            }
        }
        else if (op == 5) {
            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j < m / 2; j++) {
                    after_map[i][j + m / 2] = before_map[i][j];
                }

                for (int j = m / 2; j < m; j++) {
                    after_map[i + n / 2][j] = before_map[i][j];
                }
            }

            for (int i = n / 2; i < n; i++) {
                for (int j = 0; j < m / 2; j++) {
                    after_map[i - n / 2][j] = before_map[i][j];
                }

                for (int j = m / 2; j < m; j++) {
                    after_map[i][j - m / 2] = before_map[i][j];
                }
            }
        }
        else {
            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j < m / 2; j++) {
                    after_map[i + n / 2][j] = before_map[i][j];
                }

                for (int j = m / 2; j < m; j++) {
                    after_map[i][j - m / 2] = before_map[i][j];
                }
            }

            for (int i = n / 2; i < n; i++) {
                for (int j = 0; j < m / 2; j++) {
                    after_map[i][j + m / 2] = before_map[i][j];
                }

                for (int j = m / 2; j < m; j++) {
                    after_map[i - n / 2][j] = before_map[i][j];
                }
            }
        }

        return after_map;
    }
}
