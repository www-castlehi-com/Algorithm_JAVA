package Baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_13460 {

    static Character[][] board;
    static RedBlueMarble redBlueMarble = new RedBlueMarble();
    static boolean[][][][] visited;

    public static class RedBlueMarble {
        int redX, redY;
        int blueX, blueY;
        int count;

        public RedBlueMarble() {
            count = 0;
        }

        public RedBlueMarble(RedBlueMarble prev) {
            redX = prev.redX;
            redY = prev.redY;
            blueX = prev.blueX;
            blueY = prev.blueY;
            count = prev.count;
        }

        public boolean isEqual() {
            if (this.redX == this.blueX && this.redY == this.blueY) return true;
            else return false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0], m = arr[1];
        visited = new boolean[n][m][n][m];
        board = new Character[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                board[i][j] = s.charAt(j);
                if (board[i][j] == 'B') {
                    redBlueMarble.blueX = j;
                    redBlueMarble.blueY = i;
                }
                else if (board[i][j] == 'R') {
                    redBlueMarble.redX = j;
                    redBlueMarble.redY = i;
                }
            }
        }

        int result = bfs();
        bw.write(Integer.toString(result));
        bw.flush();
    }

    private static int bfs() {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        int cnt = 0;

        LinkedList<RedBlueMarble> queue = new LinkedList<>();
        queue.add(redBlueMarble);
        while (!queue.isEmpty()) {
            RedBlueMarble cur = queue.poll();
            visited[cur.redY][cur.redX][cur.blueY][cur.blueX] = true;
            cnt = cur.count;
            if (cnt >= 10) return -1;

            for (int i = 0; i < 4; i++) {
                RedBlueMarble sample = new RedBlueMarble(cur);

                int blueMove = move(sample, dy[i], dx[i], 'B');
                if (blueMove != 0) {
                    int redMove = move(sample, dy[i], dx[i], 'R');
                    if (redMove == 0) return sample.count + 1;

                    equalCheck(cur, sample, dy[i], dx[i]);
                    if (!visited[sample.redY][sample.redX][sample.blueY][sample.blueX]) {
                        sample.count = sample.count + 1;
                        queue.add(sample);
                    }
                }
            }
        }
        return -1;
    }

    public static int move (RedBlueMarble marble, int dy, int dx, Character color) {
        while (true) {
            int nextY, nextX;

            if (color == 'B') {
                nextY = marble.blueY + dy;
                nextX = marble.blueX + dx;

                if (board[nextY][nextX] == '#') return 1;
                else if (board[nextY][nextX] == 'O') return 0;

                marble.blueY = nextY;
                marble.blueX = nextX;
            }
            else {
                nextY = marble.redY + dy;
                nextX = marble.redX + dx;

                if (board[nextY][nextX] == '#') return 1;
                else if (board[nextY][nextX] == 'O') return 0;

                marble.redY = nextY;
                marble.redX = nextX;
            }
        }
    }

    public static void equalCheck(RedBlueMarble standard, RedBlueMarble target, int directionY, int directionX) {
        if (target.isEqual()) {
            int dyBlue = Math.abs(standard.blueY - target.blueY);
            int dxBlue = Math.abs(standard.blueX - target.blueX);

            int dyRed = Math.abs(standard.redY - target.redY);
            int dxRed = Math.abs(standard.redX - target.redX);

            if (dyBlue > dyRed) target.blueY = target.blueY + (directionY * -1);
            else target.redY = target.redY + (directionY * -1);

            if (dxBlue > dxRed) target.blueX = target.blueX + (directionX * -1);
            else target.redX = target.redX + (directionX * -1);
        }
    }
}
