package SWEA.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_4012 {

    static int n;
    static int[][] foods = new int[16][16];
    static long result = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int test = 1; test <= t; test++) {
            result = Long.MAX_VALUE;
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    foods[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            boolean[] visited = new boolean[n];
            solve(0, visited, 0);

            sb.append("#").append(test).append(" ").append(result).append("\n");
        }
        System.out.print(sb);
    }

    private static void solve(int idx, boolean[] visited, int cnt) {
        if (cnt == n / 2) {
            long selectedSum = 0;
            long notSelectedSum = 0;

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (visited[i] && visited[j]) {
                        selectedSum += (foods[i][j] + foods[j][i]);
                    }
                    else if (!visited[i] && !visited[j]) {
                        notSelectedSum += (foods[i][j] + foods[j][i]);
                    }
                }
            }

            result = Math.min(result, Math.abs(selectedSum - notSelectedSum));
        }
        else {
            for (int i = idx; i < n; i++) {
                visited[i] = true;
                solve(i + 1, visited, cnt + 1);
                visited[i] = false;
            }
        }
    }
}
