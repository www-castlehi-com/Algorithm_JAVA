package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;

public class P_17837 {

    static int n;
    static int k;
    static int[][] chess;
    static LinkedList<Integer>[][] chessPiece;
    static Map<Integer, ChessPiece> chessPieceMatcher = new HashMap<>();

    static public class ChessPiece {
        int idx;
        int x, y;
        int direction;

        public ChessPiece(int idx, int y, int x, int direction) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        chess = new int[n][n];
        chessPiece = new LinkedList[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                chess[i][j] = Integer.parseInt(st.nextToken());
                chessPiece[i][j] = new LinkedList<>();
            }
        }
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int direct = Integer.parseInt(st.nextToken()) - 1;
            ChessPiece piece = new ChessPiece(i, y, x, direct);
            chessPiece[y][x].add(i);
            chessPieceMatcher.put(i, piece);
        }
        System.out.println(solve());
    }

    private static int solve() {
        int cnt = 0;
        while (cnt++ <= 1000) {
            for (int i = 0; i < k; i++) {
                ChessPiece chessPiece = chessPieceMatcher.get(i);
                int curY = chessPiece.y;
                int curX = chessPiece.x;
                int curDir = chessPiece.direction;
                int num = searchIdx(i, curX, curY);

                int nextY = curY + dy[curDir];
                int nextX = curX + dx[curDir];

                if (!isValidLocation(nextY, nextX)) {
                    chessPiece.direction = curDir = switchDirection(curDir);
                    nextY = curY + dy[curDir];
                    nextX = curX + dx[curDir];

                    if (!isValidLocation(nextY, nextX))
                        continue;
                }

                if (move(curY, curX, nextY, nextX, num)) {
                    return cnt;
                }
            }
        }
        return -1;
    }

    private static boolean move(int curY, int curX, int nextY, int nextX, int num) {
        int color = chess[nextY][nextX];

        while (chessPiece[curY][curX].size() > num) {
            int idx = -1;
            if (color == 0) {
                idx = chessPiece[curY][curX].remove(num);
            }
            else {
                idx = chessPiece[curY][curX].removeLast();
            }

            ChessPiece chess = chessPieceMatcher.get(idx);
            chess.y = nextY;
            chess.x = nextX;
            chessPiece[nextY][nextX].add(idx);
        }

        if (chessPiece[nextY][nextX].size() >= 4) {
            return true;
        }
        return false;
    }

    private static int switchDirection(int dir) {
        switch (dir) {
            case 0:
                return 1;
            case 1:
                return 0;
            case 2:
                return 3;
            case 3:
                return 2;
        }
        return -1;
    }

    private static boolean isValidLocation(int y, int x) {
        return (y >= 0 && y < n && x >= 0 && x < n && chess[y][x] != 2);
    }

    private static int searchIdx(int pieceIdx, int x, int y) {
        for (int i = 0; i < chessPiece[y][x].size(); i++) {
            if (chessPiece[y][x].get(i) == pieceIdx) {
                return i;
            }
        }
        return -1;
    }
}