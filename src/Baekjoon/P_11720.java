package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class P_11720 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

        bw.write(Integer.toString(Arrays.stream(numbers).sum()));
        bw.flush();
    }
}
