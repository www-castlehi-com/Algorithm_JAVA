package Codetree.Quest.Samsung;

import java.io.*;
import java.util.*;

public class 이동경로상에_있는_모든_숫자_더하기 {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int y = n / 2, x = n / 2;

        String command = br.readLine();

        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int sum = map[y][x];
        int myDir = 0;
        for (int i = 0; i < t; i++) {
            char dir = command.charAt(i);

            switch(dir) {
                case 'R':
                    myDir = (myDir + 1) % 4;
                    break;
                case 'L':
                    myDir = (myDir + 3) % 4;
                    break;
                case 'F':
                    int nextY = y + dy[myDir];
                    int nextX = x + dx[myDir];
                    if (nextY >= 0 && nextY < n && nextX >= 0 && nextX < n) {
                        y = nextY;
                        x = nextX;
                        sum += map[y][x];
                    }
                    break;
            }
        }

        System.out.print(sum);
    }
}