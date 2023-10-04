package Baekjoon.Class;

import java.io.*;
import java.util.*;

public class P_2568 {

    static int n;
    static ArrayList<ElectricWire> electricWires = new ArrayList<>();
    static ArrayList<Integer> lis = new ArrayList<>();
    static int[] idxes;

    static public class ElectricWire {
        int a;
        int b;

        public ElectricWire(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            electricWires.add(new ElectricWire(line[0], line[1]));
        }
        Collections.sort(electricWires, Comparator.comparing(o -> o.a));
        idxes = new int[n];

        lis.add(electricWires.get(0).b);
        for (int i = 1; i < n; i++) {
            if (lis.get(lis.size() - 1) < electricWires.get(i).b) {
                lis.add(electricWires.get(i).b);
                idxes[i] = lis.size() - 1;
            } else idxes[i] = binarySearch(electricWires.get(i).b);
        }

        int cur = lis.size() - 1;
        ArrayList<Integer> removeItems = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            if (idxes[i] == cur) cur--;
            else removeItems.add(electricWires.get(i).a);
        }
        Collections.sort(removeItems, Comparator.comparing(o -> o));

        bw.write(removeItems.size() + "\n");
        for (Integer removeItem : removeItems) {
            bw.write(removeItem + "\n");
        }
        bw.flush();
    }

    private static int binarySearch(Integer value) {
        int start = 0, end = lis.size() - 1;

        while (start < end) {
            int mid = (start + end) / 2;

            if (lis.get(mid) < value) start = mid + 1;
            else end = mid;
        }
        lis.set(end, value);

        return end;
    }
}
