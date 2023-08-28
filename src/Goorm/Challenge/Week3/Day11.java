package Goorm.Challenge.Week3;

import java.io.*;
import java.util.Arrays;

public class Day11 {

    static final int MAX = 1000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] items = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int ans = MAX;
        for (int i = n / items[1]; i >= 0; i--) {
            int nCopy = n - items[1] * i;
            int cnt = i;

            cnt += (nCopy / items[0]);
            nCopy %= items[0];

            if (nCopy == 0) {
                ans = cnt;
                break;
            }
        }

        if (ans == MAX) bw.write(Integer.toString(-1));
        else bw.write(Integer.toString(ans));
        bw.flush();
    }
}
