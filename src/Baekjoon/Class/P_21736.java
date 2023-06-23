package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_21736 {

    static int n, m;
    static String[][] map;
    static int max = 0;

    static class Doyeon {
        int x, y;

        public Doyeon(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1];

        Doyeon doyeon = null;

        map = new String[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().split("");
            for (int j = 0; j < m; j++) if (map[i][j].equals("I")) doyeon = new Doyeon(j, i);
        }

        bfs(doyeon);

        if (max != 0) bw.write(Integer.toString(max));
        else bw.write("TT");
        bw.flush();
    }

    private static void bfs(Doyeon doyeon) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        boolean[][] visited = new boolean[n][m];
        LinkedList<Doyeon> queue = new LinkedList<>();
        visited[doyeon.y][doyeon.x] = true;
        queue.add(doyeon);

        while (!queue.isEmpty()) {
            Doyeon pop = queue.pop();

            int prev_y = pop.y;
            int prev_x = pop.x;

            for (int i = 0; i < 4; i++) {
                int next_y = prev_y + dy[i];
                int next_x = prev_x + dx[i];

                if (next_y >= 0 && next_y < n && next_x >= 0 && next_x < m) {
                    if (!visited[next_y][next_x] && !map[next_y][next_x].equals("X")) {
                        if (map[next_y][next_x].equals("P")) max++;

                        queue.add(new Doyeon(next_x, next_y));
                        visited[next_y][next_x] = true;
                    }
                }
            }
        }
    }
}
