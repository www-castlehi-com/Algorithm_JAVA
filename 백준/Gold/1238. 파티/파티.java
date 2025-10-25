import java.io.*;
import java.util.*;

public class Main {
    
    private static List<int[]>[] incoming;
    private static List<int[]>[] outgoing;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        incoming = new ArrayList[n + 1];
        outgoing = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            incoming[i] = new ArrayList<>();
            outgoing[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int town1 = Integer.parseInt(st.nextToken());
            int town2 = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            
            incoming[town1].add(new int[]{town2, time});
            outgoing[town2].add(new int[]{town1, time});
        }
        
        int[] incomingResult = dijkstra(incoming, x);
        int[] outgoingResult = dijkstra(outgoing, x);
        
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, incomingResult[i] + outgoingResult[i]);
        }
        
        sb.append(Integer.toString(answer));
        System.out.print(sb);
    }
    
    private static int[] dijkstra(List<int[]>[] ways, int target) {
        int[] result = new int[ways.length + 1];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[target] = 0;
        
        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.add(new int[]{target, 0});
        while (!pq.isEmpty()) {
            int[] way = pq.poll();
            
            for(int[] nextWay : ways[way[0]]) {
                int nextTime = result[way[0]] + nextWay[1];
                if (nextTime < result[nextWay[0]]) {
                    result[nextWay[0]] = nextTime;
                    pq.add(new int[]{nextWay[0], nextTime});
                }
            }
        }
        
        return result;
    }
}