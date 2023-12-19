package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P_3190 {

    static public class Location {
        int x, y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            Location compare = (Location) obj;
            if (x == compare.x && y == compare.y) return true;
            else return false;
        }
    }

    static int n;
    static int[][] map;
    static char[] commands = new char[10001];
    static Queue<Location> snake = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        map[0][0] = -1;
        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            map[line[0] - 1][line[1] - 1] = i + 1;
        }
        int l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            String[] s = br.readLine().split(" ");
            commands[Integer.parseInt(s[0])] = s[1].charAt(0);
        }

        int curX = 0, curY = 0, cur = 0, dir = 1;
        snake.add(new Location(curX, curY));
        while (true) {
            cur++;
            if (dir == 0) curY--;
            else if (dir == 1) curX++;
            else if (dir == 2) curY++;
            else curX--;

            if (!checkRule(curX, curY)) break;

            if (commands[cur] == 'L') dir = (dir - 1 + 4) % 4;
            else if (commands[cur] == 'D') dir = (dir + 1) % 4;
        }

        bw.write(Integer.toString(cur));
        bw.flush();
    }

    private static boolean checkRule(int curX, int curY) {
        if (curX >= 0 && curX < n && curY >= 0 && curY < n && !snake.contains(new Location(curX, curY))) {
            int idx = map[curY][curX];
            if (idx == -1) {
                return false;
            }
            else if (idx != 0) {
                map[curY][curX] = -1;
                snake.add(new Location(curX, curY));
            }
            else {
                map[curY][curX] = -1;
                snake.add(new Location(curX, curY));
                Location tail = snake.poll();
                map[tail.y][tail.x] = 0;
            }
            return true;
        }
        return false;
    }
}
