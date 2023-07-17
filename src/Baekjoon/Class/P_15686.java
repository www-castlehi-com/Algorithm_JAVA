package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class P_15686 {

    static class Place {
        int x, y, cnt;

        public Place(int x, int y) {
            this.x = x;
            this.y = y;
            this.cnt = 0;
        }

        public Place(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    static int[][] map;
    static ArrayList<Place> chickens;
    static ArrayList<Place> houses;
    static int n, m;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1];
        map = new int[n][n];
        chickens = new ArrayList<>();
        houses = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) houses.add(new Place(j, i));
                else if (map[i][j] == 2) chickens.add(new Place(j, i));
            }
        }

        getChickens(m, 0, 0, new Place[m]);

        bw.write(Integer.toString(min));
        bw.flush();
    }

    private static void getChickens(int m, int cur, int idx, Place[] arr) {
        if (cur == m) {
            int res = 0;

            for (Place house : houses) {
                int tmp = Integer.MAX_VALUE;
                for (Place chicken : arr) {
                    int dist = Math.abs(house.y - chicken.y) + Math.abs(house.x - chicken.x);
                    tmp = Math.min(tmp, dist);
                }
                res += tmp;
            }

            min = Math.min(min, res);
        }
        else {
            for (int i = idx; i < chickens.size(); i++) {
                arr[cur] = chickens.get(i);
                getChickens(m, cur + 1, i + 1, arr);
            }
        }
    }
}
