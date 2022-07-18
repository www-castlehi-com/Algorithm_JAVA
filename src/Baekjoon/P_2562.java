package Baekjoon;

import java.io.*;

public class P_2562 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int max = Integer.MIN_VALUE, idx = 0;
        for (int i = 1; i <= 9; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num > max) {
                max = num;
                idx = i;
            }
        }

        bw.write(max + "\n" + idx);
        bw.flush();
    }
}
