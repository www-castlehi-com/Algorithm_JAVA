package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class P_1799 {

    static int n;
    static int[][] map;
    static ArrayList<Bishop> bishopWhite = new ArrayList<>();
    static ArrayList<Bishop> bishopBlack = new ArrayList<>();

    static public class Bishop {
        int x, y;

        public Bishop(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static ArrayList<Bishop> bishops;
    static int ans;
    static int[][] dxdy = {{-1, -1}, {1, 1}, {-1, 1}, {1, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    if ((i + j) % 2 == 0) bishopBlack.add(new Bishop(j, i));
                    else bishopWhite.add(new Bishop(j, i));
                }
            }
        }

        int res = 0;

        bishops = new ArrayList<>(bishopBlack);
        findBishop(0, 0);
        res += ans;

        ans = 0;
        bishops = new ArrayList<>(bishopWhite);
        findBishop(0, 0);
        res += ans;

        bw.write(Integer.toString(res));
        bw.flush();
    }

    private static void findBishop(int cnt, int res) {
        if (cnt == bishops.size()) ans = Math.max(ans, res);
        else {
            Bishop bishop = bishops.get(cnt);

            if (canPlaceBishop(bishop.y, bishop.x)) {
                markMap(bishop.y, bishop.x, 3);
                map[bishop.y][bishop.x] = 2;
                findBishop(cnt + 1, res + 1);
                map[bishop.y][bishop.x] = 1;
                markMap(bishop.y, bishop.x, 1);
            }
            findBishop(cnt + 1, res);
        }
    }

    private static void markMap(int y, int x, int idx) {
        for (int[] ints : dxdy) {
            int nextY = y + ints[0], nextX = x + ints[1];

            while (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) {
                if (map[nextY][nextX] == 2) return;

                map[nextY][nextX] = idx;

                nextY += ints[0];
                nextX += ints[1];
            }
        }
    }

    private static boolean canPlaceBishop(int y, int x) {
        if (map[y][x] == 3) return false;

        for (int[] ints : dxdy) {
            int nextY = y + ints[0], nextX = x + ints[1];

            while (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) {
                if (map[nextY][nextX] == 2) return false;
                nextY += ints[0];
                nextX += ints[1];
            }
        }

        return true;
    }
}