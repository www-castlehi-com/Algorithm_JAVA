import java.io.*;
import java.util.*;

public class Main {
    
    private static List<int[]>[] graph;
    private static int[] shortestPath;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());
        graph = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }
        
        shortestPath = new int[v + 1];
        Arrays.fill(shortestPath, Integer.MAX_VALUE);
        shortestPath[k] = 0;
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[v1].add(new int[]{v2, w});
        }
        
        dijkstra(k);
        
        for (int i = 1; i <= v; i++) {
            if (shortestPath[i] == Integer.MAX_VALUE) {
                sb.append("INF\n");
            } else {
                sb.append(shortestPath[i] + "\n");
            }
        }
        
        System.out.print(sb);
    }
    
    private static void dijkstra(int k) {
        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        queue.add(new int[]{k, 0});
        shortestPath[k] = 0;
        while (!queue.isEmpty()) {
            int[] vertex = queue.poll();

            for (int[] nextVertex : graph[vertex[0]]) {
                int nextWeight = shortestPath[vertex[0]] + nextVertex[1];
                if (nextWeight < shortestPath[nextVertex[0]]) {
                    shortestPath[nextVertex[0]] = nextWeight;
                    queue.add(new int[]{nextVertex[0], nextWeight});
                }
            }
        }
    }
}