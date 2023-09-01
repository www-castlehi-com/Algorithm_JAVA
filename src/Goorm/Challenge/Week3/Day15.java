package Goorm.Challenge.Week3;

import java.io.*;
import java.util.*;

public class Day15 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = line[0], k = line[1];
        Map<Integer, Long> map = new TreeMap<>(((o1, o2) -> -(o1 - o2)));
        for (int i = 0; i < n; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            map.put(line[1] / line[0], map.getOrDefault(line[1] / line[0], 0L) + line[0]);
        }

        long ans = 0;
        for (Map.Entry<Integer, Long> entry : map.entrySet()) {
            long cnt = (k - entry.getValue() > 0) ? entry.getValue() : k;

            if (cnt == 0) break;
            k -= cnt;
            ans += entry.getKey() * cnt;
        }

        bw.write(Long.toString(ans));
        bw.flush();
    }
}
