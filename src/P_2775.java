import java.io.*;

public class P_2775 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0 ; i < t; i++) {
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());
            int[] basic_resident = new int[n + 1];
            for (int l = 1; l <= n; l++) basic_resident[l] = l;
            int[] target_resident = new int[n + 1];

            for (int j = 1; j <= k; j++) {
                target_resident = new int[n + 1];
                for (int l = n; l >= 1; l--) {
                    for (int m = l; m >= 1; m--) target_resident[l] += basic_resident[m];
                }
                basic_resident = target_resident;
            }
            bw.write(target_resident[n] + "\n");
        }
        bw.flush();
    }
}
