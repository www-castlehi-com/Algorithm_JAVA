import java.util.*;
import java.io.*;

public class Main {
    
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        sb.append(clear(n, m, r, c, d, map));
        System.out.println(sb);
    }
    
    private static int clear(int n, int m, int r, int c, int d, int[][] map) {
        int cnt = 0;
        int curR = r;
        int curC = c;
        int curD = d;
        while (true) {
            if (map[curR][curC] == 0) {
                cnt++;
            }
            map[curR][curC] = 2;
            
            boolean hasNonClearArea = false;
            for (int i = 0; i < 4; i++) {
                int nextY = dy[i] + curR;
                int nextX = dx[i] + curC;
                
                if (nextY >= 0 && nextY < n && nextX >= 0 && nextX < m && map[nextY][nextX] == 0) {
                    hasNonClearArea = true;
                    break;
                }
            }
            
            if (!hasNonClearArea) {
                int nextY = curR + dy[(curD + 2) % 4];
                int nextX = curC + dx[(curD + 2) % 4];
                if (nextY >= 0 && nextY < n && nextX >= 0 && nextX < m && map[nextY][nextX] != 1) {
                    curR = nextY;
                    curC = nextX;
                } else {
                    break;
                }
            } else {
                curD = (curD + 3) % 4;
                
                int nextY = curR + dy[curD];
                int nextX = curC + dx[curD];
                if (nextY >= 0 && nextY < n && nextX >= 0 && nextX < m && map[nextY][nextX] == 0) {
                    curR = nextY;
                    curC = nextX;
                }
            }
        }
        
        return cnt;
    }
}