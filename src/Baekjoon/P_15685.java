package Baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class P_15685 {

    static int[][] dragon;
    static boolean[][] map = new boolean[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        dragon = new int[n][4];
        for (int i = 0; i < n; i++) dragon[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int i = 0; i < n; i++) {
            dragon_curve(i);
        }

        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j< 100; j++) {
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) cnt++;
            }
        }
        bw.write(Integer.toString(cnt));
        bw.flush();
    }

    public static void dragon_curve(int idx) {
        Stack<Integer> stack = new Stack<>();

        map[dragon[idx][1]][dragon[idx][0]] = true;
        trace(idx, dragon[idx][2]);
        stack.push((dragon[idx][2] + 1) % 4);

        for (int i = 1; i <= dragon[idx][3]; i++) {
            for (int j = stack.size() - 1; j >= 0; j--) {
                int dir = stack.get(j);

                trace(idx, dir);
                stack.push((dir + 1) % 4);
            }
        }
    }

    public static void trace(int idx, int dir) {
        switch (dir) {
            case 0:
                dragon[idx][0]++;
                break;
            case 1:
                dragon[idx][1]--;
                break;
            case 2:
                dragon[idx][0]--;
                break;
            case 3:
                dragon[idx][1]++;
                break;
        }

        map[dragon[idx][1]][dragon[idx][0]] = true;
    }
}
