package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;

public class P_1992 {

    static int[][] map;
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++)
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

        divide(n, 0, 0);
        bw.flush();
    }

    private static void divide(int size, int x, int y) throws IOException {
        if (!sameColor(size, x, y)) {
            bw.write("(");
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    divide(size / 2, x + j * (size / 2), y + i * (size / 2));
                }
            }
            bw.write(")");
        }

    }

    private static boolean sameColor(int size, int x, int y) throws IOException {
        int offset = map[y][x];
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (offset != map[i][j]) return false;
            }
        }
        bw.write(Integer.toString(offset));
        return true;
    }
}
