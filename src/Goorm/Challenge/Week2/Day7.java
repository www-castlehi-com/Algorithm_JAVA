package Goorm.Challenge.Week2;

import java.io.*;
import java.util.Arrays;

public class Day7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = line[0], k = line[1];
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int ans = 0;
        int[] dy = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
        int[] dx = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 1) {
                    int cnt = 0;
                    for (int l = 0; l < 9; l++) {
                        int checkX = j + dx[l];
                        int checkY = i + dy[l];

                        if (checkX >= 0 && checkX < n && checkY >= 0 && checkY < n && map[checkY][checkX] == 1) cnt++;
                    }
                    if (cnt == k) ans++;
                }
            }
        }

        bw.write(Integer.toString(ans));
        bw.flush();
    }
}
