package SWEA.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P_1247 {

    static public class Location {
        int x, y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n;
    static ArrayList<Location> locations;
    static Location company;
    static Location house;

    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        for (int test = 1; test <= t; test++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            locations = new ArrayList<>();
            for (int i = 0; i < n + 2; i++) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                if (i == 0) {
                    company = new Location(x, y);
                }
                else if (i == 1) {
                    house = new Location(x, y);
                }
                else {
                    locations.add(new Location(x, y));
                }
            }

            res = Integer.MAX_VALUE;
            dfs(company, 0, 0, new boolean[n]);

            System.out.println("#" + test + " " + res);
        }
    }

    private static void dfs(Location cur, int cnt, int totalDist, boolean[] visited) {
        if (cnt == n) {
            res = Math.min(res, totalDist + Math.abs(cur.x - house.x) + Math.abs(cur.y - house.y));
        } else if (totalDist < res){
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    Location next = locations.get(i);
                    dfs(next, cnt + 1, totalDist + Math.abs(cur.x - next.x) + Math.abs(cur.y - next.y), visited);
                    visited[i] = false;
                }
            }
        }
    }
}
