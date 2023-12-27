package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_1961 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int test = 1; test <= t; test++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            int[][] matrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            String[][] rotateMatrix = new String[n][3];
            for (int i = 0; i < n; i++) {
                StringBuilder num90 = new StringBuilder(), num180 = new StringBuilder(), num270 = new StringBuilder();
                for (int j = n - 1; j >= 0; j--) {
                    num90.append(matrix[j][i]);
                    num180.append(matrix[n - 1 - i][j]);
                    num270.append(matrix[n - j - 1][n - i - 1]);
                }
                rotateMatrix[i][0] = num90.toString();
                rotateMatrix[i][1] = num180.toString();
                rotateMatrix[i][2] = num270.toString();
            }

            System.out.println("#" + test);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(rotateMatrix[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

}
