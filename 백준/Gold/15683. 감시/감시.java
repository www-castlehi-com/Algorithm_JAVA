import java.io.*;
import java.util.*;

public class Main {
    
    private static int answer = 64;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        List<int[]> cctvs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctvs.add(new int[]{i, j});
                }
            }
        }
        
        dfs(0, cctvs, map);
        
        sb.append(Integer.toString(answer));
        System.out.print(sb);
    }
    
    private static void dfs(int cur, List<int[]> cctvs, int[][] map) {
        if (cur == cctvs.size()) {
            answer = Math.min(answer, getNonWatchField(map));
            return ;
        }
        
        int[] cctv = cctvs.get(cur);
        for (int i = 0; i < 4; i++) {
            checkFields(cctv, map, i, true);
            dfs(cur + 1, cctvs, map);
            checkFields(cctv, map, i, false);
        }
    }
    
    private static int getNonWatchField(int[][] map) {
        int cnt = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
    
    private static void checkFields(int[] cctv, int[][] map, int dir, boolean isWatch) {
        int y = cctv[0];
        int x = cctv[1];
        int type = map[y][x];
        int checker = (isWatch == true) ? -1 : 1;
        
        if (dir == 0) {
            if (type >= 1) {
                checkUp(cctv, map, checker);
            } 
            if (type >= 2 && type != 3) {
                checkDown(cctv, map, checker);
            } 
            if (type >= 3) {
                checkLeft(cctv, map, checker);
            }
            if (type == 5) {
                checkRight(cctv, map, checker);
            }
        } else if (dir == 1) {
            if (type >= 1) {
                checkLeft(cctv, map, checker);
            } 
            if (type >= 2 && type != 3) {
                checkRight(cctv, map, checker);
            } 
            if (type >= 3) {
                checkDown(cctv, map, checker);
            } 
            if (type == 5) {
                checkUp(cctv, map, checker);
            }
        } else if (dir == 2) {
            if (type >= 1) {
                checkDown(cctv, map, checker);
            } 
            if (type >= 2 && type != 3) {
                checkUp(cctv, map, checker);
            } 
            if (type >= 3) {
                checkRight(cctv, map, checker);
            } 
            if (type == 5) {
                checkLeft(cctv, map, checker);
            }
        } else if (dir == 3) {
            if (type >= 1) {
                checkRight(cctv, map, checker);
            }
            if (type >= 2 && type != 3) {
                checkLeft(cctv, map, checker);
            } 
            if (type >= 3) {
                checkUp(cctv, map, checker);
            } 
            if (type == 5) {
                checkDown(cctv, map, checker);
            }
        }
    }
    
    private static void checkUp(int[] cctv, int[][] map, int checker) {
        for (int i = cctv[0]; i >= 0; i--) {
            if (map[i][cctv[1]] == 6) {
                return ;
            }
            if (map[i][cctv[1]] >= 1 && map[i][cctv[1]] <= 5) {
                continue;
            }
            map[i][cctv[1]] += checker;
        }
    }
    
    private static void checkDown(int[] cctv, int[][] map, int checker) {
        for (int i = cctv[0]; i < map.length; i++) {
            if (map[i][cctv[1]] == 6) {
                return ;
            }
            if (map[i][cctv[1]] >= 1 && map[i][cctv[1]] <= 5) {
                continue;
            }
            map[i][cctv[1]] += checker;
        }
    }
    
    private static void checkLeft(int[] cctv, int[][] map, int checker) {
        for (int i = cctv[1]; i >= 0; i--) {
            if (map[cctv[0]][i] == 6) {
                return ;
            }
            if (map[cctv[0]][i] >= 1 && map[cctv[0]][i] <= 5) {
                continue;
            }
            map[cctv[0]][i] += checker;
        }
    }
    
    private static void checkRight(int[] cctv, int[][] map, int checker) {
        for (int i = cctv[1]; i < map[cctv[0]].length; i++) {
            if (map[cctv[0]][i] == 6) {
                return ;
            }
            if (map[cctv[0]][i] >= 1 && map[cctv[0]][i] <= 5) {
                continue;
            }
            map[cctv[0]][i] += checker;
        }
    }
}