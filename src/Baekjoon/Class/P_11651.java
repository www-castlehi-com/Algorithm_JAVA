package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class P_11651 {

    public static class Coordinate11651 {
        int x;
        int y;

        public Coordinate11651(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        ArrayList<Coordinate11651> Coordinate11651s = new ArrayList<>();
        for (int i = 0 ; i < n; i++) {
            int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Coordinate11651s.add(new Coordinate11651(s[0], s[1]));
        }

        Coordinate11651s.sort(new Comparator<Coordinate11651>() {
            @Override
            public int compare(Coordinate11651 o1, Coordinate11651 o2) {
                if (o1.y == o2.y) return o1.x - o2.x;
                else return o1.y - o2.y;
            }
        });

        for (Coordinate11651 Coordinate11651 : Coordinate11651s) {
            bw.write(Coordinate11651.x + " " + Coordinate11651.y + "\n");
        }
        bw.flush();
    }
}
