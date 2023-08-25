package Goorm.Challenge.Week2;

import java.io.*;
import java.util.Arrays;

public class Day10 {

    static int n;
    static int rg, cg, rp, cp;
    static Inst[][] map;

    static public class Inst {
        int count;
        Character command;

        public Inst(int count, Character command) {
            this.count = count;
            this.command = command;
        }
    }

    static boolean[][] visited;
    static int sg = 1, sp = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        rg = line[0] - 1; cg = line[1] - 1;
        line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        rp = line[0] - 1; cp = line[1] - 1;
        map = new Inst[n][n];
        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                int count = Integer.parseInt(s[j].substring(0, s[j].length() - 1));
                Character command = s[j].charAt(s[j].length() - 1);
                map[i][j] = new Inst(count, command);
            }
        }

        game();

        if (sg > sp) bw.write("goorm " + sg);
        else bw.write("player " + sp);
        bw.flush();
    }

    private static void game(){
        visited = new boolean[n][n];
        visited[rg][cg] = true;
        Loop1:
        while (true) {
            int count = map[rg][cg].count;
            char command = map[rg][cg].command;
            for (int i = 1; i <= count; i++) {
                if (move(0, rg, cg, command)) {
                    visited[rg][cg] = true;
                    sg++;
                } else break Loop1;
            }
        }

        visited = new boolean[n][n];
        visited[rp][cp] = true;
        Loop1:
        while (true) {
            int count = map[rp][cp].count;
            char command = map[rp][cp].command;
            for (int i = 1; i <= count; i++) {
                if (move(1, rp, cp, command)) {
                    visited[rp][cp] = true;
                    sp++;
                } else break Loop1;
            }
        }
    }

    private static boolean move(int player, int r, int c, char command) {
        int nextR = r, nextC = c;
        switch (command) {
            case 'L':
                nextC--;
                break;
            case 'R' :
                nextC++;
                break;
            case 'U':
                nextR--;
                break;
            case 'D':
                nextR++;
                break;
        }
        if (nextC == -1) nextC = n - 1;
        if (nextC == n) nextC = 0;
        if (nextR == -1) nextR = n - 1;
        if (nextR == n) nextR = 0;

        if (player == 0) {
            rg = nextR;
            cg = nextC;
        }
        else {
            rp = nextR;
            cp = nextC;
        }

        if (visited[nextR][nextC]) return false;
        else return true;
    }
}
