import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class P_17219 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = s[0], m = s[1];

        HashMap<String, String> personalData = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] data = br.readLine().split(" ");
            personalData.put(data[0], data[1]);
        }

        for (int i = 0; i < m; i++) {
            bw.write(personalData.get(br.readLine()) + "\n");
        }
        bw.flush();
    }
}
