package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P_1786 {

    static String t;
    static String p;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = br.readLine();
        p = br.readLine();

        System.out.println(kmp());
        System.out.print(sb);
    }

    public static int[] makeTable() {
        int n = p.length();
        int[] table = new int[n];

        int idx = 0;
        for (int i = 1; i < n; i++) {
            while (idx > 0 && p.charAt(i) != p.charAt(idx)) {
                idx = table[idx - 1];
            }

            if (p.charAt(i) == p.charAt(idx)) {
                idx++;
                table[i] = idx;
            }
        }

        return table;
    }

    public static int kmp() {
        int[] table = makeTable();

        for (int i : table) {
            System.out.print(i + " ");
        }
        System.out.println();

        int begin = 0, matched = 0;
        int cnt = 0;

        while (begin <= t.length() - p.length()) {
            if (matched < p.length() && t.charAt(begin + matched) == p.charAt(matched)) {
                matched++;
                if (matched == p.length()) {
                    sb.append(begin + 1).append(" ");
                    cnt++;
                }
            }
            else {
                if (matched == 0) {
                    begin++;
                }
                else {begin += matched - table[matched - 1];
                    matched = table[matched - 1];
                }
            }
        }

        return cnt;
    }
}
