package Baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class P_1780 {

    static int[][] papers;
    static HashMap<Integer, Integer> paperNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        papers = new int[n][n];
        for (int i = 0; i < n; i++) papers[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        paperNum = new HashMap<>();
        paperNum.put(-1, 0);
        paperNum.put(0, 0);
        paperNum.put(1, 0);

        cutPaper(n, 0, 0);

        bw.write(paperNum.get(-1) + "\n");
        bw.write(paperNum.get(0) + "\n");
        bw.write(paperNum.get(1) + "\n");
        bw.flush();
    }

    private static void cutPaper(int size, int x, int y) {
        if (size > 0) {
            int result = checkSameNumber(size, x, y);
            if (result != 0) paperNum.replace(papers[y][x], paperNum.get(papers[y][x]) + 1);
            else {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        cutPaper(size / 3, x + j * (size / 3), y + i * (size / 3));
                    }
                }
            }
        }
    }

    private static int checkSameNumber(int size, int x, int y) {
        int standard = papers[y][x];

        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (papers[i][j] != standard) return 0;
            }
        }

        return 1;
    }
}
