package Baekjoon.Class;

import java.io.*;

public class P_15829 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int l = Integer.parseInt(br.readLine());
        String alpha = br.readLine();

        long sum = 0;
        long pow = 1L;
        for (int i = 0; i < alpha.length(); i++) {
            long num = alpha.charAt(i) - 'a' + 1;

            if (i >= 1) pow = (pow * 31) % 1234567891;
            sum += (num * pow) % 1234567891;
        }
        bw.write(Long.toString(sum % 1234567891));
        bw.flush();
    }
}
