package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class P_16946 {

    static int n, m;
    static int[][] map;
    static int[][] copyMap;
    static ArrayList<Loc> walls = new ArrayList<>();
    static int[] idxArray;

    static public class Loc {
        int x, y;

        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1];
        map = new int[n][m];
        copyMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < m; j++) {
                copyMap[i][j] = map[i][j];
                if (map[i][j] == 1) {
                    walls.add(new Loc(j, i));
                    copyMap[i][j] = -1;
                }
            }
        }
        idxArray = new int[n * m + 1];
        mapCheck();

        for (Loc wall : walls) {
            int cnt = 1;

            HashSet<Integer> idxes = new HashSet<>();

            for (int i = 0; i < 4; i++) {
                int nextY = wall.y + dy[i];
                int nextX = wall.x + dx[i];

                if (nextY >= 0 && nextY < n && nextX >= 0 && nextX < m && copyMap[nextY][nextX] != -1)
                    idxes.add(copyMap[nextY][nextX]);
            }

            for (Integer idx : idxes) {
                cnt += idxArray[idx];
            }

            map[wall.y][wall.x] = cnt % 10;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) bw.write(map[i][j] + "");
            bw.newLine();
        }
        bw.flush();
    }

    private static void mapCheck() {
        boolean[][] visited = new boolean[n][m];

        int idx = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && map[i][j] == 0) {
                    bfs(visited, i, j, idx++);
                }
            }
        }
    }

    private static void bfs(boolean[][] visited, int y, int x, int idx) {
        LinkedList<Loc> queue = new LinkedList<>();
        queue.add(new Loc(x, y));

        int cnt = 0;
        while (!queue.isEmpty()) {
            Loc poll = queue.poll();

            if (!visited[poll.y][poll.x]) {
                visited[poll.y][poll.x] = true;
                copyMap[poll.y][poll.x] = idx;
                cnt++;

                for (int i = 0; i < 4; i++) {
                    int nextY = poll.y + dy[i];
                    int nextX = poll.x + dx[i];

                    if (nextY >= 0 && nextY < n && nextX >= 0 && nextX < m && !visited[nextY][nextX] && copyMap[nextY][nextX] != -1) {
                        queue.add(new Loc(nextX, nextY));
                    }
                }
            }
        }

        idxArray[idx] = cnt;
    }
}
