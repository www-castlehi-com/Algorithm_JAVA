package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_17070 {

    static int n;
    static int[][] map;
    static int cnt = 0;

    static class Pipe {
        int x, y;
        int dir;

        public Pipe(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dfs(new Pipe(1, 0, 0));

        bw.write(Integer.toString(cnt));
        bw.flush();
    }

    private static void dfs(Pipe pipe) {

        if (pipe.x == n - 1 && pipe.y == n - 1) {
            cnt++;
            return ;
        }

        int nextX = pipe.x;
        int nextY = pipe.y;

        if (pipe.dir == 0) {    //가로
            //가로
            nextX = pipe.x + 1;
            if (nextX >= 0 && nextX < n && map[pipe.y][nextX] != 1) dfs(new Pipe(nextX, pipe.y, 0));

            //대각선
            nextX = pipe.x + 1;
            nextY = pipe.y + 1;
            if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && map[pipe.y][nextX] != 1 && map[nextY][pipe.x] != 1 && map[nextY][nextX] != 1)
                dfs(new Pipe(nextX, nextY, 2));
        } else if (pipe.dir == 1) {   //세로
            //세로
            nextY = pipe.y + 1;
            if (nextY >= 0 && nextY < n && map[nextY][pipe.x] != 1) dfs(new Pipe(pipe.x, nextY, 1));

            //대각선
            nextX = pipe.x + 1;
            nextY = pipe.y + 1;
            if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && map[pipe.y][nextX] != 1 && map[nextY][pipe.x] != 1 && map[nextY][nextX] != 1)
                dfs(new Pipe(nextX, nextY, 2));
        } else {  //대각선
            //가로
            nextX = pipe.x + 1;
            if (nextX >= 0 && nextX < n && map[pipe.y][nextX] != 1) dfs(new Pipe(nextX, pipe.y, 0));

            //세로
            nextY = pipe.y + 1;
            if (nextY >= 0 && nextY < n && map[nextY][pipe.x] != 1) dfs(new Pipe(pipe.x, nextY, 1));

            //대각선
            nextX = pipe.x + 1;
            nextY = pipe.y + 1;
            if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && map[pipe.y][nextX] != 1 && map[nextY][pipe.x] != 1 && map[nextY][nextX] != 1)
                dfs(new Pipe(nextX, nextY, 2));
        }
    }
}
