import java.io.*;
import java.util.Arrays;

public class P_10430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int a = array[0];
        int b = array[1];
        int c = array[2];

        bw.write((a + b) % c + "\n");
        bw.write(((a % c) + (b % c)) % c + "\n");
        bw.write((a * b) % c + "\n");
        bw.write(((a % c) * (b % c)) % c + "\n");

        bw.flush();
    }
}
