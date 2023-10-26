package Baekjoon.Class;

import java.io.*;

public class P_14939 {

    static final int LIMIT = 101;
    static int ans = LIMIT;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[][] map = new String[10][10];
        for (int i = 0; i < 10; i++) map[i] = br.readLine().split("");

        switchFirst(0, 0, map);

        if (ans != LIMIT) {
            bw.write(Integer.toString(ans));
        }
        else bw.write(Integer.toString(-1));
        bw.flush();
    }

    private static void switchFirst(int x, int cnt, String[][] map) {
        if (x == 10) {
            int tempCnt = cnt;
            for (int i = 1; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (map[i - 1][j].equals("O")) {
                        push(i, j, map);
                        tempCnt++;
                    }
                }
            }

            if (check(map)) {
                ans = Math.min(ans, tempCnt);
            }
        }
        else {
            String[][] copyMap = copy(map);
            switchFirst(x + 1, cnt, map);
            push(0, x, copyMap);
            switchFirst(x + 1, cnt + 1, copyMap);
        }
    }

    private static String[][] copy(String[][] map) {
        String[][] copyMap = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        return copyMap;
    }

    private static boolean check(String[][] map) {
        for (int j = 0; j < 10; j++) {
            if (map[9][j].equals("O")) {
                return false;
            }
        }
        return true;
    }

    private static void push(int y, int x, String[][] map) {
        switching(y, x, map);
        switching(y + 1, x, map);
        switching(y - 1, x, map);
        switching(y, x + 1, map);
        switching(y, x - 1, map);
    }

    private static void switching(int y, int x, String[][] map) {
        if (x >= 0 && x < 10 && y >= 0 && y < 10) {
            if (map[y][x].equals("O")) map[y][x] = "#";
            else map[y][x] = "O";
        }
    }
}
