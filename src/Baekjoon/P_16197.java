package Baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P_16197 {

    static int n, m;
    static String[][] board;
    static twoCoins[] first_coin;

    public static class twoCoins {
        int coin1_x, coin1_y;
        int coin2_x, coin2_y;
        int count;

        public twoCoins(int coin1_x, int coin1_y, int coin2_x, int coin2_y, int count) {
            this.coin1_x = coin1_x;
            this.coin1_y = coin1_y;
            this.coin2_x = coin2_x;
            this.coin2_y = coin2_y;
            this.count = count;
        }

        public twoCoins(int coin_x, int coin_y) {
            this.coin1_x = coin_x;
            this.coin1_y = coin_y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = arr[0]; m = arr[1];
        int idx = 0;
        board = new String[n][m];
        first_coin = new twoCoins[2];
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                if (board[i][j].equals("o")) first_coin[idx++] = new twoCoins(j, i);
            }
        }

        bw.write(Integer.toString(bfs()));
        bw.flush();
    }

    public static int bfs() {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<twoCoins> q = new LinkedList<>();
        q.add(new twoCoins(first_coin[0].coin1_x, first_coin[0].coin1_y, first_coin[1].coin1_x, first_coin[1].coin1_y, 0));

        while (!q.isEmpty()) {
            twoCoins cur = q.poll();

            if (cur.count >= 10) break;

            for (int i = 0; i < 4; i++) {
                int coin1_x = cur.coin1_x + dx[i], coin1_y = cur.coin1_y + dy[i];
                int coin2_x = cur.coin2_x + dx[i], coin2_y = cur.coin2_y + dy[i];

                if (coin1_x >= 0 && coin1_x < m && coin1_y >= 0 && coin1_y < n && board[coin1_y][coin1_x].equals("#")) {
                    coin1_x = cur.coin1_x;
                    coin1_y = cur.coin1_y;
                }
                if (coin2_x >= 0 && coin2_x < m && coin2_y >= 0 && coin2_y < n && board[coin2_y][coin2_x].equals("#")) {
                    coin2_x = cur.coin2_x;
                    coin2_y = cur.coin2_y;
                }

                int coin_cnt = 0;
                if (coin1_x >= 0 && coin1_x < m && coin1_y >= 0 && coin1_y < n) coin_cnt++;
                if (coin2_x >= 0 && coin2_x < m && coin2_y >= 0 && coin2_y < n) coin_cnt++;

                if (coin_cnt == 1) return cur.count + 1;
                else if (coin_cnt == 2) q.add(new twoCoins(coin1_x, coin1_y, coin2_x, coin2_y, cur.count + 1));
            }
        }

        return -1;
    }
}
