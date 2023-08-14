package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class P_2166 {

    static int n;
    static ArrayList<Point> points;

    static class Point {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public Point(Point point) {
            this.x = point.x;
            this.y = point.y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            long[] line = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            points.add(new Point(line[0], line[1]));
        }
        points.add(new Point(points.get(0)));

        System.out.println(String.format("%.1f", shoelace()));

        br.close();
    }

    private static double shoelace() {
        long sum1 = 0l, sum2 = 0l;

        for (int i = 0; i < n; i++) {
            sum1 += points.get(i).x * points.get(i + 1).y;
            sum2 += points.get(i + 1).x * points.get(i).y;
        }

        return Math.abs(sum1 - sum2) * 0.5;
    }
}
