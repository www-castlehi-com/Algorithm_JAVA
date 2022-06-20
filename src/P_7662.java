import java.io.*;
import java.util.*;

public class P_7662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            Map<Integer, Integer> map = new HashMap<>();
            PriorityQueue<Integer> minQueue = new PriorityQueue<>();
            PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());

            int q = Integer.parseInt(br.readLine());
            for (int i = 0; i < q; i++) {
                String[] arr = br.readLine().split(" ");
                int n = Integer.parseInt(arr[1]);

                if (arr[0].charAt(0) == 'I') {
                    map.put(n, map.getOrDefault(n, 0) + 1);

                    minQueue.add(n);
                    maxQueue.add(n);
                }
                else {
                    if (map.size() == 0) continue;

                    PriorityQueue<Integer> queue = n == 1 ? maxQueue : minQueue;
                    removeMap(queue, map);
                }
            }

            if (map.size() == 0) bw.write("EMPTY\n");
            else {
                int max = removeMap(maxQueue, map);
                int min = map.size() > 0 ? removeMap(minQueue, map) : max;
                bw.write(max + " " + min + "\n");
            }
        }
        bw.flush();
    }

    private static int removeMap(PriorityQueue<Integer> queue, Map<Integer, Integer> map) {
        int num;

        while(true) {
            num = queue.poll();
            int cnt = map.getOrDefault(num, 0);

            if (cnt == 0) continue;
            if (cnt == 1) map.remove(num);
            else map.put(num, cnt - 1);
            break;
        }
        return num;
    }
}
