package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class P_11050 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0], k = arr[1];

        int numerator = 1, denominator = 1;
        for (int i = 0; i < k; i++) {
            numerator *= (n - i);
            denominator *= (k - i);
        }

        bw.write(Integer.toString(numerator / denominator));
        bw.flush();
    }
}
