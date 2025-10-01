import java.util.*;

class Solution {
    
    private static LinkedList<Integer>[] graph;
    private static boolean[] visit;
    private static int maxDistance = 0;
    private static int answer = 0;
    
    public int solution(int n, int[][] edge) {
        graph = addVertex(n, edge);
        visit = new boolean[n + 1];
        
        bfs();
        
        return answer;
    }
    
    private LinkedList<Integer>[] addVertex(int n, int[][] edge) {
        LinkedList<Integer>[] graph = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }
        
        for (int i = 0; i < edge.length; i++) {
            graph[edge[i][0]].add(edge[i][1]);
            graph[edge[i][1]].add(edge[i][0]);
        }
        
        return graph;
    }
    
    private void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 0});
        visit[1] = true;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curVertex = cur[0];
            int distance = cur[1];
            
            if (distance > maxDistance) {
                maxDistance = distance;
                answer = 1;
            } else if (distance == maxDistance) {
                answer++;
            }
            
            for (int nextVertex : graph[curVertex]) {
                if (!visit[nextVertex]) {
                    queue.add(new int[]{nextVertex, distance + 1});
                    visit[nextVertex] = true;
                }
            }
        }
    }
}