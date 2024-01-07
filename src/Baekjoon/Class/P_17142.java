package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class P_17142 {

    static int n;
    static int m;
    static int[][] map;
    static ArrayList<Virus> viruses = new ArrayList<>();
    static int res = Integer.MAX_VALUE;

    static public class Virus {
        int y, x;
        int time;

        public Virus(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    viruses.add(new Virus(i, j, 0));
                }
            }
        }

        if (checkAllVisit(new boolean[n][n])) System.out.print(0);
        else {
            setViruses(0, 0, new Virus[m]);
            System.out.print(res == Integer.MAX_VALUE ? -1 : res);
        }

    }

    private static void setViruses(int cur, int selectCount, Virus[] selectViruses) {
        if (selectCount == m) {
            int time = bfs(selectViruses);
            if (time != -1) {
                res = Math.min(res, time);
            }
        }
        else {
            for (int i = cur; i < viruses.size(); i++) {
                Virus virus = viruses.get(i);
                selectViruses[selectCount] = virus;
                setViruses(i + 1, selectCount + 1, selectViruses);
            }
        }
    }

    private static int bfs(Virus[] selectViruses) {
        boolean[][] visited = new boolean[n][n];
        LinkedList<Virus> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            Virus virus = selectViruses[i];
            queue.add(virus);
            visited[virus.y][virus.x] = true;
        }

        while (!queue.isEmpty()) {
            Virus poll = queue.poll();
            int curY = poll.y;
            int curX = poll.x;
            int curTime = poll.time;

            for (int i = 0; i < 4; i++) {
                int nextY = curY + dy[i];
                int nextX = curX + dx[i];

                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && !visited[nextY][nextX]) {
                    if (map[nextY][nextX] != 1) {
                        visited[nextY][nextX] = true;
                        queue.add(new Virus(nextY, nextX, curTime + 1));
                        if (checkAllVisit(visited)) {
                            return curTime + 1;
                        }
                    }
                }
            }
        }

        return -1;
    }

    private static boolean checkAllVisit(boolean[][] visited) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
