package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int k = arr[0], n = arr[1];
        int[] lines = new int[k];
        int maxLength = Integer.MIN_VALUE;

        for (int i = 0; i < k; i++) {
            lines[i] = Integer.parseInt(br.readLine());
            if (maxLength < lines[i]) maxLength = lines[i];
        }

        long start = 1, end = maxLength;
        while (start <= end) {
            long mid = (start + end) / 2;
            long sum = 0;

            for (int line : lines) {
                sum += (line / mid);
            }
            if (sum >= n) start = mid + 1;
            else end = mid - 1;
        }

        bw.write(Long.toString(end));
        bw.flush();
    }
}
