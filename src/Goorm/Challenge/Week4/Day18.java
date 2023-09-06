package Goorm.Challenge.Week4;

import java.io.*;
import java.util.Arrays;

public class Day18 {

    static int n, m;
    static Line[][] map;

    static class Line {
        int row;
        int column;

        public Line(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1];
        map = new Line[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) map[i][j] = new Line(0, 0);
        }
        for (int i = 0; i < m; i++) {
            String[] s = br.readLine().split(" ");
            int y = Integer.parseInt(s[0]) - 1, x = Integer.parseInt(s[1]) - 1;
            drawLine(y, x, s[2]);
        }

        long cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cnt += (long) map[i][j].row * map[i][j].column;
            }
        }

        bw.write(Long.toString(cnt));
        bw.flush();
    }

    private static void drawLine(int y, int x, String dir) {
        switch (dir)
        {
            case "L" :
                for (int i = 0; i <= x; i++) map[y][i].row++;
                break;
            case "R":
                for (int i = x; i < n; i++) map[y][i].row++;
                break;
            case "U":
                for (int i = 0; i <= y; i++) map[i][x].column++;
                break;
            case "D":
                for (int i = y; i < n; i++) map[i][x].column++;
                break;
        }
    }
}
