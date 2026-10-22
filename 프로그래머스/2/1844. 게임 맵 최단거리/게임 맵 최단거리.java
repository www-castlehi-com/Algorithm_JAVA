import java.util.*;

class Solution {
    
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};
    
    public int solution(int[][] maps) {
        return findEnemy(maps);
    }
    
    private int findEnemy(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        
        Queue<int[]> locations = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        
        locations.add(new int[]{0, 0, 1});
        visited[0][0] = true;
        
        while (!locations.isEmpty()) {
            int[] cur = locations.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curCnt = cur[2];
            
            if (curX == m - 1 && curY == n - 1) {
                return curCnt;
            }
            
            for (int i = 0; i < 4; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];
                
                if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n) {
                    if (!visited[nextY][nextX] && maps[nextY][nextX] == 1) {
                        visited[nextY][nextX] = true;
                        locations.add(new int[]{nextX, nextY, curCnt + 1});
                    }
                }
            }
        }
        
        return -1;
    }
}