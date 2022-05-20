import java.io.*;
import java.util.Arrays;

public class P_1152 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] arr = br.readLine().split(" ");

        int length = arr.length;
        if (length >= 1 && arr[0].equals("")) length--;

        bw.write(Integer.toString(length));
        bw.flush();
    }
}
