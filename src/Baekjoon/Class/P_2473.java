package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_2473 {

    static final long MAX = 3000000000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] liquids = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(liquids);

        long ans = MAX;
        int ansP1 = 0, ansP2 = 0, ansP3 = 0;
        Loop1:
        for (int i = 0; i < n - 2; i++) {
            int p1 = i + 1, p2 = n - 1;
            while (p1 < p2) {
                long mix = (long) liquids[i] + liquids[p1] + liquids[p2];

                if (ans > Math.abs(mix)) {
                    ansP1 = i; ansP2 = p1; ansP3 = p2;
                    ans = Math.abs(mix);
                }

                if (mix < 0) p1++;
                else if (mix == 0) break Loop1;
                else p2--;
            }
        }

        bw.write(liquids[ansP1] + " " + liquids[ansP2] + " " + liquids[ansP3]);
        bw.flush();
    }
}