package TossNEXT;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P_NEXT2023_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] v = new int[3][2];
        for (int i = 0; i < 3; i++) v[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        HashMap<Integer, Integer> xMap = new HashMap<>();
        HashMap<Integer, Integer> yMap = new HashMap<>();
        for (int[] ints : v) {
            xMap.put(ints[0], xMap.getOrDefault(ints[0], 0) + 1);
            yMap.put(ints[1], yMap.getOrDefault(ints[1], 0) + 1);
        }

        int ansX = 0, ansY = 0;
        for (Map.Entry<Integer, Integer> entry : xMap.entrySet()) {
            if (entry.getValue() == 1) {
                ansX = entry.getKey();
                break;
            }
        }
        for (Map.Entry<Integer, Integer> entry : yMap.entrySet()) {
            if (entry.getValue() == 1) {
                ansY = entry.getKey();
                break;
            }
        }

        bw.write(ansX + " " + ansY);
        bw.flush();
    }
}
