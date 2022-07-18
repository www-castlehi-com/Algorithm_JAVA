package Baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class P_16236 {

    static int n;
    static int[][] space;
    static ArrayList<Fish> fishes = new ArrayList<>();

    public static class BabyShark {
        int size;
        int number;
        int x, y;
        int second;

        public BabyShark() {
        }

        public BabyShark(int size, int number, int x, int y, int second) {
            this.size = size;
            this.number = number;
            this.x = x;
            this.y = y;
            this.second = second;
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        space = new int[n][n];
        BabyShark babyShark = new BabyShark();
        for (int i = 0; i < n; i++) {
            space[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < n; j++) {
                if (space[i][j] == 9) babyShark = new BabyShark(2, 0, j, i, 0);
                else if (space[i][j] != 0) {
                    fishes.add(new Fish(j, i, space[i][j]));
                }
            }
        }

        fishes.sort(new Comparator<Fish>() {
            @Override
            public int compare(Fish o1, Fish o2) {
                if (o1.y - o2.y != 0) return o1.y - o2.y;
                else return o1.x - o2.x;
            }
        });

        bw.write(Integer.toString(bfs(babyShark)));
        bw.flush();
    }

    private static int bfs(BabyShark curShark) {
        LinkedList<BabyShark> queue = new LinkedList<>();
        queue.add(curShark);

        while (!queue.isEmpty()) {
            BabyShark poll = queue.poll();

            if (fishes.isEmpty()) {
                return poll.second;
            }

            Fish fish = GetMinDistance(poll);

            if (fish == null) return poll.second;

            fishes.remove(fish);
            space[fish.y][fish.x] = 9;
            space[poll.y][poll.x] = 0;

            poll.number++;
            if (poll.number >= poll.size) {
                poll.size++;
                poll.number = 0;
            }
            poll.y = fish.y;
            poll.x = fish.x;

            queue.add(poll);
        }
        return 0;
    }

    private static Fish GetMinDistance(BabyShark poll) {
        int min = Integer.MAX_VALUE;
        Fish minFish = null;
        int pollSecond = 0;

        for (Fish fish : fishes) {
            if (fish.size < poll.size) {
                int distance = bfsFish(poll, fish);
                if (distance < min) {
                    min = distance;
                    pollSecond = distance;
                    minFish = fish;
                }
            }
        }

        poll.second += pollSecond;

        return minFish;
    }

    private static int bfsFish(BabyShark shark, Fish fish) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        LinkedList<BabyShark> queue = new LinkedList<>();
        BabyShark curShark = new BabyShark(shark.size, shark.number, shark.x, shark.y, 0);
        queue.add(curShark);
        boolean[][] visited = new boolean[n][n];
        visited[curShark.y][curShark.x] = true;

        while (!queue.isEmpty()) {
            BabyShark poll = queue.poll();

            if (poll.y == fish.y && poll.x == fish.x) {
                return poll.second;
            }

            for (int i = 0; i < 4; i++) {
                int x = poll.x + dx[i];
                int y = poll.y + dy[i];

                if (y >= 0 && y < n && x >= 0 && x < n) {
                    if (!visited[y][x]) {
                        if (space[y][x] == 0 || (space[y][x] != 0 && space[y][x] <= poll.size)) {
                            visited[y][x] = true;
                            queue.add(new BabyShark(poll.size, poll.number, x, y, poll.second + 1));
                        }
                    }
                }
            }
        }

        return Integer.MAX_VALUE;
    }
}
