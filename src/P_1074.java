import java.io.*;
import java.util.Arrays;

public class P_1074 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0], r = arr[1], c = arr[2];

        int length = (int) Math.pow(2, n);
        int cnt = 0;
        while (length > 1) {
            if (r < length /2 && c >= length / 2) {
                cnt += (length / 2) * (length / 2);
                c -= length / 2;
            }
            else if (r >= length / 2 && c < length / 2) {
                cnt += (length / 2) * (length / 2) * 2;
                r -= length / 2;
            }
            else if (r >= length / 2 && c >= length / 2) {
                cnt += (length / 2) * (length / 2) * 3;
                r -= length / 2;
                c -= length / 2;
            }

            length /= 2;
        }

        bw.write(Integer.toString(cnt));
        bw.flush();
    }
}
