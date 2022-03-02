import java.io.*;
import java.util.Arrays;

public class P_2309 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = new int[9];
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
        }
        Arrays.sort(arr);

        int i = 0, j = 0;
        int diff = sum - 100;
        boolean flag = false;
        for (i = 0; i < 8; i++) {
            for (j = i + 1; j < 9; j++) {
                if (diff == arr[i] + arr[j]) {
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }

        for (int k = 0; k < 9; k++) {
            if (k != i && k != j)
                bw.write(arr[k] + "\n");
        }
        bw.flush();
    }
}
