package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class P_16931 {

    static int n, m;
    static int[][] cube;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = array[0];
        m = array[1];
        cube = new int[n][m];
        for (int i = 0; i < n; i++) cube[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        bw.write(Integer.toString(surface_area()));
        bw.flush();
    }

    public static int surface_area() {
        int result = n * m * 2;

        for (int i = 0; i < n; i++) {
            int sub_result = 0;
            sub_result += cube[i][0];
            for (int j = 1; j < m; j++) {
                int diff = cube[i][j] - cube[i][j - 1];

                if (diff > 0) sub_result += diff;
            }
            result += sub_result * 2;
        }

        for (int i = 0 ; i < m; i++) {
            int sub_result = 0;
            sub_result += cube[0][i];
            for (int j = 1; j < n; j++) {
                int diff = cube[j][i] - cube[j - 1][i];

                if (diff > 0) sub_result += diff;
            }
            result += sub_result * 2;
        }

        return result;
    }
}
