package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;

public class P_1629 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int a = line[0], b = line[1], c = line[2];

        long ans = solve(a, b, c);

        bw.write(Long.toString(ans));
        bw.flush();
    }

    private static long solve(int a, int b, int c) {
        if (b == 1) return a % c;

        long divide = solve(a, b / 2, c);

        if (b % 2 == 0) return divide * divide % c;
        else return divide * divide % c * a % c;
    }
}
