import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();
    private static List<Integer>[] graph;
    private static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph[v1].add(v2);
            graph[v2].add(v1);
        }
        for (int i = 1; i <= n; i++) {
            Collections.sort(graph[i]);
        }
        
        visited = new boolean[n + 1];
        dfs(v);
        
        sb.append("\n");
        visited = new boolean[n + 1];
        bfs(v);
        
        System.out.print(sb);
    }
    
    private static void dfs(int v) {
        sb.append(Integer.toString(v) + " ");
        visited[v] = true;
        for (int nextVertex : graph[v]) {
            if (!visited[nextVertex]) {
                dfs(nextVertex);
            }
        }
    }
    
    private static List<Integer> bfs(int v) {
        List<Integer> answer = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        boolean[] visited = new boolean[graph.length];
        visited[v] = true;
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            sb.append(Integer.toString(vertex) + " ");
            
            for (int nextVertex : graph[vertex]) {
                if (!visited[nextVertex]) {
                    visited[nextVertex] = true;
                    queue.add(nextVertex);
                }
            }
        }
        
        return answer;
    }
}