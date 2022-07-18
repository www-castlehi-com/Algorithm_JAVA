package Baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_12100 {

    static int max = Integer.MIN_VALUE;

    public static class Board2048 {
        int size;
        int cnt;
        int[][] board2048;

        public Board2048(int size, int cnt, int[][] board2048) {
            this.size = size;
            this.cnt = cnt;
            this.board2048 = board2048;
        }

        public Board2048(Board2048 board2048) {
            this.size = board2048.size;
            this.cnt = board2048.cnt;
            this.board2048 = new int[this.size][this.size];

            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < this.size; j++) {
                    this.board2048[i][j] = board2048.board2048[i][j];
                }
            }
        }

        public boolean isEqual(Board2048 board2048) {
            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < this.size; j++) {
                    if (this.board2048[i][j] != board2048.board2048[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        bfs(new Board2048(n, 0, board));

        bw.write(Integer.toString(max));
        bw.flush();
    }

    private static void bfs(Board2048 board2048) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        LinkedList<Board2048> queue = new LinkedList<>();
        queue.add(board2048);

        while (!queue.isEmpty()) {
            Board2048 cur = queue.poll();
            if (cur.cnt >= 5) return ;

            for (int i = 0; i < 4; i++) {
                Board2048 next = new Board2048(cur);

                push(next, dy[i], dx[i]);

                next.cnt++;
                findMaxBlock(next);
                if (!cur.isEqual(next)) {
                    findMaxBlock(next);
                    queue.add(next);
                }
            }
        }
    }

    private static void findMaxBlock(Board2048 next) {
        for (int i = 0; i < next.size; i++) {
            for (int j = 0; j < next.size; j++) {
                if (next.board2048[i][j] > max) max = next.board2048[i][j];
            }
        }
    }

    private static void push(Board2048 next, int dy, int dx) {
        boolean[][] visited = new boolean[next.size][next.size];

        if (dx == -1) {
            for (int i = 0; i < next.size; i++) {
                for (int j = 1; j < next.size; j++) {
                    for (int k = j; k >= 1; k--) {
                        if (next.board2048[i][k] == 0) break;
                        if (next.board2048[i][k - 1] != 0) {
                            if (!visited[i][k - 1] && next.board2048[i][k - 1] == next.board2048[i][k]) {
                                next.board2048[i][k - 1] *= 2;
                                next.board2048[i][k] = 0;
                                visited[i][k - 1] = true;
                            }
                            break;
                        }
                        else {
                            next.board2048[i][k - 1] = next.board2048[i][k];
                            next.board2048[i][k] = 0;
                        }
                    }
                }
            }
        }
        else if (dx == 1) {
            for (int i = 0; i < next.size; i++) {
                for (int j = next.size - 2; j >= 0; j--) {
                    for (int k = j; k <= next.size - 2; k++) {
                        if (next.board2048[i][k] == 0) break;
                        if (next.board2048[i][k + 1] != 0) {
                            if (!visited[i][k + 1] && next.board2048[i][k + 1] == next.board2048[i][k]) {
                                next.board2048[i][k + 1] *= 2;
                                next.board2048[i][k] = 0;
                                visited[i][k + 1] = true;
                            }
                            break;
                        }
                        else {
                            next.board2048[i][k + 1] = next.board2048[i][k];
                            next.board2048[i][k] = 0;
                        }
                    }
                }
            }
        }
        else if (dy == -1) {
            for (int i = 1; i < next.size; i++) {
                for (int j = 0; j < next.size; j++) {
                    for (int k = i; k >= 1; k--) {
                        if (next.board2048[k][j] == 0) break;
                        if (next.board2048[k - 1][j] != 0) {
                            if (!visited[k - 1][j] && next.board2048[k - 1][j] == next.board2048[k][j]) {
                                next.board2048[k - 1][j] *= 2;
                                next.board2048[k][j] = 0;
                                visited[k - 1][j] = true;
                            }
                            break;
                        }
                        else {
                            next.board2048[k - 1][j] = next.board2048[k][j];
                            next.board2048[k][j] = 0;
                        }
                    }
                }
            }
        }
        else {
            for (int i = next.size - 2; i >= 0; i--) {
                for (int j = 0; j < next.size; j++) {
                    for (int k = i; k <= next.size - 2; k++) {
                        if (next.board2048[k][j] == 0) break;
                        if (next.board2048[k + 1][j] != 0) {
                            if (!visited[k + 1][j] && next.board2048[k + 1][j] == next.board2048[k][j]) {
                                next.board2048[k + 1][j] *= 2;
                                next.board2048[k][j] = 0;
                                visited[k + 1][j] = true;
                            }
                            break;
                        }
                        else {
                            next.board2048[k + 1][j] = next.board2048[k][j];
                            next.board2048[k][j] = 0;
                        }
                    }
                }
            }
        }
    }
}