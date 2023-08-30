package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class P_4386 {

    static int n;
    static ArrayList<ZodiacSign> zodiacSigns = new ArrayList<>();
    static int[] parent;

    static public class Star {
        int idx;
        double x, y;

        public Star(int idx, double x, double y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }
    }

    static public class ZodiacSign {
        Star s1, s2;
        double dist;

        public ZodiacSign(Star s1, Star s2, double dist) {
            this.s1 = s1;
            this.s2 = s2;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        Star[] stars = new Star[n];
        for (int i = 0; i < n; i++) {
            double[] line = Arrays.stream(br.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
            stars[i] = new Star(i + 1, line[0], line[1]);
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                zodiacSigns.add(new ZodiacSign(stars[i], stars[j], calcDist(stars[i], stars[j])));
            }
        }
        Collections.sort(zodiacSigns, Comparator.comparing(o -> o.dist));

        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) parent[i] = i;

        bw.write(Double.toString(kruskal()));
        bw.flush();
    }

    private static double kruskal() {
        double total = 0.0;

        for (ZodiacSign zodiacSign : zodiacSigns) {
            if (!isSameParent(zodiacSign.s1, zodiacSign.s2)) {
                total += zodiacSign.dist;

                union(zodiacSign.s1, zodiacSign.s2);
            }
        }

        return total;
    }

    private static void union(Star s1, Star s2) {
        int p1 = find(s1.idx);
        int p2 = find(s2.idx);

        if (p1 != p2) parent[p2] = p1;
    }

    private static boolean isSameParent(Star s1, Star s2) {
        if (find(s1.idx) != find(s2.idx)) return false;
        else return true;
    }

    private static int find(int idx) {
        if (parent[idx] == idx) return idx;
        else return parent[idx] = find(parent[idx]);
    }

    static double calcDist(Star s1, Star s2) {
        return Math.sqrt(Math.pow(s1.x - s2.x, 2) + Math.pow(s1.y - s2.y, 2));
    }
}
