import java.io.*;
import java.util.Arrays;

public class P_1018 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0], m = arr[1];

        String[][] board = new String[n][m];
        for (int i = 0; i < n; i++) board[i] = br.readLine().split("");
        String[][] sample1 = new String[n][m];
        String[][] sample2 = new String[n][m];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    sample1[i][j] = "W";
                    sample2[i][j] = "B";
                }
                else if (i % 2 == 0 && j % 2 != 0) {
                    sample1[i][j] = "B";
                    sample2[i][j] = "W";
                }
                else if (i % 2 != 0 && j % 2 == 0) {
                    sample1[i][j] = "B";
                    sample2[i][j] = "W";
                }
                else {
                    sample1[i][j] = "W";
                    sample2[i][j] = "B";
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= n - 8; i++) {
            for (int j = 0; j <= m - 8; j++) {
                int sample1Count = 0, sample2Count = 0;
                for (int k = i; k < i + 8; k++) {
                    for (int l = j; l < j + 8; l++) {
                        if (!board[k][l].equals(sample1[k - i][l - j])) sample1Count++;
                        if (!board[k][l].equals(sample2[k - i][l - j])) sample2Count++;
                    }
                }
                min = (min > Math.min(sample1Count, sample2Count)) ? Math.min(sample1Count, sample2Count) : min;
            }
        }

        bw.write(Integer.toString(min));
        bw.flush();
    }
}
