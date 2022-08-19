package Baekjoon.Class;

import java.io.*;

public class P_2839 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int cnt = Integer.MAX_VALUE;
        if (n % 3 == 0) cnt = n / 3;

        int bag_5 = n / 5;
        for (int i = 1; i <= bag_5; i++) {
            int sub_cnt = 0;
            if ((n - (i * 5)) % 3 == 0) sub_cnt += i + ((n - (i * 5)) / 3);
            if (sub_cnt != 0 && cnt > sub_cnt) cnt = sub_cnt;
        }

        if (cnt == Integer.MAX_VALUE) bw.write("-1");
        else bw.write(Integer.toString(cnt));
        bw.flush();
    }
}
