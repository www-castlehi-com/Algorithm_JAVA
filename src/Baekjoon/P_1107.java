package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P_1107 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cur = 100;
        int tar = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        if (n > 0) {
            int[] btn = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int res;

            int min = Integer.MAX_VALUE;
            if (tar < 100) {
                while (cur >= 0) {
                    if (not_breakdown(cur, btn)) {
                        res = Math.abs(cur - tar) + ((Integer.toString(cur).length() > 100 - cur) ? 100 - cur : Integer.toString(cur).length());
                        min = (min > res) ? res : min;
                    }
                    else {
                        if (cur == tar) min = (min > 100 - cur) ? 100 - cur : min;
                    }
                    cur--;
                }
            } else if (tar == 100) min = 0;
            else {
                while (cur <= 999999) {
                    if (not_breakdown(cur, btn)) {
                        res = Math.abs(cur - tar) + ((Integer.toString(cur).length() > cur - 100) ? cur - 100 : Integer.toString(cur).length());
                        min = (min > res) ? res : min;
                    }
                    else {
                        if (cur == tar) min = (min > cur - 100) ? cur - 100 : min;
                    }
                    cur++;
                }
            }

            System.out.println(min);
        }
        else
            System.out.println((Integer.toString(tar).length() > Math.abs(tar - 100)) ? Math.abs(tar - 100) : Integer.toString(tar).length());
    }

    public static boolean not_breakdown(int cur, int[] btn) {
        int a, b;
        a = cur;

        if (a != 0) {
            while (a != 0) {
                b = a % 10;
                a /= 10;
                for (int i = 0; i < btn.length; i++) {
                    if (b == btn[i]) return false;
                }
            }
        }
        else {
            for (int i = 0; i < btn.length; i++) {
                if (a == btn[i]) return false;
            }
        }
        return true;
    }
}
