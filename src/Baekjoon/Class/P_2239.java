package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class P_2239 {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] map = new int[9][9];
    static ArrayList<Blank> blanks = new ArrayList<>();

    static class Blank {
        int x, y;

        public Blank(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < 9; j++) {
                if (map[i][j] == 0) blanks.add(new Blank(j, i));
            }
        }

        backTracking(0);
    }

    private static void backTracking(int cnt) throws IOException {
        if (cnt == blanks.size()) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    bw.write(map[i][j] + "");
                }
                bw.newLine();
            }
            bw.flush();

            System.exit(0);
        }

        Blank blank = blanks.get(cnt);
        for (int i = 1; i <= 9; i++) {
            int x = blank.x;
            int y = blank.y;

            if (validate(x, y, i)) {
                map[y][x] = i;
                backTracking(cnt + 1);
                map[y][x] = 0;
            }
        }
    }

    private static boolean validate(int x, int y, int num) {
        for (int i = 0; i < 9; i++) {
            if (map[i][x] == num) return false;
            if (map[y][i] == num) return false;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[y / 3 * 3 + i][x / 3 * 3 + j] == num) return false;
            }
        }

        return true;
    }
}
