package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_9328 {

    static int h, w;
    static String[][] map;
    static int[] keys;
    static int ans;

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

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            h = line[0]; w = line[1];
            map = new String[h + 2][w + 2];
            for (int i = 0; i < h; i++) {
                String[] s = br.readLine().split("");
                for (int j = 1; j <= w; j++) map[i + 1][j] = s[j - 1];
            }
            keys = new int[26];
            String[] s = br.readLine().split("");
            for (int i = 0; i < s.length; i++) {
                if (s[i].charAt(0) != '0') keys[s[i].charAt(0) - 'a'] = 1;
            }
            ans = 0;

            bfs();

            bw.write(ans + "\n");
        }
        bw.flush();
    }

    private static void bfs() {
        LinkedList<Loc> queue = new LinkedList<>();
        for (int i = 0; i <= w + 1; i++) {
            queue.add(new Loc(i, 0));
            queue.add(new Loc(i, h + 1));
        }
        for (int i = 0; i <= h + 1; i++) {
            queue.add(new Loc(0, i));
            queue.add(new Loc(w + 1, i));
        }

        LinkedList<Loc>[] keyQueue = new LinkedList[26];
        for (int i = 0; i < 26; i++) keyQueue[i] = new LinkedList<Loc>();

        boolean[][] visited = new boolean[h + 2][w + 2];

        while (!queue.isEmpty()) {
            Loc poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextY = poll.y + dy[i];
                int nextX = poll.x + dx[i];

                if (nextY >= 1 && nextY <= h && nextX >= 1 && nextX <= w && !visited[nextY][nextX] && !map[nextY][nextX].equals("*")) {
                    char target = map[nextY][nextX].charAt(0);
                    visited[nextY][nextX] = true;

                    if (target >= 'a' && target <= 'z') {
                        keys[target - 'a'] = 1;
                        queue.add(new Loc(nextX, nextY));
                        while (!keyQueue[target - 'a'].isEmpty()) {
                            queue.add(keyQueue[target - 'a'].poll());
                        }
                    }
                    else if (target >= 'A' && target <= 'Z') {
                        if (keys[target - 'A'] == 1) {
                            queue.add(new Loc(nextX, nextY));
                        }
                        else keyQueue[target - 'A'].add(new Loc(nextX, nextY));
                    }
                    else if (target == '$') {
                        ans++;
                        queue.add(new Loc(nextX, nextY));
                    }
                    else if (target == '.') queue.add(new Loc(nextX, nextY));
                }
            }
        }
    }
}
