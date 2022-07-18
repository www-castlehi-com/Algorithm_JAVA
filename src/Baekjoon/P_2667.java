package Baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class P_2667 {
    
    static int[][] map;
    static boolean[][] visited;
    static int cnt;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        visited = new boolean[n][n];
        map = new int[n][n];
        for (int i = 0; i < n; i++) map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        ArrayList<Integer> section_number = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0 && visited[i][j] == false) {
                    cnt = 1;
                    visited[i][j] = true;
                    dfs(i, j);
                    section_number.add(cnt);
                }
            }
        }

        Collections.sort(section_number);
        bw.write(section_number.size() + "\n");
        for (int cnt : section_number) bw.write(cnt + "\n");
        bw.flush();
    }
    
    public static void dfs(int sx, int sy) {
        int[] x_set = {-1, 0, 1, 0};
        int[] y_set = {0, 1, 0, -1};

        for (int i = 0; i < 4; i++) {
            int x = sx + x_set[i];
            int y = sy + y_set[i];
            
            if (x >= map.length || x < 0 || y < 0 || y >= map.length) continue;
            
            if (visited[x][y] == false && map[x][y] == 1) {
                cnt++;
                visited[x][y] = true;
                dfs(x, y);
            }
        }
    }
}
