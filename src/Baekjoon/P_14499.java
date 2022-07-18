package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class P_14499 {

    static int n, m;
    static int[] command;
    static int[][] map;
    static int[] dice = new int[6];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = info[0];
        m = info[1];
        int[] coordinate = {info[2], info[3]};
        map = new int[n][m];
        for (int i = 0 ; i < n; i++) map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        command = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i : command) if (roll_the_dice(coordinate, i) == 1) bw.write(dice[5] + "\n");
        bw.flush();
    }

    public static int roll_the_dice(int[] coordinate, int command) {
        int tmp;
        int flag = 0;
        int x = coordinate[1];
        int y = coordinate[0];

        switch (command) {
            case 1:
                if (x + 1 < m) {
                    coordinate[1]++;
                    tmp = dice[1];
                    dice[1] = dice[2];
                    dice[2] = dice[3];
                    dice[3] = dice[5];
                    dice[5] = tmp;
                    flag = copy(y, x + 1);
                }
                break;
            case 2:
                if (x - 1 >= 0) {
                    coordinate[1]--;
                    tmp = dice[1];
                    dice[1] = dice[5];
                    dice[5] = dice[3];
                    dice[3] = dice[2];
                    dice[2] = tmp;
                    flag = copy(y, x - 1);
                }
                break;
            case 3:
                if (y - 1 >= 0) {
                    coordinate[0]--;
                    tmp = dice[0];
                    dice[0] = dice[5];
                    dice[5] = dice[4];
                    dice[4] = dice[2];
                    dice[2] = tmp;
                    flag = copy(y - 1, x);
                }
                break;
            case 4:
                if (y + 1 < n) {
                    coordinate[0]++;
                    tmp = dice[0];
                    dice[0] = dice[2];
                    dice[2] = dice[4];
                    dice[4] = dice[5];
                    dice[5] = tmp;
                    flag = copy(y + 1, x);
                }
                break;
        }

        return flag;
    }

    private static int copy(int y, int x) {
        if (map[y][x] == 0) map[y][x] = dice[2];
        else {
            dice[2] = map[y][x];
            map[y][x] = 0;
        }

        return 1;
    }
}