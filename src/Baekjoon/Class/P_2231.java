package Baekjoon.Class;

import java.io.*;

public class P_2231 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int flag = 0;

        for (int i = 1; i <= n; i++) {
            int st_n = i;

            int sum = i;
            while (st_n != 0) {
                sum += st_n % 10;
                st_n /= 10;
            }
            if (sum == n) {
                bw.write(Integer.toString(i));
                flag = 1;
                break;
            }
        }
        if (flag == 0) bw.write(Integer.toString(0));
        bw.flush();
    }
}
