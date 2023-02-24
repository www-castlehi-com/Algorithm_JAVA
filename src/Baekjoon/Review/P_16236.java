package Baekjoon.Review;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class P_16236 {

    public static class BabyShark {
        int x, y;
        int size;
        int cnt;
        int time;

        public BabyShark(int x, int y, int size, int cnt, int time) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.cnt = cnt;
            this.time = time;
        }

        public BabyShark(BabyShark babyShark) {
            this.x = babyShark.x;
            this.y = babyShark.y;
            this.size = babyShark.size;
            this.cnt = babyShark.cnt;
            this.time = babyShark.time;
        }
    }

    public static class Fish {
        int x, y;
        int size;

        public Fish(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }

    static int n;
    static int[][] map;
    static ArrayList<Fish> fishes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        BabyShark babyShark = null;
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 9) babyShark = new BabyShark(j, i, 2, 0, 0);
                else if (map[i][j] != 0) fishes.add(new Fish(j, i, map[i][j]));
            }
        }

        bw.write(Integer.toString(bfs(babyShark)));
        bw.flush();
    }

    private static int bfs(BabyShark babyShark) {
        LinkedList<BabyShark> queue = new LinkedList<>();
        queue.add(babyShark);

        while (!queue.isEmpty()) {
            BabyShark poll = queue.poll();

            if(fishes.isEmpty()) return poll.time;

            Fish minDistanceFish = getMinDistanceFish(poll);

            if (minDistanceFish == null) return poll.time;

            map[minDistanceFish.y][minDistanceFish.x] = 9;
            map[poll.y][poll.x] = 0;

            poll.y = minDistanceFish.y;
            poll.x = minDistanceFish.x;

            fishes.remove(minDistanceFish);

            poll.cnt++;
            if (poll.cnt == poll.size) {
                poll.cnt = 0;
                poll.size++;
            }

            queue.add(poll);
        }

        return 0;
    }

    private static Fish getMinDistanceFish(BabyShark babyShark) {
        int res = Integer.MAX_VALUE;
        Fish res_fish = null;
        int time = 0;

        for (Fish fish : fishes) {
            if (fish.size < babyShark.size) {
                int distance = bfs(babyShark, fish);
                if (res > distance) {
                    res = distance;
                    res_fish = fish;
                    time = distance;
                }
            }
        }

        babyShark.time += time;
        return res_fish;
    }

    private static int bfs(BabyShark babyShark, Fish fish) {
        int[] y = {-1, 0, 1, 0};
        int[] x = {0, -1, 0, 1};

        LinkedList<BabyShark> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        BabyShark curShark = new BabyShark(babyShark.x, babyShark.y, babyShark.size, babyShark.cnt, 0);
        queue.add(curShark);
        visited[babyShark.y][babyShark.x] = true;

        while (!queue.isEmpty()) {
            BabyShark poll = queue.poll();

            if (poll.y == fish.y && poll.x == fish.x) return poll.time;

            for (int i = 0; i < 4; i++) {
                int nextY = poll.y + y[i];
                int nextX = poll.x + x[i];

                if (nextY >= 0 && nextY < n && nextX >= 0 && nextX < n) {
                    if (!visited[nextY][nextX] && map[nextY][nextX] <= poll.size) {
                        visited[nextY][nextX] = true;
                        BabyShark clone_babyShark = new BabyShark(poll);
                        clone_babyShark.y = nextY;
                        clone_babyShark.x = nextX;
                        clone_babyShark.time++;

                        queue.add(clone_babyShark);
                    }
                }
            }
        }

        return Integer.MAX_VALUE;
    }
}
