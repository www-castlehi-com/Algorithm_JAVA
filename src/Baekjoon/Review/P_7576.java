package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_7576 {

    static int m, n;
    static int[][] box;
    static boolean[][] visited;
    static int result;
    static LinkedList<Tomato> queue;

    private static class Tomato {
        int y, x;
        int days;

        public Tomato(int y, int x, int days) {
            this.y = y;
            this.x = x;
            this.days = days;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        m = line[0]; n = line[1];
        result = 0;
        box = new int[n][m];
        visited = new boolean[n][m];
        queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            box[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < m; j++) {
                if (box[i][j] == 1) {
                    queue.add(new Tomato(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        bfs();

        boolean raped = true;
        Loop :
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j] == 0) {
                    raped = false;
                    break Loop;
                }
            }
        }

        if (raped) bw.write(Integer.toString(result));
        else bw.write(Integer.toString(-1));
        bw.flush();
    }

    static public void bfs() {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        while (!queue.isEmpty()) {
            Tomato poll = queue.poll();

            if (poll.days > result) result = poll.days;

            for (int k = 0; k < 4; k++) {
                int y = poll.y + dy[k];
                int x = poll.x + dx[k];

                if (x >= 0 && x < m && y >= 0 && y < n) {
                    if (!visited[y][x] && box[y][x] == 0) {
                        visited[y][x] = true;
                        box[y][x] = 1;
                        queue.add(new Tomato(y, x, poll.days + 1));
                    }
                }
            }
        }
    }
}
