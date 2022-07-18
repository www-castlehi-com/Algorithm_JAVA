package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class P_18111 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0], m = arr[1], b = arr[2];
        int[][] blocks = new int[n][m];
        for (int i = 0; i < n; i++) {
            blocks[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int minTime = Integer.MAX_VALUE;
        int resultHeight = 0;
        for (int i = 256; i >= 0; i--) {
            int getBlock = 0;
            int putBlock = 0;
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (blocks[j][k] > i) getBlock += (blocks[j][k] - i);
                    else putBlock += (i - blocks[j][k]);
                }
            }
            if (getBlock + b >= putBlock) {
                int time = 2 * getBlock + putBlock;
                if (minTime > time) {
                    minTime = time;
                    resultHeight = i;
                }
            }
        }

        bw.write(minTime + " " + resultHeight);
        bw.flush();
    }
}
