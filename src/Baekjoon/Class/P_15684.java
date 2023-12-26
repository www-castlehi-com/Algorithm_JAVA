package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class P_15684 {

    static int n, h;
    static int[][] ladders;
    static int minCnt = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; h = line[2];
        int m = line[1];
        ladders = new int[n + 1][h + 1];
        for (int i = 0; i < m; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            ladders[line[1]][line[0]] = line[1] + 1;
            ladders[line[1] + 1][line[0]] = line[1];
        }

        if (line[1] == 0) bw.write(Integer.toString(0));
        else {
            makeLadder(1, 0);
            if (minCnt > 3) minCnt = -1;
            bw.write(Integer.toString(minCnt));
        }
        bw.flush();
    }

    private static void makeLadder(int b, int cnt) {
        if (checkLadderGame()) {
            minCnt = Math.min(minCnt, cnt);
        }
        if (cnt < 3) {
            for (int i = b; i < n; i++) {
                for (int j = 1; j <= h; j++) {
                    if (ladders[i][j] == 0 && ladders[i + 1][j] == 0) {
                        ladders[i][j] = i + 1;
                        ladders[i + 1][j] = i;
                        makeLadder(i, cnt + 1);
                        ladders[i][j] = 0;
                        ladders[i + 1][j] = 0;
                    }
                }
            }
        }
    }

    private static boolean checkLadderGame() {
        for (int i = 1; i <= n; i++) {
            if (!checkEachLadder(i)) return false;
        }
        return true;
    }

    private static boolean checkEachLadder(int vertical) {
        int curX = vertical;
        for (int curY = 1; curY <= h; curY++) {
            if (ladders[curX][curY] != 0) curX = ladders[curX][curY];
        }

        if (curX == vertical) return true;
        else return false;
    }
}
