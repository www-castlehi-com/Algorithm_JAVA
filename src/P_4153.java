import java.io.*;
import java.util.Arrays;

public class P_4153 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int x = arr[0], y = arr[1], z = arr[2];
            if (x == 0 && y == 0 && z == 0) break;

            int max = x;
            int flag = 0;
            if (y > max) {
                max = y;
                flag = 1;
            }
            if (z > max) {
                max = z;
                flag = 2;
            }

            if (flag == 0) {
                if (x * x == y * y + z * z) bw.write("right\n");
                else bw.write("wrong\n");
            }
            else if (flag == 1) {
                if (y * y == z * z + x * x) bw.write("right\n");
                else bw.write("wrong\n");
            }
            else {
                if (z * z == x * x + y * y) bw.write("right\n");
                else bw.write("wrong\n");
            }
        }

        bw.flush();
    }
}
