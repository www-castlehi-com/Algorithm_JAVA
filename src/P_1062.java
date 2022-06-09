import java.io.*;
import java.util.Arrays;

public class P_1062 {

    static int[] words;
    static int alpha;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0], k = arr[1];

        if (k < 5) max = 0;
        else if (k == 26) max = n;
        else {
            words = new int[n];
            for (int i = 0; i < n; i++) {
                String word = br.readLine();

                int bit = 0;
                for (int j = 0; j < word.length(); j++) {
                    bit |= 1 << (word.charAt(j) - 'a');
                }
                words[i] = bit;
            }

            alpha |= (1 << 'a' - 'a');
            alpha |= (1 << 'n' - 'a');
            alpha |= (1 << 't' - 'a');
            alpha |= (1 << 'i' - 'a');
            alpha |= (1 << 'c' - 'a');

            readable(k - 5, 0);
        }

        bw.write(Integer.toString(max));
        bw.flush();
    }

    public static void readable(int chance, int next) {
        if (chance == 0) {
            int cnt = 0;
            for (int i = 0; i < words.length; i++) {
                if ((alpha & words[i]) == words[i]) cnt++;
            }
            if (cnt > max) max = cnt;
        }
        else {
            for (int i = next; i < 26; i++) {
                if ((alpha & (1 << i)) == 0) {
                    alpha |= (1 << i);
                    readable(chance - 1, i + 1);
                    alpha &= ~(1 << i);
                }
            }
        }
    }
}
