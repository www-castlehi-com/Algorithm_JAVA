package Baekjoon.Class;

import java.io.*;
import java.util.*;

public class P_17143 {

    static int r, c, m;
    static int[][] map;
    static Map<Integer, Shark> sharks = new HashMap<>();

    static public class Shark {
        int r, c, s, d, z;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    static int res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        r = line[0]; c = line[1]; m = line[2];
        map = new int[r][c];
        for (int i = 0; i < m; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            sharks.put(i + 1, new Shark(line[0] - 1, line[1] - 1, line[2], line[3], line[4]));
            map[line[0] - 1][line[1] - 1] = i + 1;
        }

        for (int i = 0; i < c; i++) {
            catchShark(i);
            moveShark();
        }

        bw.write(Integer.toString(res));
        bw.flush();
    }

    private static void moveShark() {
        List<Integer> removeList = new ArrayList<>();
        int[][] copyMap = new int[r][c];

        for (Map.Entry<Integer, Shark> entry : sharks.entrySet()) {
            Shark shark = entry.getValue();

            int dist = shark.s;
            while (dist > 0) {
                int diff = 0;
                switch (shark.d) {
                    case 1:
                        diff = Math.min(shark.r, dist);
                        shark.r -= diff;
                        dist -= diff;
                        if (dist > 0)
                            shark.d = 2;
                        break;
                    case 2:
                        diff = Math.min(r - shark.r - 1, dist);
                        shark.r += diff;
                        dist -= diff;
                        if (dist > 0)
                            shark.d = 1;
                        break;
                    case 3:
                        diff = Math.min(c - shark.c - 1, dist);
                        shark.c += diff;
                        dist -= diff;
                        if (dist > 0)
                            shark.d = 4;
                        break;
                    case 4:
                        diff = Math.min(shark.c, dist);
                        shark.c -= diff;
                        dist -= diff;
                        if (dist > 0)
                            shark.d = 3;
                        break;
                }
            }

            if (copyMap[shark.r][shark.c] != 0) {
                Shark existShark = sharks.get(copyMap[shark.r][shark.c]);
                if (existShark.z > shark.z) removeList.add(entry.getKey());
                else {
                    removeList.add(copyMap[shark.r][shark.c]);
                    copyMap[shark.r][shark.c] = entry.getKey();
                }
            } else copyMap[shark.r][shark.c] = entry.getKey();
        }

        for (Integer integer : removeList) {
            sharks.remove(integer);
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) map[i][j] = copyMap[i][j];
        }
    }

    private static void catchShark(int curC) {
        for (int i = 0; i < r; i++) {
            Shark shark = sharks.getOrDefault(map[i][curC], null);
            if (shark != null) {
                sharks.remove(map[i][curC]);
                res += shark.z;
                break;
            }
        }
    }
}
