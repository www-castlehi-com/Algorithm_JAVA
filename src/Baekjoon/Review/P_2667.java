package Baekjoon.Review;

import java.io.*;
import java.util.*;

public class P_2667 {

    static int n;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        visited = new boolean[n][n];

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && map[i][j] == 1) list.add(bfs(i, j));
            }
        }
        Collections.sort(list);

        bw.write(list.size() + "\n");
        for (Integer integer : list) {
            bw.write(integer + "\n");
        }
        bw.flush();
    }

    private static int bfs(int y, int x) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});
        visited[y][x] = true;

        int cnt = 0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            cnt++;

            for (int i = 0; i < 4; i++) {
                int nextY = poll[0] + dy[i];
                int nextX = poll[1] + dx[i];

                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) {
                    if (!visited[nextY][nextX] && map[nextY][nextX] == 1) {
                        queue.add(new int[]{nextY, nextX});
                        visited[nextY][nextX] = true;
                    }
                }
            }
        }

        return cnt;
    }
}
