package Goorm.Challenge.Week2;

import java.io.*;

public class Day8 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] healthItem = {14, 7, 1};
        int cnt = 0;
        for (int item : healthItem) {
            cnt += n / item;
            n %= item;
        }

        bw.write(Integer.toString(cnt));
        bw.flush();
    }
}
