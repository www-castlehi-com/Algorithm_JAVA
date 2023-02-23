package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;

public class P_14500 {
    static int n, m;
    static int[][] map;
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /*input*/
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1];
        map = new int[n][m];
        for (int i = 0; i < n; i++) map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        tetromino1();
        tetromino2();
        tetromino3();
        tetromino4();
        tetromino5();

        bw.write(Integer.toString(res));
        bw.flush();
    }

    private static void tetromino1() {
        for (int i = 0; i <= n - 1; i++) {
            for (int j = 0; j <= m - 4; j++) {
                int sum = map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i][j + 3];
                if (res < sum) res = sum;
            }
        }

        for (int j = 0; j <= m - 1; j++) {
            for (int i = 0; i <= n - 4; i++) {
                int sum = map[i][j] + map[i + 1][j] + map[i + 2][j] + map[i + 3][j];
                if (res < sum) res = sum;
            }
        }
    }

    private static void tetromino2() {
        for (int i = 0; i <= n - 2; i++) {
            for (int j = 0; j <= m - 2; j++) {
                int sum = map[i][j] + map[i][j + 1] + map[i + 1][j] + map[i + 1][j + 1];
                if (res < sum) res = sum;
            }
        }
    }

    private static void tetromino3() {
        for (int i = 0; i <= n - 3; i++) {
            for (int j = 0; j <= m - 2; j++) {
                int sum = map[i][j] + map[i + 1][j] + map[i + 2][j] + map[i + 2][j + 1];
                if (res < sum) res = sum;

                sum = map[i][j] + map[i][j + 1] + map[i + 1][j + 1] + map[i + 2][j + 1];
                if (res < sum) res = sum;

                sum = map[i][j + 1] + map[i + 1][j + 1] + map[i + 2][j + 1] + map[i + 2][j];
                if (res < sum) res = sum;

                sum = map[i][j] + map[i][j + 1] + map[i + 1][j] + map[i + 2][j];
                if (res < sum) res = sum;
            }
        }

        for (int i = 0; i <= n - 2; i++) {
            for (int j = 0; j <= m - 3; j++) {
                int sum = map[i + 1][j] + map[i + 1][j + 1] + map[i + 1][j + 2] + map[i][j + 2];
                if (res < sum) res = sum;

                sum = map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i + 1][j];
                if (res < sum) res = sum;

                sum = map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i + 1][j + 2];
                if (res < sum) res = sum;

                sum = map[i][j] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 1][j + 2];
                if (res < sum) res = sum;
            }
        }
    }

    private static void tetromino4() {
        for (int i = 0; i <= n - 3; i++) {
            for (int j = 0; j <= m - 2; j++) {
                int sum = map[i][j] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 2][j + 1];
                if (res < sum) res = sum;

                sum = map[i][j + 1] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 2][j];
                if (res < sum) res = sum;
            }
        }

        for (int i = 0; i <= n - 2; i++) {
            for (int j = 0; j <= m - 3; j++) {
                int sum = map[i + 1][j] + map[i + 1][j + 1] + map[i][j + 1] + map[i][j + 2];
                if (res < sum) res = sum;

                sum = map[i][j] + map[i][j + 1] + map[i + 1][j + 1] + map[i + 1][j + 2];
                if (res < sum) res = sum;
            }
        }
    }

    private static void tetromino5() {
        for (int i = 0; i <= n - 2; i++) {
            for (int j = 0; j <= m - 3; j++) {
                int sum = map[i][j] + map[i][j + 1] + map[i + 1][j + 1] + map[i][j + 2];
                if (res < sum) res = sum;

                sum = map[i + 1][j] + map[i + 1][j + 1] + map[i][j + 1] + map[i + 1][j + 2];
                if (res < sum) res = sum;
            }
        }

        for (int i = 0; i <= n - 3; i++) {
            for (int j = 0; j <= m - 2; j++) {
                int sum = map[i][j] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 2][j];
                if (res < sum) res = sum;

                sum = map[i + 1][j] + map[i][j + 1] + map[i + 1][j + 1] + map[i + 2][j + 1];
                if (res < sum) res = sum;
            }
        }
    }
}
