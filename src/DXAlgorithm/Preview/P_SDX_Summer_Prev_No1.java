package DXAlgorithm.Preview;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_SDX_Summer_Prev_No1 {

    static int n;
    static int[][] area;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        int tc = 0;
        while (tc++ < t) {
            n = Integer.parseInt(br.readLine());

            area = new int[n][n];
            for (int i = 0; i < n; i++) area[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int height = 1;
            int space = 1;
            while (true) {
                visited = new boolean[n][n];
                int subSpace = 0;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (!visited[i][j] && area[i][j] > height) {
                            bfs(height, i, j);
                            subSpace++;
                        }
                    }
                }
                height++;

                if (subSpace == 0) break;
                if (space < subSpace) space = subSpace;
            }
            bw.write("#" + tc + " " + space + "\n");
        }
        bw.flush();
    }

    private static void bfs(int height, int curY, int curX) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{curY, curX});
        visited[curY][curX] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextY = poll[0] + dy[i];
                int nextX = poll[1] + dx[i];

                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) {
                    if (!visited[nextY][nextX] && area[nextY][nextX] > height) {
                        visited[nextY][nextX] = true;
                        queue.add(new int[]{nextY, nextX});
                    }
                }
            }
        }
    }
}
