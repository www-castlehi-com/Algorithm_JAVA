import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int[] line = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = line[j];
                
                if (i - 1 >= 0 && j - 1 >= 0 && map[i][j] != 0) {
                    if (map[i - 1][j] != 0 && map[i][j - 1] != 0) {
                        int target = Math.min(map[i - 1][j], map[i][j - 1]);
                        if (map[i - target][j - target] != 0) {
                            map[i][j] = target + 1;
                        } else {
                            map[i][j] = target;
                        }
                    }
                }
                
                answer = Math.max(answer, map[i][j]);
            }
        }
        
        sb.append(Integer.toString(answer * answer));
        System.out.print(sb);
    }
}