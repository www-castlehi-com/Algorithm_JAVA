package Baekjoon;

import java.io.*;

public class P_1436 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int number = 666;
        int cnt = 1;
        while (cnt != n) {
            number++;
            if (Integer.toString(number).contains("666")) cnt++;
        }

        bw.write(Integer.toString(number));
        bw.flush();
    }
}
