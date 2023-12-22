package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class P_14891 {

    static int[] gear = new int[4];
    static int[] standard = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < 4; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < 8; j++) {
                gear[i] |= (Integer.parseInt(line[j]) << (7 - j));
            }
        }

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int num = line[0] - 1, dir = line[1];
            int east = (gear[num] & (1 << (8 - standard[num] + 5) % 8)) != 0 ? 1 : 0;
            int west = (gear[num] & (1 << (8 - standard[num] + 1) % 8)) != 0 ? 1 : 0;
            changeOtherGear(num - 1, dir * -1, west, 'W');
            changeOtherGear(num + 1, dir * -1, east, 'E');
            changeStandard(num, dir);
        }

        int sum = 0;
        for (int i = 0; i < 4; i++) {
            int stand = (gear[i] & (1 << (7 - standard[i]))) != 0 ? 1 : 0;
            sum += (stand << i);
        }
        bw.write(Integer.toString(sum));
        bw.flush();
    }

    private static void changeStandard(int num, int dir) {
        standard[num] = (standard[num] - dir + 8) % 8;
    }

    private static void changeOtherGear(int num, int dir, int status, char stand) {
        if (num < 0 || num >= 4) return ;

        int east = (gear[num] & (1 << (8 - standard[num] + 5) % 8)) != 0 ? 1 : 0;
        int west = (gear[num] & (1 << (8 - standard[num] + 1) % 8)) != 0 ? 1 : 0;

        if (stand == 'W') {
            if (east != status) {
                changeOtherGear(num - 1, dir * -1, west, 'W');
                changeStandard(num, dir);
            }
        }
        else if (stand == 'E') {
            if (west != status) {
                changeOtherGear(num + 1, dir * -1, east, 'E');
                changeStandard(num, dir);
            }
        }
    }
}
