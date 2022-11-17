package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_10026 {

    static int n;
    static String[][] colors;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        colors = new String[n][n];
        for (int i = 0; i < n; i++) colors[i] = br.readLine().split("");

        boolean[][] visited = new boolean[n][n];
        int origin = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    bfs(i, j, visited, true, colors[i][j]);
                    origin++;
                }
            }
        }

        boolean[][] weakness_visited = new boolean[n][n];
        int weakness = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!weakness_visited[i][j]) {
                    bfs(i, j, weakness_visited, false, colors[i][j]);
                    weakness++;
                }
            }
        }

        bw.write(origin + " " + weakness);
        bw.flush();
    }

    public static void bfs(int i, int j, boolean[][] visited, boolean origin, String color) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int k = 0; k < 4; k++) {
                int x = poll[1] + dx[k];
                int y = poll[0] + dy[k];

                if (x >= 0 && x < n && y >= 0 && y < n && !visited[y][x]) {
                    if (origin) {
                        if (colors[y][x].equals(color)) {
                            queue.add(new int[]{y, x});
                            visited[y][x] = true;
                        }
                    }
                    else {
                        if ((color.equals("R") || color.equals("G")) && (colors[y][x].equals("R") || colors[y][x].equals("G"))) {
                            visited[y][x] = true;
                            queue.add(new int[]{y, x});
                        }
                        else if (colors[y][x].equals(color)) {
                            visited[y][x] = true;
                            queue.add(new int[]{y, x});
                        }
                    }
                }
            }
        }
    }
}
