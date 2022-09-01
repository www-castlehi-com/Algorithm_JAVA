package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;

public class P_2630 {

    static int[][] map;
    static int[] paperNumber = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        cutPaper(0, 0, n);

        for (int i : paperNumber) {
            bw.write(i + "\n");
        }
        bw.flush();
    }

    private static void cutPaper(int y, int x, int n) {
        int result = checkPaper(y, x, n);
        if (result != -1) paperNumber[result]++;
        else {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    cutPaper(y + i * n / 2, x + j * n / 2, n / 2);
                }
            }
        }
    }

    private static int checkPaper(int y, int x, int n) {
        int init = map[y][x];
        for (int i = y; i < y + n; i++) {
            for (int j = x; j < x + n; j++) {
                if (init != map[i][j]) return -1;
            }
        }

        return init;
    }
}
