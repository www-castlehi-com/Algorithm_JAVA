package Goorm.Challenge.Week2;

import java.io.*;
import java.util.Arrays;

public class Day9 {

    static int[] dx = {-1, 0, 1, 0, 0};
    static int[] dy = {0, -1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = line[0], k = line[1];
        String[][] map = new String[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().split(" ");
        }
        int[][] bomb = new int[n][n];

        int ans = 0;
        for (int i = 0; i < k; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int y = line[0] - 1, x = line[1] - 1;

            for (int j = 0; j < 5; j++) {
                int nextY = y + dy[j];
                int nextX = x + dx[j];

                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) {
                    if (!map[nextY][nextX].equals("#")) {
                        bomb[nextY][nextX]++;
                        if (map[nextY][nextX].equals("@")) bomb[nextY][nextX]++;
                        ans = Math.max(ans, bomb[nextY][nextX]);
                    }
                }
            }
        }

        bw.write(Integer.toString(ans));
        bw.flush();
    }
}
