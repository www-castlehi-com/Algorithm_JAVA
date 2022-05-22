import java.io.*;
import java.util.*;

public class P_1546 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        double[] arr = Arrays.stream(br.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();

        double max = 0;
        for (double i : arr) {
            if (max < i) max = i;
        }

        double sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = arr[i] / max * 100;
            sum += arr[i];
        }
        double average = sum / n;

        bw.write(Double.toString(average));
        bw.flush();
    }
}
