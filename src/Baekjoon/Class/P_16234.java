package Baekjoon.Class;

import java.io.*;
import java.util.*;

class P_16234 {

    static int n;
    static int l, r;
    static int[][] map;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while (true) {
            boolean canMove = false;
            boolean[][] visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        Set<Integer> movers = move(i, j, visited);
                        if (movers.size() != 1) {
                            canMove = true;
                            calculateMover(movers);
                        }
                    }
                }
            }

            if (!canMove) break;
            day++;
        }

        System.out.println(day);
    }

    public static Set<Integer> move(int y, int x, boolean[][] visited) {
        LinkedList<int[]> queue = new LinkedList<int[]>();
        Set<Integer> movers = new HashSet<Integer>();
        queue.add(new int[] {y, x});
        visited[y][x] = true;
        movers.add(y * n + x);

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int curY = poll[0];
            int curX = poll[1];

            for (int i = 0; i < 4; i++) {
                int nextY = curY + dy[i];
                int nextX = curX + dx[i];

                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && !visited[nextY][nextX]) {
                    int diff = Math.abs(map[curY][curX] - map[nextY][nextX]);
                    if (diff >= l && diff <= r) {
                        queue.add(new int[]{nextY, nextX});
                        movers.add(nextY * n + nextX);
                        visited[nextY][nextX] = true;
                    }
                }
            }
        }

        return movers;
    }

    public static void calculateMover(Set<Integer> movers) {
        int totalPopulation = 0;
        int numberOfCity = 0;
        for (Integer mover : movers) {
            int i = mover / n;
            int j = mover % n;

            totalPopulation += map[i][j];
            numberOfCity++;
        }

        int averagePopulation = totalPopulation / numberOfCity;
        for (Integer mover : movers) {
            int i = mover / n;
            int j = mover % n;

            map[i][j] = averagePopulation;
        }
    }
}