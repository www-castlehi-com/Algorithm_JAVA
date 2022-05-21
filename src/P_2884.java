import java.io.*;
import java.util.Arrays;

public class P_2884 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        if (array[0] == 0) array[0] = 24;
        int time = array[0] * 60 + array[1];
        time -= 45;
        if (time / 60 == 24) bw.write(0 + " ");
        else bw.write(time / 60 + " ");
        bw.write(Integer.toString(time % 60));
        bw.flush();
    }
}
