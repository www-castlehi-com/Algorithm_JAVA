package Baekjoon.Class;

import java.io.*;
import java.util.*;

public class P_17779
{

    static int n;
    static int[][] map;

    static int res = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j < n + 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        setVariance();

        System.out.println(res);
    }

    public static void setVariance() {
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {
                for (int d1 = 1; d1 < n; d1++) {
                    for (int d2 = 1; d1 + d2 <= n; d2++) {
                        res = Math.min(res, divideDistrict(x, y, d1, d2));
                    }
                }
            }
        }
    }

    public static boolean checkRange(int value) {
        if (value >= 1 && value <= n) return true;
        else return false;
    }

    public static int divideDistrict(int x, int y, int d1, int d2) {
        int[][] district = new int[n + 1][n + 1];

        for (int i = 0; i <= d1; i++) {
            if (checkRange(x + i) && checkRange(y - i))
                district[y - i][x + i] = 5;
            if (checkRange(x + d2 + i) && checkRange(y + d2 - i))
                district[y + d2 - i][x + d2 + i] = 5;
        }
        for (int i = 0; i <= d2; i++) {
            if (checkRange(x + i) && checkRange(y + i))
                district[y + i][x + i] = 5;
            if (checkRange(x + d1+ i) && checkRange(y - d1 + i))
                district[y - d1 + i][x + d1 + i] = 5;
        }

        for (int i = 1; i <= x + d1; i++) {
            for (int j = 1; j < y; j++) {
                if (checkRange(i) && checkRange(j)) {
                    if (district[j][i] == 5) break;
                    district[j][i] = 1;
                }
            }
        }
        for (int i = x + d1 + 1; i <= n; i++) {
            for (int j = 1; j <= y + d2; j++) {
                if (checkRange(i) && checkRange(j)) {
                    if (district[j][i] == 5) break;
                    district[j][i] = 2;
                }
            }
        }
        for (int i = 1; i < x + d2; i++) {
            for (int j = n; j >= y; j--) {
                if (checkRange(i) && checkRange(j)) {
                    if (district[j][i] == 5) break;
                    district[j][i] = 3;
                }
            }
        }
        for (int i = n; i >= x + d2; i--) {
            for (int j = n; j > y - d1 + d2; j--) {
                if (checkRange(i) && checkRange(j)) {
                    if (district[j][i] == 5) break;
                    district[j][i] = 4;
                }
            }
        }

        int[] info = new int[6];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (district[i][j] == 0) district[i][j] = 5;
                info[district[i][j]] += map[i][j];
            }
        }

        int max = 0, min = Integer.MAX_VALUE;
        for (int i = 1; i <= 5; i++) {
            min = Math.min(min, info[i]);
            max = Math.max(max, info[i]);
        }

        return max - min;
    }
}
