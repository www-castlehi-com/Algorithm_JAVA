package Codeforce;

import java.io.*;
import java.util.Arrays;

public class Div3_883_A {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int cnt = 0;
            for (int i = 0; i < n; i++) {
                int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                if (line[0] > line[1]) cnt++;
            }

            bw.write(cnt + "\n");
        }
        bw.flush();
    }
}
