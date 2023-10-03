package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class P_2162 {

    static int n;
    static Line[] lines;
    static int[] parent;

    static public class Point implements Comparable{
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Object o) {
            Point point = (Point) o;

            if (this.x == point.x) return this.y - point.y;
            else return this.x - point.x;
        }
    }

    static public class Line{
        Point p1, p2;

        public Line(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        lines = new Line[n];
        for (int i = 0; i < n; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Point p1 = new Point(line[0], line[1]);
            Point p2 = new Point(line[2], line[3]);
            lines[i] = new Line(p1, p2);
        }
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                if (!isSameParent(i, j)) {
                    if (checkCross(lines[i], lines[j])) {
                        union(i, j);
                    }
                }
            }
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : parent) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        int size = map.size();
        int cnt = 0;
        for (Integer value : map.values()) {
            cnt = Math.max(cnt, value);
        }

        bw.write(size + "\n" + cnt);
        bw.flush();
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    private static boolean isSameParent(int a, int b) {
        if (find(a) == find(b)) return true;
        else return false;
    }

    private static int find(int a) {
        if (parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }

    private static boolean checkCross(Line l1, Line l2) {
        int ccw1 = ccw(l1.p1, l1.p2, l2.p1), ccw2 = ccw(l1.p1, l1.p2, l2.p2);
        int ccw3 = ccw(l2.p1, l2.p2, l1.p1), ccw4 = ccw(l2.p1, l2.p2, l1.p2);

        if (ccw1 * ccw2 <= 0 && ccw3 * ccw4 <= 0) {
            if (ccw1 * ccw2 == 0 && ccw3 * ccw4 == 0) {
                Point temp;

                if (l1.p1.compareTo(l1.p2) > 0) {
                    temp = l1.p1;
                    l1.p1 = l1.p2;
                    l1.p2 = temp;
                }
                if (l2.p1.compareTo(l2.p2) > 0) {
                    temp = l2.p1;
                    l2.p1 = l2.p2;
                    l2.p2 = temp;
                }

                if (l1.p1.compareTo(l2.p2) <= 0 && l1.p2.compareTo(l2.p1) >= 0) return true;
                else return false;
            }
            else return true;
        }
        return false;
    }

    private static int ccw(Point p1, Point p2, Point p3) {
        int res = (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p2.x * p1.y + p3.x * p2.y + p1.x * p3.y);

        if (res > 0) return 1;
        else if (res == 0) return 0;
        else return -1;
    }
}
