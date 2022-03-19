import java.io.*;
import java.util.Arrays;

public class P_1182 {

    static int[]            info;
    static int[]            seq;
    static int              result = 0;
    static BufferedWriter   bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        find_subseq(0, 0);

        if (info[1] == 0) result--;
        bw.write(Integer.toString(result));
        bw.flush();
    }

    public static void find_subseq(int cnt, int sum) {
        if (cnt == info[0]) {
            if (sum == info[1]) result += 1;
        }
        else {
            find_subseq(cnt + 1, sum);
            find_subseq(cnt + 1, sum + seq[cnt]);
        }
    }
}
