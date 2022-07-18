package Baekjoon;

import java.io.*;

public class P_2577 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = 1;
        for (int i = 0; i< 3; i++) {
            num *= Integer.parseInt(br.readLine());
        }

        int[] digit = new int[10];
        while (num != 0) {
            digit[num % 10]++;
            num /= 10;
        }

        for (int i : digit) {
            bw.write(i + "\n");
        }
        bw.flush();
    }
}
