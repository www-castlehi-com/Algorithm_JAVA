package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class P_2263 {

    static BufferedWriter bw;
    static int[] inOrder, postOrder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        inOrder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        postOrder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int root = postOrder[n - 1];
        int leftStart = 0, leftEnd = 0, rightStart = 0, rightEnd = n - 1;
        for (int i = 0; i < n; i++) {
            if (inOrder[i] == root) {
                bw.write(root + " ");
                if (i != 0) findTree(i, 0, i - 1, 0, i - 1);
                if (n - i - 1 != 0) findTree(n - i - 1, i + 1, n - 1, i, n - 2);
                break;
            }
        }

        bw.flush();
    }

    private static void findTree(int length, int inStart, int inEnd, int poStart, int poEnd) throws IOException {
        int root = postOrder[poEnd];
        bw.write(root + " ");

        if (length == 1) return ;

        int subLen = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inOrder[i] == root) {
                if (subLen != 0) findTree(subLen, inStart, inStart + subLen - 1, poStart, poStart + subLen - 1);
                if (length - subLen - 1 != 0) findTree(length - subLen - 1, inStart + subLen + 1, inEnd, poStart + subLen, poEnd - 1);
                break;
            }
            subLen++;
        }
    }
}
