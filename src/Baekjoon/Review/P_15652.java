package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;

public class P_15652 {

    static int n, m;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1];

        solve(new int[m], 0);

        bw.flush();
    }

    private static void solve(int[] ints, int i) throws IOException {
        if (i == m)
            print(ints);
        else {
            for (int j = 1; j <= n; j++) {
                ints[i] = j;
                if (check_validate(ints, i))
                    solve(ints, i + 1);
            }
        }
    }

    private static void print(int[] ints) throws IOException {
        for (int anInt : ints) {
            bw.write(anInt + " ");
        }
        bw.newLine();
    }

    private static boolean check_validate(int[] ints, int i) {
        for (int j = 0; j < i; j++) {
            if (ints[j] > ints[i]) return false;
        }
        return true;
    }
}
