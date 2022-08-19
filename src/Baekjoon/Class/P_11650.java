package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class P_11650 {

    public static class Coordinate11650 {
        int x;
        int y;

        public Coordinate11650(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        ArrayList<Coordinate11650> Coordinate11650s = new ArrayList<>();
        for (int i = 0 ; i < n; i++) {
            int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Coordinate11650s.add(new Coordinate11650(s[0], s[1]));
        }

        Coordinate11650s.sort(new Comparator<Coordinate11650>() {
            @Override
            public int compare(Coordinate11650 o1, Coordinate11650 o2) {
                if (o1.x == o2.x) return o1.y - o2.y;
                else return o1.x - o2.x;
            }
        });

        for (Coordinate11650 Coordinate11650 : Coordinate11650s) {
            bw.write(Coordinate11650.x + " " + Coordinate11650.y + "\n");
        }
        bw.flush();
    }
}
