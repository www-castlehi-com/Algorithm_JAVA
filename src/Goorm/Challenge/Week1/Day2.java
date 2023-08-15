package Goorm.Challenge.Week1;

import java.io.*;
import java.util.Arrays;

public class Day2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int t = line[0], m = line[1];
        int time = t * 60 + m;
        for (int i = 0; i < n; i++) {
            int c = Integer.parseInt(br.readLine());

            time += c;
        }

        t = 0;
        while (time >= 60) {
            t++;
            if (t >= 24) t = 0;
            time -= 60;
        }
        m = time;

        bw.write(t + " " + m);
        bw.flush();

        bw.close();
        br.close();
    }
}
