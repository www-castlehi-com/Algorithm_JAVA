import java.io.*;
import java.util.*;

public class P_1157 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ArrayList<Integer> alpha = new ArrayList<>();
        for (int i = 0; i < 26; i++) alpha.add(0);
        String s = br.readLine().toLowerCase(Locale.ROOT);

        for (int i =0; i < s.length(); i++) {
            alpha.set(s.charAt(i) - 'a', alpha.get(s.charAt(i) - 'a') + 1);
        }

        int max = Collections.max(alpha);
        int cnt = 0;
        int idx = 0;
        for (int i = 0; i < 26; i++) {
            if (alpha.get(i) == max) {
                cnt++;
                idx = i;
            }
        }

        if (cnt > 1) bw.write("?");
        else bw.write(idx + 'A');
        bw.flush();
    }
}
