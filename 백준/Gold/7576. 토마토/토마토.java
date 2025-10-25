import java.io.*;
import java.util.*;

public class Main {
    
    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, -1, 0, 1};
    
    private static Queue<int[]> tomatoes = new LinkedList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    tomatoes.add(new int[]{i, j, 0});
                }
            }
        }
        
        int result = bfs(map);
        if (!isAvailability(map)) {
            result = -1;
        }
        
        sb.append(Integer.toString(result));
        System.out.print(sb);
    }
    
    private static int bfs(int[][] map) {
        int maxDay = 0;
        while (!tomatoes.isEmpty()){
            int[] tomato = tomatoes.poll();
            int y = tomato[0];
            int x = tomato[1];
            int day = tomato[2];
            maxDay = day;
            
            for (int i = 0; i < 4; i++) {
                int nextY = y + dy[i];
                int nextX = x + dx[i];
                
                if (nextY >= 0 && nextY < map.length && nextX >= 0 && nextX < map[nextY].length && map[nextY][nextX] == 0) {
                    map[nextY][nextX] = 1;
                    tomatoes.add(new int[]{nextY, nextX, day + 1});
                }
            }
        }
        
        return maxDay;
    }
    
    private static boolean isAvailability(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) {
                    return false;
                }
            }
        }
        
        return true;
    }
}