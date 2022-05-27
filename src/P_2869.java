import java.io.*;
import java.util.Arrays;

public class P_2869 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int a = arr[0], b = arr[1], v = arr[2];

        int day = (v - b) / (a - b);
        if ((v - b) % (a - b) != 0) day++;

        bw.write(Integer.toString(day));
        bw.flush();
    }
}
