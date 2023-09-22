package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_16724 {

    static int n, m;
    static String[][] map;
    static int[][] visited;
    static int idx = 1;

    static public class Loc {
        int y, x;

        public Loc(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1];
        map = new String[n][m];
        for (int i = 0; i < n; i++) map[i] = br.readLine().split("");
        visited = new int[n][m];

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == 0) {
                    if (bfs(i, j)) cnt++;
                    idx++;
                }
            }
        }

        bw.write(Integer.toString(cnt));
        bw.flush();
    }

    private static boolean bfs(int y, int x) {
        LinkedList<Loc> queue = new LinkedList<>();
        queue.add(new Loc(y, x));

        while (!queue.isEmpty()) {
            Loc poll = queue.poll();

            if (visited[poll.y][poll.x] == 0) {
                visited[poll.y][poll.x] = idx;

                if (map[poll.y][poll.x].equals("L")) queue.add(new Loc(poll.y, poll.x - 1));
                else if (map[poll.y][poll.x].equals("R")) queue.add(new Loc(poll.y, poll.x + 1));
                else if (map[poll.y][poll.x].equals("U")) queue.add(new Loc(poll.y - 1, poll.x));
                else queue.add(new Loc(poll.y + 1, poll.x));
            }
            else if (visited[poll.y][poll.x] != idx) return false;
        }

        return true;
    }
}
