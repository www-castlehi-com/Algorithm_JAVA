import java.io.*;

public class P_2739 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        for (int i = 1; i<= 9; i++) {
            bw.write(n + " * " + i + " = " + (n * i) + "\n");
        }
        bw.flush();
    }
}
