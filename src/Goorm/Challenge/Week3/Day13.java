package Goorm.Challenge.Week3;

import java.io.*;
import java.util.*;

public class Day13 {

    static int n, k;
    static int[][] map;
    static boolean[][] visited;
    static Map<Integer, Integer> apartments = new HashMap<>();

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; k = line[1];
        map = new int[n][n];
        for (int i = 0; i < n; i++) map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        visited = new boolean[n][n];

        int maxApt = 0, maxType = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    int cnt = dfs(j, i, map[i][j]);
                    if (cnt >= k) {
                        apartments.put(map[i][j], apartments.getOrDefault(map[i][j], 0) + 1);

                        if (maxApt < apartments.get(map[i][j])) maxType = map[i][j];
                        else if (maxApt == apartments.get(map[i][j])) maxType = Math.max(maxType, map[i][j]);

                        maxApt = Math.max(maxApt, apartments.get(map[i][j]));
                    }
                }
            }
        }

        bw.write(Integer.toString(maxType));
        bw.flush();
    }

    private static int dfs(int x, int y, int type) {
        Stack<int[]> stack = new Stack<>();
        stack.add(new int[]{y, x});

        int cnt = 0;
        while (!stack.isEmpty()) {
            int[] pop = stack.pop();

            int curY = pop[0];
            int curX = pop[1];

            if (!visited[curY][curX]) {
                visited[curY][curX] = true;
                cnt++;

                for (int i = 0; i < 4; i++) {
                    int nextY = curY + dy[i];
                    int nextX = curX + dx[i];

                    if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && map[nextY][nextX] == type && !visited[nextY][nextX]) {
                        stack.add(new int[]{nextY, nextX});
                    }
                }
            }
        }

        return cnt;
    }
}
