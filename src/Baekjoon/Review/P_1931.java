package Baekjoon.Review;

import java.io.*;
import java.util.*;

public class P_1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            list.add(line);
        }
        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
        });

        int cnt = 0;
        int lastTime = 0;
        for (int[] ints : list) {
            if (ints[0] >= lastTime) {
                lastTime = ints[1];
                cnt++;
            }
        }

        bw.write(Integer.toString(cnt));
        bw.flush();
    }
}
