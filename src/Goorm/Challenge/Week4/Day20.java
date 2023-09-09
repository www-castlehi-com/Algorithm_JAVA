package Goorm.Challenge.Week4;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class Day20 {

    static int n, k, q;
    static String[][] map;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; k = line[1]; q = line[2];
        map = new String[n][n];
        for (int i = 0; i < n; i++) map[i] = br.readLine().split("");
        for (int i = 0; i < q; i++) {
            String[] s = br.readLine().split(" ");

            int y = Integer.parseInt(s[0]) - 1;
            int x = Integer.parseInt(s[1]) - 1;
            map[y][x] = s[2];

            bfs(y, x);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) bw.write(map[i][j]);
            bw.newLine();
        }
        bw.flush();
    }

    private static void bfs(int y, int x) {
        String target = map[y][x];
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[] {y, x});
        boolean[][] visited = new boolean[n][n];
        String[][] copyMap = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) copyMap[i][j] = map[i][j];
        }

        int cnt = 0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            
            int curY = poll[0];
            int curX = poll[1];

            if (!visited[curY][curX]) {
                visited[curY][curX] = true;
                copyMap[curY][curX] = ".";
                cnt++;

                for (int i = 0; i < 4; i++) {
                    int nextY = curY + dy[i];
                    int nextX = curX + dx[i];

                    if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && !visited[nextY][nextX] && map[nextY][nextX].equals(target)) {
                        queue.add(new int[]{nextY, nextX});
                    }
                }
            }
        }

        if (cnt >= k) map = copyMap;
    }
}
