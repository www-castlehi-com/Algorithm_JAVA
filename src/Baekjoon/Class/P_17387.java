package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class P_17387 {

    static public class Point implements Comparable{
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Object o) {
            Point point = (Point) o;

            if (point.x == this.x) return (int) (this.y - point.y);
            else return (int) (this.x - point.x);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Point[] points = new Point[4];
        long[] line = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        points[0] = new Point(line[0], line[1]);
        points[1] = new Point(line[2], line[3]);
        line = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        points[2] = new Point(line[0], line[1]);
        points[3] = new Point(line[2], line[3]);

        bw.write(Integer.toString(checkIntersect(points)));
        bw.flush();
    }

    private static int checkIntersect(Point[] points) {
        int cp1 = crossProduct(points[0], points[1], points[2]), cp2 = crossProduct(points[0], points[1], points[3]);
        int cp3 = crossProduct(points[2], points[3], points[0]), cp4 = crossProduct(points[2], points[3], points[1]);

        if (cp1 * cp2 <= 0 && cp3 * cp4 <= 0) {
            if (cp1 * cp2 == 0 && cp3 * cp4 == 0) {
                Point temp;
                if (points[0].compareTo(points[1]) > 0) {
                    temp = points[0];
                    points[0] = points[1];
                    points[1] = temp;
                }
                if (points[2].compareTo(points[3]) > 0) {
                    temp = points[2];
                    points[2] = points[3];
                    points[3] = temp;
                }

                if (points[0].compareTo(points[3]) <= 0 && points[1].compareTo(points[2]) >= 0) return 1;
                else return 0;
            }
            return 1;
        }
        else return 0;
    }

    private static int crossProduct(Point p1, Point p2, Point p3) {
        long res = (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p2.x * p1.y + p3.x * p2.y + p1.x * p3.y);

        if (res > 0) return 1;
        else if (res == 0) return 0;
        else return -1;
    }
}
