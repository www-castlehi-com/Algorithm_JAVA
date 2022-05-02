import java.io.*;
import java.util.Arrays;

public class P_14890 {

    static int n, l;
    static int way = 0;
    static int[][] map_2d;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = info[0];
        l = info[1];
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        map_2d = new int[n * 2][n];
        for (int i = 0; i < n; i++) map_2d[i] = map[i];
        for (int i = n; i < 2 * n; i++) {
            for (int j = 0; j < n; j++) map_2d[i][j] = map[j][i - n];
        }

        pre_feasibility_study();
        bw.write(Integer.toString(way));
        bw.flush();
    }

    public static void pre_feasibility_study() {
        for (int i = 0; i < 2 * n ; i++) {
            int standard = map_2d[i][0];
            int cnt = 0;
            visited = new boolean[n];

            for (int j = 0; j < n; j++) {
                if (map_2d[i][j] == standard) cnt++;
                else {
                    if (!construction(i, j, cnt)) break;
                    cnt = 1;
                    standard = map_2d[i][j];
                }

                if (j == n - 1) way++;
            }
        }
    }

    public static boolean construction(int y, int x, int cont) {
        int next = map_2d[y][x];
        int before = map_2d[y][x - 1];

        if (Math.abs(next - before) != 1) return false;
        else if (next - before == 1) {
            if (cont >= l) {
                for (int i = x - 1; i >= x - l; i--) {
                    if (visited[i]) return false;
                    visited[i] = true;
                }
            }
            else return false;
        }
        else {
            int cnt = 0;
            for (int i = x; i < x + l; i++) {
                if (i >= n) return false;
                if (map_2d[y][i] == next) cnt++;
            }
            if (cnt == l) {
                for (int i = x; i < x + l; i++) {
                    if (visited[i]) return false;
                    visited[i] = true;
                }
            }
            else return false;
        }
        return true;
    }
}