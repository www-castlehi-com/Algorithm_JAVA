import java.io.*;

public class P_11726 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] rectangle = new int[1001];

        rectangle[1] = 1;
        rectangle[2] = 2;
        for (int i = 3; i <= n; i++) rectangle[i] = (rectangle[i - 1] + rectangle[i - 2]) % 10007;

        bw.write(Integer.toString(rectangle[n]));
        bw.flush();
    }
}
