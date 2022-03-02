import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class P_1978 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        ArrayList<Boolean> list = new ArrayList<>(1001);
        list.add(false);
        list.add(false);
        for (int i = 2; i <= 1000; i++)
            list.add(i, true);
        for (int i = 2; i * i <= 1000; i++) {
            if (list.get(i)) {
                for (int j = i * i ; j <= 1000; j+=i) list.set(j, false);
            }
        }

        int cnt = 0;

        for (int num : array) {
            if (list.get(num))
                cnt++;
        }
        bw.write(Integer.toString(cnt));
        bw.flush();
    }
}
