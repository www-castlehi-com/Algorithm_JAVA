package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P_14501 {
    static int max = Integer.MIN_VALUE;
    static int[][] consulting_info;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        consulting_info = new int[n][2];
        for (int i = 0 ; i < n; i++) {
            consulting_info[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        find_consulting(1, 0);

        System.out.print(max);
    }

    public static void find_consulting(int date, int money) {
        if (date == consulting_info.length + 1)
            max = (max > money) ? max : money;
        else if (date <= consulting_info.length) {
            find_consulting(date + consulting_info[date - 1][0], money + consulting_info[date - 1][1]);
            find_consulting(date + 1, money);
        }
    }
}
