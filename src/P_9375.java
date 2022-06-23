import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class P_9375 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            HashMap<String, Integer> clothes = new HashMap<>();

            int cnt = 1;
            for (int i = 0; i < n; i++) {
                String[] type = br.readLine().split(" ");
                clothes.put(type[1], clothes.getOrDefault(type[1], 0) + 1);
            }

            for (Integer value : clothes.values()) {
                cnt *= (value + 1);
            }

            bw.write((cnt - 1) + "\n");
        }
        bw.flush();
    }
}
