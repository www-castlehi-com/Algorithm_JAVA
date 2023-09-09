package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class P_1202 {

    static public class Jewel implements Comparable{
        int weigh;
        int price;

        public Jewel(int weigh, int price) {
            this.weigh = weigh;
            this.price = price;
        }

        @Override
        public int compareTo(Object o) {
            Jewel jewel = (Jewel) o;

            if (this.weigh != jewel.weigh) return (this.weigh - jewel.weigh);
            else return -(this.price - jewel.price);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = line[0], k = line[1];
        Jewel[] jewels = new Jewel[n];
        for (int i = 0; i < n; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            jewels[i] = new Jewel(line[0], line[1]);
        }
        Arrays.sort(jewels);
        Integer[] bags = new Integer[k];
        for (int i = 0; i < k; i++) bags[i] = Integer.parseInt(br.readLine());
        Arrays.sort(bags);

        PriorityQueue<Jewel> queue = new PriorityQueue<>(Comparator.comparing(o -> -o.price));
        int idx = 0;
        long ans = 0;
        for (Integer bag : bags) {
            while (idx < n && jewels[idx].weigh <= bag) queue.add(jewels[idx++]);

            if (!queue.isEmpty()) ans += queue.poll().price;
        }

        bw.write(Long.toString(ans));
        bw.flush();
    }
}
