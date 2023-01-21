package Baekjoon.Review;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class P_7662 {
    static PriorityQueue<Integer> maxQueue;
    static PriorityQueue<Integer> minQueue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            minQueue = new PriorityQueue<>();
            maxQueue = new PriorityQueue<>(Collections.reverseOrder());
            Map<Integer, Integer> map = new HashMap<>();

            int k = Integer.parseInt(br.readLine());
            while (k-- > 0) {
                String[] line = br.readLine().split(" ");
                char com = line[0].charAt(0);
                int n = Integer.parseInt(line[1]);

                if (com == 'I') {
                    map.put(n, map.getOrDefault(n, 0) + 1);
                    maxQueue.add(n);
                    minQueue.add(n);
                } else if (com == 'D') {
                    if (map.isEmpty()) continue;

                    PriorityQueue<Integer> queue = (n == 1) ? maxQueue : minQueue;

                    adjustMap(queue, map);
                }
            }

            if (map.isEmpty()) bw.write("EMPTY\n");
            else {
                int max = adjustMap(maxQueue, map);
                int min = map.size() > 0 ? adjustMap(minQueue, map) : max;

                bw.write(max + " " + min + "\n");
            }
        }

        bw.flush();
    }

    private static int adjustMap(PriorityQueue<Integer> queue, Map<Integer, Integer> map) {
        int num;
        while (true) {
            num = queue.poll();
            int cnt = map.getOrDefault(num, 0);

            if (cnt == 0) continue;
            else if (cnt == 1) map.remove(num);
            else map.replace(num, cnt - 1);
            break;
        }
        return num;
    }
}
