package Goorm.Challenge.Week2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Day6 {

    static int n;
    static String[] s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        s = br.readLine().split("");

        HashSet<String> set = new HashSet<>();
        ArrayList<String[]> subStrings = new ArrayList<>();
        int idx1 = 0, idx2 = 1;
        while (!(idx1 >= n && idx2 >= n)) {
            String s1 = getString(0, idx1);
            String s2 = getString(idx1, idx2);
            String s3 = getString(idx2, n);

            if (!s1.equals("") && !s2.equals("") && !s3.equals("")) {
                set.add(s1);
                set.add(s2);
                set.add(s3);

                subStrings.add(new String[]{s1, s2, s3});
            }

            idx2++;
            if (idx2 >= n) {
                idx1++;
                idx2 = idx1 + 1;
            }
        }

        ArrayList<String> subString = new ArrayList<>(set);
        Collections.sort(subString);

        int ans = 0;
        for (String[] string : subStrings) {
            int score = 0;
            for (String s1 : string) {
                score += subString.indexOf(s1) + 1;
            }
            ans = Math.max(ans, score);
        }

        bw.write(Integer.toString(ans));
        bw.flush();
    }

    private static String getString(int start, int end) {
        String res = "";

        if (start >= 0 && start <= n && end >= 0 && end <= n) {
            for (int i = start; i < end; i++) {
                res += s[i];
            }
        }

        return res;
    }
}
