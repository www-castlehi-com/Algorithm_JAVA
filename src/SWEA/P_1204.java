package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class P_1204 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int test = Integer.parseInt(br.readLine());
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int[] scores = new int[101];
            for (int num : line) {
                scores[num]++;
            }

            int maxAns = 0, maxKey = 0;
            for (int i = 100; i >= 0; i--) {
                if (maxAns < scores[i]) {
                    maxAns = scores[i];
                    maxKey = i;
                }
            }

            bw.write("#" + test + " " + maxKey + "\n");
        }
        bw.flush();
    }
}
