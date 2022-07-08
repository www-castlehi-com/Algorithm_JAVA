import java.io.*;
import java.util.*;

public class P_1916 {

    static int[] minimumCost;
    static ArrayList<int []>[] city;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        city = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) city[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int departure = arr[0], arrival = arr[1], weigh = arr[2];
            city[departure].add(new int[]{arrival, weigh});
        }
        minimumCost = new int[n + 1];
        Arrays.fill(minimumCost, Integer.MAX_VALUE);
        minimumCost[1] = 0;

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Dijkstra(arr[0]);

        bw.write(Integer.toString(minimumCost[arr[1]]));
        bw.flush();
    }

    private static void Dijkstra(int departure) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparing(o -> o[1]));
        queue.add(new int[]{departure, 0});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int cur = poll[0];
            int time = poll[1];

            if (minimumCost[cur] < time) continue;
            for (int i = 0; i < city[cur].size(); i++) {
                int next = city[cur].get(i)[0];
                int nextTime = time + city[cur].get(i)[1];

                if (nextTime < minimumCost[next]) {
                    minimumCost[next] = nextTime;
                    queue.add(new int[]{next, nextTime});
                }
            }
        }
    }
}
