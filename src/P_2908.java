import java.io.*;
import java.util.Arrays;

public class P_2908 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] arr = br.readLine().split(" ");
        String a = arr[0], b = arr[1];
        String a_reverse = "", b_reverse = "";
        for (int i = a.length() - 1; i >= 0; i--) {
            a_reverse += a.charAt(i);
            b_reverse += b.charAt(i);
        }

        bw.write(Integer.toString(Math.max(Integer.parseInt(a_reverse), Integer.parseInt(b_reverse))));
        bw.flush();
    }
}
