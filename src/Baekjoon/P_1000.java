package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class P_1000 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        bw.write(Integer.toString(s[0] + s[1]));
        bw.flush();
    }
}
