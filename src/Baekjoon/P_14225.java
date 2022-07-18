package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class P_14225 {

    static int n;
    static int[] s;
    static boolean[] visited = new boolean[2000001];
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        subsequence(0, 0);

        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                bw.write(Integer.toString(i));
                break;
            }
        }
        bw.flush();
    }

    public static void subsequence(int sum, int idx) {
        visited[sum] = true;
        if (idx == n) return ;

        subsequence(sum + s[idx], idx + 1);
        subsequence(sum, idx + 1);
    }
}
