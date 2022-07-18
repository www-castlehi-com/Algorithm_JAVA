package Baekjoon;

import java.io.*;

public class P_1748 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int sep = 9;
        int ans = 0;
        int digit = 1;
        while (sep <= n) {
            ans += (digit * sep);
            n -= sep;
            sep *= 10;
            digit++;
        }
        if (sep - n > 0) ans += (digit * n);

        bw.write(Integer.toString(ans));
        bw.flush();
    }
}
