import java.io.*;
import java.util.Arrays;

public class P_17425 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[] array = new long[1000001];
        Arrays.fill(array, 1);

        for (int i = 2; i <= 1000000; i++) {
            for (int j = 1; i * j <= 1000000; j++) {
                array[i*j] += i;
            }
            array[i] = array[i - 1] + array[i];
        }

        int n = Integer.parseInt(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while(n-- > 0) {
            bw.write(array[Integer.parseInt(br.readLine())] + "\n");
        }
        bw.flush();
    }
}
