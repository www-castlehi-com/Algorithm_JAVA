import java.io.*;
import java.util.LinkedList;

public class P_10026 {

    static int n;
    static String[][] colors;
    static boolean[][] visited;

    public static class Color {
        int y, x;
        String color;

        public Color(int y, int x, String color) {
            this.y = y;
            this.x = x;
            this.color = color;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        colors = new String[n][n];
        for (int i = 0; i < n; i++) {
            colors[i] = br.readLine().split("");
        }

        int colorWeaknessCnt = 0;
        int notColorWeaknessCnt = 0;

        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    bfs(i, j, true);
                    colorWeaknessCnt++;
                }
            }
        }

        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    bfs(i, j, false);
                    notColorWeaknessCnt++;
                }
            }
        }

        bw.write(notColorWeaknessCnt + " " + colorWeaknessCnt);
        bw.flush();
    }

    private static void bfs(int y, int x, boolean colorWeakness) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        LinkedList<Color> queue = new LinkedList<>();
        queue.add(new Color(y, x, colors[y][x]));

        while (!queue.isEmpty()) {
            Color poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = poll.x + dx[i];
                int nextY = poll.y + dy[i];

                if (nextY >= 0 && nextY < n && nextX >= 0 && nextX < n) {
                    if (!visited[nextY][nextX]) {
                        if (colorWeakness && ((colors[nextY][nextX].equals(poll.color)) || ((poll.color.equals("R") || poll.color.equals("G")) && (colors[nextY][nextX].equals("R") || colors[nextY][nextX].equals("G"))))) {
                            visited[nextY][nextX] = true;
                            queue.add(new Color(nextY, nextX, colors[nextY][nextX]));
                        }
                        else if (!colorWeakness && (colors[nextY][nextX].equals(poll.color))) {
                            visited[nextY][nextX] = true;
                            queue.add(new Color(nextY, nextX, colors[nextY][nextX]));
                        }
                    }
                }
            }
        }
    }
}
