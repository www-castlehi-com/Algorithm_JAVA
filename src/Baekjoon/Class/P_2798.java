package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_2798 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0], m = arr[1];
        int[] cards = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int sum;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    sum = cards[i] + cards[j] + cards[k];

                    if (sum <= m && max < sum) max = sum;
                }
            }
        }

        bw.write(Integer.toString(max));
        bw.flush();
    }
}
