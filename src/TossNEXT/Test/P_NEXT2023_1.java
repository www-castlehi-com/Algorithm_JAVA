package TossNEXT.Test;

import java.io.*;
import java.util.Arrays;

public class P_NEXT2023_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int n = Integer.parseInt(br.readLine());

        int max = -1;

        for (int i = 0; i <= s.length() - n; i++) {
            if (s.charAt(i) - '0' <= n && s.charAt(i) - '0' > 0) {
                int[] dup = new int[n + 1];
                int panDigital = 0;
                for (int j = i; j < i + n; j++) {
                    int cur = s.charAt(j) - '0';
                    if (cur <= n && cur > 0 && dup[cur] == 0) {
                        dup[cur]++;
                        panDigital = panDigital * 10 + cur;
                    }
                    else break;
                }

                if (Arrays.stream(dup).sum() == n) max = panDigital;
            }
        }

        bw.write(Integer.toString(max));
        bw.flush();
    }
}
