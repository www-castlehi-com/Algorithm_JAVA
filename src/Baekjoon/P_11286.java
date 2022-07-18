package Baekjoon;

import java.io.*;
import java.util.*;

public class P_11286 {

    static HashMap<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<Integer> positiveQueue = new PriorityQueue<>();
        PriorityQueue<Integer> negativeQueue = new PriorityQueue<>(Collections.reverseOrder());
        map = new HashMap<>();

        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            int x = Integer.parseInt(br.readLine());
            if (x != 0) map.put(x, map.getOrDefault(x, 0) + 1);

            if (x > 0) positiveQueue.add(x);
            else if (x < 0) negativeQueue.add(x);
            else {
                if (map.size() == 0) bw.write("0\n");
                else {
                    int small;
                    if (positiveQueue.size() != 0 && negativeQueue.size() != 0) {
                        if (Math.abs(positiveQueue.peek()) > Math.abs(negativeQueue.peek())) small = negativeQueue.peek();
                        else if (Math.abs(positiveQueue.peek()) < Math.abs(negativeQueue.peek())) small = positiveQueue.peek();
                        else small = positiveQueue.peek() > negativeQueue.peek() ? negativeQueue.peek() : positiveQueue.peek();
                    }
                    else if (negativeQueue.size() == 0) small = positiveQueue.peek();
                    else small = negativeQueue.peek();

                    bw.write(small + "\n");

                    if (small < 0) RemoveMap(negativeQueue, small);
                    else RemoveMap(positiveQueue, small);
                }
            }
        }

        bw.flush();
    }

    private static void RemoveMap(PriorityQueue<Integer> queue, int target) {
        queue.poll();
        int cnt = map.get(target);

        if (cnt == 1) {
            map.remove(target);
        }
        else {
            map.replace(target, cnt - 1);
        }
    }
}
