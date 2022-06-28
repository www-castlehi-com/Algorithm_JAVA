import java.io.*;

public class P_5525 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        String s = br.readLine();

        int[] cnt = new int[m];
        int result = 0;
        for (int i = 1; i < m - 1; i++) {
            if (s.charAt(i) == 'O' && s.charAt(i + 1) == 'I') {
                cnt[i + 1] = cnt[i - 1] + 1;

                if (cnt[i + 1] >= n && s.charAt(i - 2 * n + 1) == 'I') result++;
            }
        }

        bw.write(Integer.toString(result));
        bw.flush();
    }
}

