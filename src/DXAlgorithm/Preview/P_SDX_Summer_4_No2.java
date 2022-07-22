package DXAlgorithm.Preview;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_SDX_Summer_4_No2 {


    static private class UserSolution {

        int[][] queue;
        int front, rear;
        int map_size;
        boolean[][] visited;
        static int map[][];

        void bfs_init(int map_size, int map[][]) {
            map_size++;
            this.map_size = map_size;
            this.map = new int[map_size][map_size];
            for (int i = 1; i < map_size; i++) {
                for (int j = 1; j < map_size; j++) {
                    this.map[i][j] = map[i - 1][j - 1];
                }
            }
        }

        int bfs(int x1, int y1, int x2, int y2) {
            int[] dx = {-1, 0, 1, 0};
            int[] dy = {0, -1, 0, 1};

            queue = new int[map_size * map_size][3];
            front = rear = 0;
            visited = new boolean[map_size][map_size];

            queue[rear][0] = x1;
            queue[rear][1] = y1;
            queue[rear][2] = 0;
            rear++;
            visited[y1][x1] = true;

            while (front != rear) {
                int pop_x = queue[front][0];
                int pop_y = queue[front][1];
                int pop_cnt = queue[front][2];
                front++;

                if (pop_x == x2 && pop_y == y2) return pop_cnt;

                for (int i =0; i < 4; i++) {
                    int x = pop_x + dx[i];
                    int y = pop_y + dy[i];

                    if (x >= 1 && x < map_size && y >= 1 && y < map_size) {
                        if (!visited[y][x] && map[y][x] == 0) {
                            queue[rear][0] = x;
                            queue[rear][1] = y;
                            queue[rear][2] = pop_cnt + 1;
                            rear++;
                            visited[y][x] = true;
                        }
                    }
                }
            }
            return -1;
        }
    }

    private static UserSolution usersolution = new UserSolution();

    private static BufferedReader br;

    private static int cmd_bfs() throws Exception {

        int score = 100;
        int N, M, x1, y1, x2, y2, dist, ans;
        int[][] map = new int[10][10];
        String str;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        N = Integer.parseInt(str);

        for(int i = 0; i < N; i++) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        usersolution.bfs_init(N, map);

        str = br.readLine();
        M = Integer.parseInt(str);

        for(int i = 1; i <= M; i++) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            ans = Integer.parseInt(st.nextToken());

            dist = usersolution.bfs(x1, y1, x2, y2);

            if(dist != ans) {
                score = 0;
            }
        }
        return score;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("#total score : " + cmd_bfs());
    }
}
