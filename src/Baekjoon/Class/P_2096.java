package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n + 1][5];
        for (int i = 1; i <= n; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 1; j <= 3; j++) arr[i][j] = line[j - 1];
        }

        int[][] dpMax = new int[n + 1][5];
        int[][] dpMin = new int[n + 1][5];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dpMin[i], Integer.MAX_VALUE);
            for (int j = 1; j <= 3; j++) {
                dpMax[i][j] = arr[i][j] + getMax(dpMax[i - 1][j - 1], dpMax[i - 1][j], dpMax[i - 1][j + 1]);
                dpMin[i][j] = arr[i][j] + getMin(dpMin[i - 1][j - 1], dpMin[i - 1][j], dpMin[i - 1][j + 1]);
            }
        }

        bw.write(Arrays.stream(dpMax[n]).max().getAsInt() + " " + Arrays.stream(dpMin[n]).min().getAsInt());
        bw.flush();
    }

    private static int getMin(int i1, int i2, int i3) {
        int min = Math.min(i1, i2);
        min = Math.min(min, i3);
        return min;
    }

    private static int getMax(int i1, int i2, int i3) {
        int max = Math.max(i1, i2);
        max = Math.max(max, i3);
        return max;
    }
}
