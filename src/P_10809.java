import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class P_10809 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] alpha = new int[26];
        for (int i = 0; i< 26; i++) alpha[i] = -1;

        String s = br.readLine();
        for (int i = 0; i < s.length(); i++) {
            if (alpha[s.charAt(i) - 'a'] == -1) alpha[s.charAt(i) - 'a'] = i;
        }

        for (int i = 0; i< 26; i++) bw.write(alpha[i] + " ");
        bw.flush();
    }
}
