package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_1992 {

    static int[][] video;
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        video = new int[n][n];
        for (int i = 0; i < n; i++) {
            video[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        VideoCompression(n, 0, 0);

        bw.flush();
    }

    private static void VideoCompression(int size, int y, int x) throws IOException {
        if (size == 0) return;
        else {
            int result = CheckCompression(size, y, x);
            if (result != -1) bw.write(Integer.toString(result));
            else {
                bw.write("(");
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        VideoCompression(size / 2, y + i * (size / 2), x + j * (size / 2));
                    }
                }
                bw.write(")");
            }
        }
    }

    private static int CheckCompression(int size, int y, int x) {
        int standard = video[y][x];

        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (standard != video[i][j]) return -1;
            }
        }

        return standard;
    }
}
