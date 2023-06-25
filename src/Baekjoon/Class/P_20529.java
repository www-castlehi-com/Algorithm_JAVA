package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;

public class P_20529 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            String[] mbtis = br.readLine().split(" ");

            if (n >= 33) {
                bw.write(0 + "\n");
                continue;
            }

            ArrayList<Integer> mbtis_bits = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int conv = convert_bits(mbtis[i]);
                mbtis_bits.add(conv);
            }

            int min = 1000;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    for (int k = j + 1; k < n; k++) {
                        int distance = calculate_distance(mbtis_bits.get(i), mbtis_bits.get(j)) + calculate_distance(mbtis_bits.get(i), mbtis_bits.get(k)) + calculate_distance(mbtis_bits.get(j), mbtis_bits.get(k));
                        if (min > distance) min = distance;
                    }
                }
            }

            bw.write(min + "\n");
        }
        bw.flush();
    }

    private static int calculate_distance(int a, int b) {
        int res = a ^ b;

        switch (res) {
            case 0:
                return 0;
            case 8: case 4: case 2: case 1:
                return 1;
            case 12: case 10: case 9: case 6: case 5: case 3:
                return 2;
            case 14: case 11: case 13: case 7:
                return 3;
            default:
                return 4;
        }
    }

    private static int convert_bits(String mbti) {
        int mbti_bits = 0;

        if (mbti.charAt(0) == 'E') mbti_bits |= 1 << 3;

        if (mbti.charAt(1) == 'S') mbti_bits |= 1 << 2;

        if (mbti.charAt(2) == 'T') mbti_bits |= 1 << 1;

        if (mbti.charAt(3) == 'J') mbti_bits |= 1 << 0;

        return mbti_bits;
    }
}
