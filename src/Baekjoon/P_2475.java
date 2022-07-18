package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class P_2475 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int sum = 0;
        for (int i : s) {
            sum += (int) Math.pow(i, 2);
        }

        bw.write(Integer.toString(sum % 10));
        bw.flush();
    }
}
