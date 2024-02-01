package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_12891 {

    static int[] alphabetMatcher = new int[26];
    static int[] cnt = new int[4];
    static int[] term = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        String pw = br.readLine();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            term[i] = Integer.parseInt(st.nextToken());
        }

        alphabetMatcher['C' - 'A'] = 1;
        alphabetMatcher['G' - 'A'] = 2;
        alphabetMatcher['T' - 'A'] = 3;

        int p1 = 0;
        int p2 = p - 1;
        int result = 0;
        for (int i = p1; i <= p2; i++) {
            cnt[alphabetMatcher[pw.charAt(i) - 'A']]++;
        }
        result += checkPasswordTerm(cnt, term);

        while (p2 < s - 1) {
            cnt[alphabetMatcher[pw.charAt(p1++) - 'A']]--;
            cnt[alphabetMatcher[pw.charAt(++p2) - 'A']]++;
            result += checkPasswordTerm(cnt, term);
        }

        System.out.print(result);
    }

    private static int checkPasswordTerm(int[] cnt, int[] term) {
        for (int i = 0; i < 4; i++) {
            if (cnt[i] < term[i]) return 0;
        }
        return 1;
    }
}
