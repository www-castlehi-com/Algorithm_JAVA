package SWEA.Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P_1204 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int test = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine(), " ");
            int[] score = new int[1000];
            for (int i = 0; i < 1000; i++) {
                score[i] = Integer.parseInt(st.nextToken());
            }

            int[] statistic = new int[101];
            for (int num : score) {
                statistic[num]++;
            }

            int maxAns = 0, maxKey = 0;
            for (int i = 100; i >= 0; i--) {
                if (maxAns < statistic[i]) {
                    maxAns = statistic[i];
                    maxKey = i;
                }
            }

            System.out.println("#" + test + " " + maxKey);
        }
    }
}
