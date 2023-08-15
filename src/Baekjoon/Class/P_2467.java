package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_2467 {

    static int[] arr;
    static int ansP1, ansP2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int p1 = 0, p2 = n - 1;
        ansP1 = p1; ansP2 = p2;

        twoPoint(p1, p2);
        bw.write(arr[ansP1] + " " + arr[ansP2]);
        bw.flush();

        bw.close();
        br.close();
    }

    private static int twoPoint(int p1, int p2) {
        int ans = 2000000000;

        while (p1 < p2) {
            int mix = arr[p1] + arr[p2];

            if (Math.abs(mix) < ans) {
                ans = Math.abs(mix);
                ansP1 = p1;
                ansP2 = p2;
            }

            if (mix < 0) p1++;
            else p2--;
        }

        return ans;
    }
}
