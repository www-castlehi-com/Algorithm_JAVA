import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_13460 {

    static Character[][] board;
    static RedBlueMarble redBlueMarble = new RedBlueMarble();
    static boolean[][][][] visited;
    static int cnt = 0;
    static boolean flag;

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
            count = 0;
        }

        public RedBlueMarble(int redX, int redY, int blueX, int blueY) {
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
        }

        public boolean isEqual(RedBlueMarble marble) {
            if (marble.redX == this.redX && marble.redY == this.redY && marble.blueX == this.blueX && marble.blueY == this.blueY) return true;
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
            if (cnt > 10) return -1;

            for (int i = 0; i < 4; i++) {
                RedBlueMarble sample = new RedBlueMarble(cur);
                int[] marbles = new int[2];
                board[sample.redY][sample.redX] = 'R';
                board[sample.blueY][sample.blueX] = 'B';
                while (true) {
                    int cantMove = 0;

                    int redX = sample.redX + dx[i];
                    int redY = sample.redY + dy[i];
                    int blueX = sample.blueX + dx[i];
                    int blueY = sample.blueY + dy[i];

                    if (marbles[0] == 0) {
                        if (board[redY][redX] == '.') {
                            board[sample.redY][sample.redX] = '.';
                            sample.redX = redX;
                            sample.redY = redY;
                            board[sample.redY][sample.redX] = 'R';
                        } else if (board[redY][redX] == 'O') {
                            marbles[0] = 1;
                            board[sample.redY][sample.redX] = '.';
                        } else if (board[redY][redX] == '#' || board[redY][redX] == 'B') cantMove++;
                    }
                    if (marbles[1] == 0) {
                        if (board[blueY][blueX] == '.') {
                            board[sample.blueY][sample.blueX] = '.';
                            sample.blueX = blueX;
                            sample.blueY = blueY;
                            board[sample.blueY][sample.blueX] = 'B';
                        } else if (board[blueY][blueX] == 'O') {
                            board[sample.blueY][sample.blueX] = '.';
                            marbles[1] = 1;
                        } else if (board[blueY][blueX] == '#' || board[blueY][blueX] == 'R') {
                            cantMove++;
                            if (marbles[0] == 1) return cnt + 1;
                        }
                    }

                    if (cantMove == 2) break;
                    else if (marbles[1] == 1) break;
                }

                if (marbles[1] == 0 && marbles[0] == 1) return cnt + 1;
                else if (marbles[1] == 1 && marbles[0] == 1) return -1;
                board[sample.redY][sample.redX] = board[sample.blueY][sample.blueX] = '.';
                if (!visited[sample.redY][sample.redX][sample.blueY][sample.blueX]) {
                    visited[sample.redY][sample.redX][sample.blueY][sample.blueX] = true;
                    sample.count = cur.count + 1;
                    queue.add(sample);
                }
            }
        }
        return cnt;
    }
}
