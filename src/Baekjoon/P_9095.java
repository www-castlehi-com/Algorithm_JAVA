package Baekjoon;

import java.io.*;

public class P_9095 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        int[] num = new int[11];
        num[0] = 0;
        num[1] = 1;
        num[2] = 2;
        num[3] = 4;
        for (int i = 4; i < 11; i++) {
            num[i] = num[i - 1] + num[i - 2] + num[i - 3];
        }

        for (int i = 0; i < t; i++) {
            bw.write(num[Integer.parseInt(br.readLine())] + "\n");
        }
        bw.flush();
    }
}
