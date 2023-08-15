package Goorm.Challenge.Week1;

import java.io.*;
import java.util.Arrays;

public class Day1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int w = line[0], r = line[1];
        double rm = w * (1 + r / 30.0);

        bw.write(Integer.toString((int)rm));
        bw.flush();

        bw.close();
        br.close();
    }
}
