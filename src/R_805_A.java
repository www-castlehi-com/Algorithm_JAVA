import java.io.*;

public class R_805_A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            long m = Long.parseLong(br.readLine());

            long price = 0;
            for (long i = 1; i <= 1000000000; i *= 10) {
                if (m < i) break;
                else price = m - i;
            }

            bw.write(price + "\n");
        }
        bw.flush();
    }
}
