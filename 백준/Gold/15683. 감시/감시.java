import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static int n, m;
    static int[][] map;
    static ArrayList<CCTV> cctvs = new ArrayList<>();

    static int minBlindSpot = Integer.MAX_VALUE;

    public static class CCTV {
        int x, y;
        int type;
        int dir;

        public CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
            this.dir = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1];
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < m; j++) {
                if (map[i][j] >= 1 && map[i][j] <= 5) cctvs.add(new CCTV(j, i, map[i][j]));
            }
        }

        watch(0);

        bw.write(Integer.toString(minBlindSpot));
        bw.flush();
    }

    private static void watch(int idx) {
        if (idx == cctvs.size()) {
            findBlindSpot();

//            System.out.println("--------------------------------");
//            System.out.println("minBlindSpot = " + minBlindSpot);
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println("--------------------------------");
//            System.out.println();

        }
        else {
            CCTV cctv = cctvs.get(idx);
            for (int i = 0; i < 4; i++) {
                cctv.dir = i;
                cctvSorter(cctv, -1);
                watch(idx + 1);
                cctvSorter(cctv, 1);
            }
        }
    }

    private static void cctvSorter(CCTV cctv, int token) {
        if (cctv.type == 1) {
            switch (cctv.dir) {
                case 0:
                    watching(cctv.x, cctv.y, 0, -1, token);
                    break;
                case 1:
                    watching(cctv.x, cctv.y, 1, 0, token);
                    break;
                case 2:
                    watching(cctv.x, cctv.y, 0, 1, token);
                    break;
                case 3:
                    watching(cctv.x, cctv.y, -1, 0, token);
                    break;
            }
        }
        else if (cctv.type == 2) {
            switch (cctv.dir) {
                case 0:
                case 2:
                    watching(cctv.x, cctv.y, 0, -1, token);
                    watching(cctv.x, cctv.y, 0, 1, token);
                    break;
                case 1:
                case 3:
                    watching(cctv.x, cctv.y, -1, 0, token);
                    watching(cctv.x, cctv.y, 1, 0, token);
                    break;
            }
        }
        else if (cctv.type == 3) {
            switch (cctv.dir) {
                case 0:
                    watching(cctv.x, cctv.y, 0, -1, token);
                    watching(cctv.x, cctv.y, 1, 0, token);
                    break;
                case 1:
                    watching(cctv.x, cctv.y, 1, 0, token);
                    watching(cctv.x, cctv.y, 0, 1, token);
                    break;
                case 2:
                    watching(cctv.x, cctv.y, 0, 1, token);
                    watching(cctv.x, cctv.y, -1, 0, token);
                    break;
                case 3:
                    watching(cctv.x, cctv.y, -1, 0, token);
                    watching(cctv.x, cctv.y, 0, -1, token);
                    break;
            }
        }
        else if (cctv.type == 4) {
            switch (cctv.dir) {
                case 0:
                    watching(cctv.x, cctv.y, 0, -1, token);
                    watching(cctv.x, cctv.y, -1, 0, token);
                    watching(cctv.x, cctv.y, 1, 0, token);
                    break;
                case 1:
                    watching(cctv.x, cctv.y, 0, -1, token);
                    watching(cctv.x, cctv.y, 1, 0, token);
                    watching(cctv.x, cctv.y, 0, 1, token);
                    break;
                case 2:
                    watching(cctv.x, cctv.y, 1, 0, token);
                    watching(cctv.x, cctv.y, -1, 0, token);
                    watching(cctv.x, cctv.y, 0, 1, token);
                    break;
                case 3:
                    watching(cctv.x, cctv.y, -1, 0, token);
                    watching(cctv.x, cctv.y, 0, 1, token);
                    watching(cctv.x, cctv.y, 0, -1, token);
                    break;
            }
        }
        else if (cctv.type == 5) {
            watching(cctv.x, cctv.y, -1, 0, token);
            watching(cctv.x, cctv.y, 0, 1, token);
            watching(cctv.x, cctv.y, 0, -1, token);
            watching(cctv.x, cctv.y, 1, 0, token);
        }
    }

    private static void watching(int sx, int sy, int dx, int dy, int token) {
        int nextY = sy + dy;
        int nextX = sx + dx;
        if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n) {
            if (map[nextY][nextX] == 6) return ;
            else if (map[nextY][nextX] <= 0) map[nextY][nextX] += token;
            watching(nextX, nextY, dx, dy, token);
        }
    }

    private static void findBlindSpot() {
        int blindSpot = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) blindSpot++;
            }
        }

        minBlindSpot = Math.min(minBlindSpot, blindSpot);
    }
}
